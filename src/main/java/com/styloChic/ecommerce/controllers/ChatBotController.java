package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.models.Produit;
import com.styloChic.ecommerce.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/chatbot")
public class ChatBotController {

    @Autowired
    private ProduitRepository produitRepository;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> handleDialogflowWebhook(
            @RequestBody Map<String, Object> request) {

        try {
            Map<String, Object> queryResult = (Map<String, Object>) request.get("queryResult");
            Map<String, Object> parameters = (Map<String, Object>) queryResult.get("parameters");

            String intentName = Optional.ofNullable(queryResult.get("intent"))
                    .map(intent -> ((Map<String, Object>) intent).get("displayName"))
                    .map(Object::toString)
                    .orElse("");

            Map<String, Object> response = new HashMap<>();

            if (intentName.equalsIgnoreCase("RechercheProduit")) {
                handleProduitRequest(parameters, response);
            } else {
                response.put("fulfillmentText", "Je n'ai pas compris votre demande. Que souhaitez-vous faire ?");
                addQuickReplies(response, Arrays.asList("Rechercher un produit", "Voir les catégories", "Contactez-nous"));
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    private void handleProduitRequest(Map<String, Object> parameters, Map<String, Object> response) {
        String type = parameters.getOrDefault("type", "").toString().trim().toLowerCase();
        String couleur = parameters.getOrDefault("couleur", "").toString().trim().toLowerCase();

        boolean hasType = !type.isEmpty();
        boolean hasCouleur = !couleur.isEmpty();

        System.out.println(hasType);
        System.out.println(hasCouleur);

        List<Produit> produits = Collections.emptyList();
        String searchDescription = "";

        if (hasType && hasCouleur) {
            produits = produitRepository.findByCategorieNomAndCouleurNom(type, couleur);
            searchDescription = type + " en " + couleur;
        } else if (hasType) {
            produits = produitRepository.findByCategorieNom(type);
            searchDescription = type;
        } else if (hasCouleur) {
            produits = produitRepository.findByCouleurNom(couleur);
            searchDescription = "produits en " + couleur;
        } else {
            response.put("fulfillmentText", "Je peux vous aider à trouver des produits. Pourriez-vous préciser ce que vous cherchez ? Par exemple : 'Je cherche des sacs' ou 'Montrez-moi des produits en rouge'");
            addQuickReplies(response, Arrays.asList("Voir toutes les catégories", "Produits en promotion", "Nouveautés"));
            return;
        }

        if (produits.isEmpty()) {
            handleNoProductsFound(response, hasType, hasCouleur, type, couleur);
        } else {
            String produitsText = formatProduitsForMessaging(produits, searchDescription);
            response.put("fulfillmentText", produitsText);

            List<Map<String, Object>> messages = new ArrayList<>();
            messages.add(createTextMessage(produitsText));

            if (hasType && hasCouleur) {
                messages.add(createQuickReplies(Arrays.asList("Voir plus de " + type, "Autre couleur", "Nouvelle recherche")));
            } else if (hasType) {
                messages.add(createQuickReplies(Arrays.asList("Filtrer par couleur", "Voir autres catégories", "Nouvelle recherche")));
            } else if (hasCouleur) {
                messages.add(createQuickReplies(Arrays.asList("Filtrer par catégorie", "Voir autres couleurs", "Nouvelle recherche")));
            }

            response.put("fulfillmentMessages", messages);
        }
    }

    private void handleNoProductsFound(Map<String, Object> response, boolean hasType, boolean hasCouleur, String type, String couleur) {
        String message;
        List<String> quickReplies = new ArrayList<>();

        if (hasType && hasCouleur) {
            message = "Désolé, nous n'avons pas de " + type + " en " + couleur + " dans notre catalogue actuel.";
            quickReplies.add("Voir d'autres " + type);
            quickReplies.add("Voir d'autres produits en " + couleur);
        } else if (hasType) {
            message = "Nous n'avons actuellement aucun " + type + " disponible. Nous avons peut-être d'autres articles qui pourraient vous intéresser.";
            quickReplies.add("Voir toutes les catégories");
            quickReplies.add("Produits similaires");
        } else if (hasCouleur) {
            message = "Aucun produit disponible en " + couleur + " pour le moment.";
            quickReplies.add("Voir nos couleurs disponibles");
            quickReplies.add("Voir toutes les catégories");
        } else {
            message = "Je n'ai pas trouvé de produits correspondants. Pourriez-vous préciser votre recherche ?";
            quickReplies.add("Guide de recherche");
            quickReplies.add("Contactez un conseiller");
        }

        quickReplies.add("Nouvelle recherche");
        List<Map<String, Object>> messages = new ArrayList<>();
        messages.add(createTextMessage(message));
        messages.add(createQuickReplies(quickReplies));
        response.put("fulfillmentMessages", messages);
        response.put("fulfillmentText", message);
    }

    private String formatProduitsForMessaging(List<Produit> produits, String searchDescription) {
        if (produits.isEmpty()) {
            return "Aucun produit trouvé pour: " + searchDescription;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("🔍 Voici nos ").append(searchDescription).append(" :\n");
        sb.append("----------------------------\n");

        produits.forEach(p -> {
            sb.append("▪ ").append(p.getTitre()).append("\n");
            sb.append("Prix: ").append(String.format("%.2f", p.getPrixVenteTTC())).append(" MAD");

            if (p.getPourcentageReduction() > 0) {
                sb.append("\n⚠ Promotion: -").append(p.getPourcentageReduction()).append("%");
            }

            sb.append("\n----------------------------\n");
        });

        if (produits.size() > 3) {
            sb.append("\nNous avons ").append(produits.size()).append(" produits au total.");
        }

        return sb.toString();
    }

    private Map<String, Object> createTextMessage(String text) {
        return Map.of("text", Map.of("text", Arrays.asList(text)));
    }

    private Map<String, Object> createQuickReplies(List<String> suggestions) {
        return Map.of("quickReplies", Map.of(
                "title", "Options:",
                "quickReplies", suggestions
        ));
    }

    private void addQuickReplies(Map<String, Object> response, List<String> suggestions) {
        List<Map<String, Object>> messages = new ArrayList<>();
        if (response.containsKey("fulfillmentMessages")) {
            messages = (List<Map<String, Object>>) response.get("fulfillmentMessages");
        }
        messages.add(createQuickReplies(suggestions));
        response.put("fulfillmentMessages", messages);
    }
}