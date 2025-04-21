package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.config.JwtProvider;
import com.styloChic.ecommerce.exceptions.FournisseurException;
import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.models.*;
import com.styloChic.ecommerce.repositories.*;
import com.styloChic.ecommerce.requests.CreationProduitRequest;
import com.styloChic.ecommerce.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProduitServiceImplementation implements ProduitService{

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private CouleurRepository couleurRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    private Utilisateur verifierAdmin(String jwt) throws ProduitException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(email);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            throw new ProduitException("Accès interdit : vous devez être un administrateur !");
        }
        return admin;
    }
    @Override
    public Produit creerProduit(CreationProduitRequest requete,String jwt) throws ProduitException,FournisseurException{
        Utilisateur admin = verifierAdmin(jwt);

        Couleur couleur = couleurRepository.findById(requete.getCouleurId())
                .orElseThrow(() -> new ProduitException("La couleur spécifiée est introuvable !"));

        Categorie categorie = categorieRepository.findById(requete.getCategorieId())
                .orElseThrow(() -> new ProduitException("La catégorie spécifiée est introuvable !"));

        Fournisseur fournisseur = fournisseurRepository.findById(requete.getFournisseurId())
                .orElseThrow(() -> new FournisseurException("Le fournisseur est inexistant !"));

        int quantiteTotale = requete.getTailles().stream()
                .mapToInt(Taille::getQuantiteEnStock)
                .sum();

        Produit produit = new Produit();
        produit.setTitre(requete.getTitre());
        produit.setCouleur(couleur);
        produit.setDescription(requete.getDescription());
        produit.setPrixAchat(requete.getPrixAchat());
        produit.setPrixVenteHT(requete.getPrixVenteHT());
        produit.setTva(requete.getTva());
        produit.setTailles(requete.getTailles());
        double tvaDecimal = requete.getTva() / 100.0;
        double prixTTC = requete.getPrixVenteHT() * (1 + tvaDecimal);
        produit.setPrixVenteTTC(prixTTC);

        produit.setPourcentageReduction(requete.getPourcentageReduction());
        double pourcentageReductionDecimal = requete.getPourcentageReduction() / 100.0;
        double prixTTCReduit = prixTTC * (1 - pourcentageReductionDecimal);
        produit.setPrixVenteTTCReduit(prixTTCReduit);

        produit.setCategorie(categorie);
        produit.setFournisseur(fournisseur);
        produit.setQuantiteEnStock(quantiteTotale);
        produit.setConseilEntretien(requete.getConseilEntretien());
        produit.setSaison(requete.getSaison());
        produit.setTotal_avis(0);
        produit.setTotal_votes(0);
        produit.setImagePrincipale(requete.getImagePrincipale());
        produit.setDateCreation(LocalDateTime.now());
        produit.setDateModification(LocalDateTime.now());
        produit.setAdmin(admin);

        Produit produitAjoute = produitRepository.save(produit);
        return produitAjoute;
    }

    @Override
    public Produit miseAjourProduit(Long idProduit, CreationProduitRequest requete,String jwt) throws ProduitException,FournisseurException {
        Utilisateur admin = verifierAdmin(jwt);
        Produit produitExistant = produitRepository.findById(idProduit)
                .orElseThrow(() -> new ProduitException("Produit avec ID " + idProduit + " non trouvé"));

        Couleur couleur = couleurRepository.findById(requete.getCouleurId())
                .orElseThrow(() -> new ProduitException("La couleur spécifiée est introuvable !"));

        Categorie categorie = categorieRepository.findById(requete.getCategorieId())
                .orElseThrow(() -> new ProduitException("La catégorie spécifiée est introuvable !"));

        Fournisseur fournisseur = fournisseurRepository.findById(requete.getFournisseurId())
                .orElseThrow(() -> new FournisseurException("Le fournisseur est inexistant !"));

        int quantiteTotale = requete.getTailles().stream()
                .mapToInt(Taille::getQuantiteEnStock)
                .sum();

        produitExistant.setTitre(requete.getTitre());
        produitExistant.setCouleur(couleur);
        produitExistant.setDescription(requete.getDescription());
        produitExistant.setPrixAchat(requete.getPrixAchat());
        produitExistant.setPrixVenteHT(requete.getPrixVenteHT());
        produitExistant.setTva(requete.getTva());
        double tvaDecimal = requete.getTva() / 100.0;
        double prixTTC = requete.getPrixVenteHT() * (1 + tvaDecimal);
        produitExistant.setPrixVenteTTC(prixTTC);

        produitExistant.setPourcentageReduction(requete.getPourcentageReduction());
        double pourcentageReductionDecimal = requete.getPourcentageReduction() / 100.0;
        double prixTTCReduit = prixTTC * (1 - pourcentageReductionDecimal);
        produitExistant.setPrixVenteTTCReduit(prixTTCReduit);

        produitExistant.setTailles(requete.getTailles());
        produitExistant.setCategorie(categorie);
        produitExistant.setFournisseur(fournisseur);
        produitExistant.setQuantiteEnStock(quantiteTotale);
        produitExistant.setConseilEntretien(requete.getConseilEntretien());
        produitExistant.setSaison(requete.getSaison());
        produitExistant.setImagePrincipale(requete.getImagePrincipale());
        produitExistant.setDateModification(LocalDateTime.now());
        produitExistant.setAdmin(admin);

        Produit produitMisAJour = produitRepository.save(produitExistant);

        return produitMisAJour;
    }

    @Override
    public ProduitClientResponse chercherProduitParIdPourClient(Long id) throws ProduitException {
        Optional<Produit> produit = produitRepository.findById(id);
        if (produit.isEmpty()) {
            throw new ProduitException("Produit non trouvé avec id: " + id);
        }

        Produit p = produit.get();
        ProduitClientResponse produitClientResponse = new ProduitClientResponse(
                p.getId(),
                p.getTitre(),
                p.getDescription(),
                p.getTva(),
                p.getPrixVenteHT(),
                p.getPrixVenteTTC(),
                p.getPrixVenteTTCReduit(),
                p.getPourcentageReduction(),
                p.getCategorie().getCategorieParente().getCategorieParente().getNom(),
                p.getCategorie().getCategorieParente().getNom(),
                p.getCategorie().getNom(),
                p.getQuantiteEnStock(),
                p.getImagePrincipale(),
                p.getSaison(),
                p.getConseilEntretien(),
                p.getCouleur().getNom(),
                p.getTailles(),
                p.getImagesProduit(),
                p.getVotes(),
                p.getAvis(),
                p.getInspirations(),
                p.getVotes().size(),
                p.getAvis().size()
        );

        return produitClientResponse;
    }

    @Override
    public ProduitAdminResponse chercherProduitParIdPourAdmin(Long id,String jwt) throws ProduitException {
        verifierAdmin(jwt);
        Optional<Produit> produit = produitRepository.findById(id);
        if (produit.isEmpty()) {
            throw new ProduitException("Produit non trouvé avec id: " + id);
        }

        Produit p = produit.get();
        ProduitAdminResponse produitAdminResponse = new ProduitAdminResponse(
                p.getId(),
                p.getTitre(),
                p.getDescription(),
                p.getTva(),
                p.getPrixVenteHT(),
                p.getPrixVenteTTC(),
                p.getPrixVenteTTCReduit(),
                p.getPourcentageReduction(),
                p.getCategorie().getCategorieParente().getCategorieParente().getNom(),
                p.getCategorie().getCategorieParente().getNom(),
                p.getCategorie().getNom(),
                p.getQuantiteEnStock(),
                p.getImagePrincipale(),
                p.getSaison(),
                p.getConseilEntretien(),
                p.getCouleur().getNom(),
                p.getTailles(),
                p.getImagesProduit(),
                p.getVotes(),
                p.getAvis(),
                p.getInspirations(),
                p.getVotes().size(),
                p.getAvis().size(),
                (p.getAdmin().getNom() +" "+ p.getAdmin().getPrenom()),
                p.getDateCreation(),
                p.getDateModification(),
                p.getPrixAchat(),
                p.getFournisseur().getId(),
                p.getCouleur().getId(),
                p.getCategorie().getId()
        );

        return produitAdminResponse;
    }

    //Utiliser en suppression + Image produit
    @Override
    public Produit chercherProduit(Long id) throws ProduitException {
        Optional<Produit> produit = produitRepository.findById(id);
        if (produit.isEmpty()) {
            throw new ProduitException("Produit non trouvé avec id: " + id);
        }
        return produit.get();
    }
    @Override
    public String supprimerProduit(Long idProduit, String jwt) throws ProduitException {
        Utilisateur admin = verifierAdmin(jwt);

        Produit produit = chercherProduit(idProduit);

        if (!produit.getVotes().isEmpty()) {
            produit.getVotes().clear();
        }

        if (!produit.getAvis().isEmpty()) {
            produit.getAvis().clear();
        }

        if (!produit.getImagesProduit().isEmpty()) {
            produit.getImagesProduit().clear();
        }

        produitRepository.delete(produit);

        return "Produit supprimé avec succès.";
    }

    @Override
    public Page<ProduitClientResponse> avoirTousProduits(String categorie, String couleurs, String tailles, Integer prixMin, Integer prixMax, String trie, String stock, Integer numeroPage, Integer taillePage) {
        Pageable paginable = PageRequest.of(numeroPage, taillePage);

        List<Produit> produits = produitRepository.filterProduit(categorie, prixMin, prixMax, trie);


        if (couleurs != null && !couleurs.isEmpty()) {
            List<String> couleursList = Arrays.stream(couleurs.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());

            if (!couleursList.isEmpty()) {
                produits = produits.stream()
                        .filter(p -> couleursList.stream()
                                .anyMatch(c -> c.equalsIgnoreCase(p.getCouleur().getNom())))
                        .collect(Collectors.toList());
            }
        }

        if (tailles != null && !tailles.isEmpty()) {
            List<String> taillesList = Arrays.stream(tailles.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());

            if (!taillesList.isEmpty()) {
                produits = produits.stream()
                        .filter(p -> p.getTailles().stream()
                                .anyMatch(tailleProduit -> taillesList.stream()
                                        .anyMatch(tailleParam -> tailleParam.equalsIgnoreCase(tailleProduit.getNom()))))
                        .collect(Collectors.toList());
            }
        }

        if (stock != null) {
            if (stock.equals("en_stock")) {
                produits = produits.stream().filter(p -> p.getQuantiteEnStock() > 0).collect(Collectors.toList());
            } else if (stock.equals("en_rupture_de_stock")) {
                produits = produits.stream().filter(p -> p.getQuantiteEnStock() < 1).collect(Collectors.toList());
            }
        }

        int indexDepart = (int) paginable.getOffset();
        int indexFin = Math.min((indexDepart + paginable.getPageSize()), produits.size());

        List<Produit> contenuDePage = produits.subList(indexDepart, indexFin);

        List<ProduitClientResponse> produitDTOs = contenuDePage.stream()
                .map(p -> new ProduitClientResponse(
                        p.getId(),
                        p.getTitre(),
                        p.getDescription(),
                        p.getTva(),
                        p.getPrixVenteHT(),
                        p.getPrixVenteTTC(),
                        p.getPrixVenteTTCReduit(),
                        p.getPourcentageReduction(),
                        p.getCategorie().getCategorieParente().getCategorieParente().getNom(),
                        p.getCategorie().getCategorieParente().getNom(),
                        p.getCategorie().getNom(),
                        p.getQuantiteEnStock(),
                        p.getImagePrincipale(),
                        p.getSaison(),
                        p.getConseilEntretien(),
                        p.getCouleur().getNom(),
                        p.getTailles(),
                        p.getImagesProduit(),
                        p.getVotes(),
                        p.getAvis(),
                        p.getInspirations(),
                        p.getVotes().size(),
                        p.getAvis().size()
                ))
                .collect(Collectors.toList());

        return new PageImpl<>(produitDTOs, paginable, produits.size());
    }


    @Override
    public List<Produit> chercherTousProduits() {
        return produitRepository.findAll();
    }


   @Override
   public Page<ProduitClientResponse> chercherProduits(String requete, Integer numPage, Integer taillePage) {
       Pageable pageable = PageRequest.of(numPage, taillePage);

       Page<Produit> produitsPage = produitRepository.searchByTitreOrDescription(requete, pageable);

       List<ProduitClientResponse> produitDTOs = produitsPage.stream()
               .map(p -> new ProduitClientResponse(
                       p.getId(),
                       p.getTitre(),
                       p.getDescription(),
                       p.getTva(),
                       p.getPrixVenteHT(),
                       p.getPrixVenteTTC(),
                       p.getPrixVenteTTCReduit(),
                       p.getPourcentageReduction(),
                       p.getCategorie().getCategorieParente().getCategorieParente().getNom(),
                       p.getCategorie().getCategorieParente().getNom(),
                       p.getCategorie().getNom(),
                       p.getQuantiteEnStock(),
                       p.getImagePrincipale(),
                       p.getSaison(),
                       p.getConseilEntretien(),
                       p.getCouleur().getNom(),
                       p.getTailles(),
                       p.getImagesProduit(),
                       p.getVotes(),
                       p.getAvis(),
                       p.getInspirations(),
                       p.getVotes().size(),
                       p.getAvis().size()
               ))
               .collect(Collectors.toList());

       return new PageImpl<>(produitDTOs, pageable, produitsPage.getTotalElements());
   }


    @Override
    public void ajouterImageProduit(Long idProduit, String image,String jwt) throws ProduitException {
        Utilisateur admin = verifierAdmin(jwt);
        Produit produit = chercherProduit(idProduit);
        ImageProduit imageProduit = new ImageProduit(produit, image,LocalDateTime.now(),LocalDateTime.now(),admin);
        produit.getImagesProduit().add(imageProduit);
        produitRepository.save(produit);
    }

    @Override
    public void supprimerImageProduit(Long idProduit, Long imageId,String jwt) throws ProduitException {
        Utilisateur admin = verifierAdmin(jwt);
        Produit produit = chercherProduit(idProduit);
        ImageProduit imageAmodifier = produit.getImagesProduit().stream()
                .filter(image -> image.getId().equals(imageId))
                .findFirst()
                .orElseThrow(() -> new ProduitException("Image introuvable avec id " + imageId));
        produit.getImagesProduit().removeIf(image -> image.getId().equals(imageId));
        produitRepository.save(produit);
    }


    @Override
    public List<ImageProduitClientResponse> obtenirImagesProduitPourClient(Long idProduit) throws ProduitException {
        Produit produit = chercherProduit(idProduit);
        return produit.getImagesProduit().stream()
                .map(img -> new ImageProduitClientResponse(
                        img.getId(),
                        img.getImage()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<ImageProduitAdminResponse> obtenirImagesProduitPourAdmin(Long idProduit,String jwt) throws ProduitException {
        verifierAdmin(jwt);
        Produit produit = chercherProduit(idProduit);

        return produit.getImagesProduit().stream()
                .map(img -> new ImageProduitAdminResponse(
                        img.getId(),
                        img.getImage(),
                        img.getDateCreation(),
                        img.getDateModification(),
                        img.getProduit().getAdmin() != null ? img.getProduit().getAdmin().getNom() : "Inconnu"
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void modifierImageProduit(Long idProduit, Long imageId, String nouvelleImage,String jwt) throws ProduitException {
        Utilisateur admin = verifierAdmin(jwt);
        Produit produit = chercherProduit(idProduit);
        ImageProduit imageAmodifier = produit.getImagesProduit().stream()
                .filter(image -> image.getId().equals(imageId))
                .findFirst()
                .orElseThrow(() -> new ProduitException("Image introuvable avec id " + imageId));
        imageAmodifier.setImage(nouvelleImage);
        imageAmodifier.setDateModification(LocalDateTime.now());
        imageAmodifier.setAdmin(admin);
        produitRepository.save(produit);
    }

    @Override
    public ImageProduitClientResponse avoirImageProduitAvecIdPourClient(Long idProduit, Long imageId) throws ProduitException {
        Produit produit = chercherProduit(idProduit);
        ImageProduit image = produit.getImagesProduit().stream()
                .filter(img -> img.getId().equals(imageId))
                .findFirst()
                .orElseThrow(() -> new ProduitException("Image introuvable avec id: " + imageId));

        return new ImageProduitClientResponse(
                image.getId(),
                image.getImage()
        );

    }

    @Override
    public ImageProduitAdminResponse avoirImageProduitAvecIdPourAdmin(Long idProduit, Long imageId, String jwt) throws ProduitException {
        verifierAdmin(jwt);
        Produit produit = chercherProduit(idProduit);

        ImageProduit image = produit.getImagesProduit().stream()
                .filter(img -> img.getId().equals(imageId))
                .findFirst()
                .orElseThrow(() -> new ProduitException("Image introuvable avec id: " + imageId));

        return new ImageProduitAdminResponse(
                image.getId(),
                image.getImage(),
                image.getDateCreation(),
                image.getDateModification(),
                image.getProduit().getAdmin() != null ? image.getProduit().getAdmin().getNom() : "Inconnu"
        );
    }

    @Override
    public long compterProduits(String jwt) throws ProduitException {
        String adminEmail = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(adminEmail);

        if (admin == null || !admin.getRole().equals("ADMIN")) {
            throw new ProduitException("Accès interdit : vous devez être un administrateur pour effectuer cette action !");
        }

        return produitRepository.count();
    }

    @Override
    public List<ProduitBest10Response> getTop10ProduitsLesPlusVendus() {
        List<Object[]> results = ligneCommandeRepository.findTop10ProductsBySales();

        return results.stream()
                .map(result -> {
                    Produit produit = (Produit) result[0];
                    Long totalVentes = (Long) result[1];

                    return new ProduitBest10Response(
                            produit.getId(),
                            produit.getTitre(),
                            totalVentes
                    );
                })
                .collect(Collectors.toList());
    }



}
