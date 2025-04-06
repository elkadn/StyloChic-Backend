package com.styloChic.ecommerce.services;


import com.styloChic.ecommerce.dtos.QuantiteDTO;
import com.styloChic.ecommerce.exceptions.ElementPanierException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.ElementPanier;
import com.styloChic.ecommerce.models.Panier;
import com.styloChic.ecommerce.models.Produit;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.repositories.ElementPanierRepository;
import com.styloChic.ecommerce.repositories.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ElementPanierServiceImplementation implements ElementPanierService{

    @Autowired
    private ElementPanierRepository elementPanierRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    PanierRepository panierRepository;



    @Override
    public ElementPanier creerElementPanier(ElementPanier elementPanier) {
        elementPanier.setQuantite(elementPanier.getQuantite());
        elementPanier.setTotalHt(elementPanier.getProduit().getPrixVenteHT() * elementPanier.getQuantite());
        elementPanier.setTotalTTC(elementPanier.getProduit().getPrixVenteTTC() * elementPanier.getQuantite());
        elementPanier.setTotalTTCReduit(elementPanier.getProduit().getPrixVenteTTCReduit() * elementPanier.getQuantite());
        elementPanier.setTva(elementPanier.getTva());

        ElementPanier elementPanierCree = elementPanierRepository.save(elementPanier);
        return elementPanierCree;
    }


    @Override
    public ElementPanier modifierElementPanier(Long utilisateurId, Long id, QuantiteDTO quantiteDTO) throws ElementPanierException, UtilisateurException {
        ElementPanier element = chercherElementPanierParId(id);
        Utilisateur utilisateur = utilisateurService.chercherUtilisateurParIdParticulier(element.getUtilisateurId());
        Panier panier = panierRepository.chercherPanierParUtilisateurId(utilisateurId);
        if (!utilisateur.getId().equals(utilisateurId)) {
            throw new ElementPanierException("Ce n'est pas votre panier !");
        }

        Produit produit = element.getProduit();
        int quantiteMiseAjour = element.getQuantite();
        double totalHT = 0.0;
        double totalTTC = 0.0;
        double totalTTCReduit = 0.0;

        if (quantiteDTO.getOperation().equals("Incrementer")) {
            quantiteMiseAjour = element.getQuantite() + 1;
            element.setQuantite(quantiteMiseAjour);
        }
        else if (quantiteDTO.getOperation().equals("Decrementer")) {
            quantiteMiseAjour = element.getQuantite() - 1;

            if (quantiteMiseAjour <= 0) {
                supprimerElementPanier(utilisateurId, id);
                return null;
            }
            element.setQuantite(quantiteMiseAjour);
        } else {
            throw new ElementPanierException("Opération invalide !");
        }

        totalHT = produit.getPrixVenteHT() * quantiteMiseAjour;
        totalTTC = produit.getPrixVenteTTC() * quantiteMiseAjour;
        totalTTCReduit = produit.getPrixVenteTTCReduit() * quantiteMiseAjour;

        element.setTotalHt(totalHT);
        element.setTotalTTC(totalTTC);
        element.setTotalTTCReduit(totalTTCReduit);
        element.setTva(produit.getTva());

        mettreAJourPanier(panier);
        return elementPanierRepository.save(element);
    }

    private void mettreAJourPanier(Panier panier) {
        double totalTTC = 0;
        double totalTTCReduit = 0;
        double totalHT = 0;
        int totalElements = 0;
        double totalTVA = 0;
        double totalQuantite = 0;

        for (ElementPanier elementPanier : panier.getElementsPanier()) {
            totalTTC += elementPanier.getTotalTTC();
            totalTTCReduit += elementPanier.getTotalTTCReduit();
            totalHT += elementPanier.getTotalHt();
            totalElements += elementPanier.getQuantite();
            totalTVA += elementPanier.getTva() * elementPanier.getQuantite();
            totalQuantite += elementPanier.getQuantite();
        }

        panier.setPrixTotalHt(totalHT);
        panier.setPrixTotalTTC(totalTTC);
        panier.setPrixTotalTTCReduit(totalTTCReduit);
        panier.setTotalElement(totalElements);
        panier.setMontantBase(totalTTCReduit);
        panier.setMontantReduit(totalTTCReduit);
        panier.setPourcentageReduction(0.00);
        if (totalQuantite > 0) {
            double moyenneTVA = totalTVA / totalQuantite;
            panier.setTva(moyenneTVA);
        } else {
            panier.setTva(0.0);
        }
        panierRepository.save(panier);
    }





    @Override
    public ElementPanier elementPanierExisteDeja(Panier panier, Produit produit, String taille, Long utilisateurId) {
        ElementPanier elementPanier = elementPanierRepository.elementPanierExiste(panier, produit, taille, utilisateurId);
        return elementPanier;
    }

    @Override
    public void supprimerElementPanier(Long utilisateurId, Long elementPanierId) throws ElementPanierException, UtilisateurException {
        ElementPanier elementPanier = chercherElementPanierParId(elementPanierId);
        Utilisateur utilisateur = utilisateurService.chercherUtilisateurParIdParticulier(elementPanier.getUtilisateurId());
        Utilisateur utilisateurRequete = utilisateurService.chercherUtilisateurParIdParticulier(utilisateurId);
        Panier panier = panierRepository.chercherPanierParUtilisateurId(utilisateurId);
        if(utilisateur.getId().equals(utilisateurRequete.getId())){
            elementPanierRepository.deleteById(elementPanierId);
            mettreAJourPanier(panier);
        }else{
            throw new ElementPanierException("Ce n'est pas votre panier !");
        }
    }

    @Override
    public ElementPanier chercherElementPanierParId(Long elementPanierId) throws ElementPanierException {
        Optional<ElementPanier> optionnel = elementPanierRepository.findById(elementPanierId);

        if(optionnel.isPresent()){
            return optionnel.get();
        }
        throw new ElementPanierException("Element panier non trouvé avec id : "+elementPanierId);
    }
}
