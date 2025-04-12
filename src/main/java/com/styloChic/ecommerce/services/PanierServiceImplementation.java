package com.styloChic.ecommerce.services;


import com.styloChic.ecommerce.dtos.QuantiteDTO;
import com.styloChic.ecommerce.exceptions.CodeReductionException;
import com.styloChic.ecommerce.exceptions.ElementPanierException;
import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.*;
import com.styloChic.ecommerce.repositories.CodeReductionRepository;
import com.styloChic.ecommerce.repositories.PanierRepository;
import com.styloChic.ecommerce.requests.AjouterElementPanierRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PanierServiceImplementation implements PanierService{

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private ElementPanierService elementPanierService;

    @Autowired
    private ProduitService produitService;

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    CodeReductionRepository codeReductionRepository;


    @Override
    public Panier creerPanier(Utilisateur utilisateur) {
        Panier panier = new Panier();
        panier.setUtilisateur(utilisateur);
        panier.setCodeReduction(null);
        return panierRepository.save(panier);
    }



    @Override
    public String ajouterElementPanier(Long utilisateurId, AjouterElementPanierRequest requete) throws ProduitException, ElementPanierException, UtilisateurException {
        Panier panier = panierRepository.chercherPanierParUtilisateurId(utilisateurId);
        Produit produit = produitService.chercherProduit(requete.getIdProduit());

        ElementPanier elementEstDejaPresent = elementPanierService.elementPanierExisteDeja(panier, produit, requete.getTaille(), utilisateurId);

        if (elementEstDejaPresent != null) {
            elementPanierService.modifierElementPanier(utilisateurId, elementEstDejaPresent.getId(), new QuantiteDTO(requete.getOperation()));
        } else {
            ElementPanier elementPanier = new ElementPanier();
            elementPanier.setProduit(produit);
            elementPanier.setPanier(panier);
            elementPanier.setQuantite(1);
            elementPanier.setUtilisateurId(utilisateurId);
            elementPanier.setTva(produit.getTva());

            double totalHT = produit.getPrixVenteHT();
            double totalTTC = produit.getPrixVenteTTC();
            double totalTTCReduit = produit.getPrixVenteTTCReduit();

            elementPanier.setTotalHt(totalHT);
            elementPanier.setTotalTTC(totalTTC);
            elementPanier.setTaille(requete.getTaille());
            elementPanier.setTotalTTCReduit(totalTTCReduit);

            ElementPanier elementPanierCree = elementPanierService.creerElementPanier(elementPanier);
            panier.getElementsPanier().add(elementPanierCree);
        }

        mettreAJourPanier(panier);
        return "Produit ajouté/modifié dans le panier !";
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
        panier.setCodeReduction(null);
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
    public Panier chercherPanierUtilisateur(Long utilisateurId) {
        Panier panier = panierRepository.chercherPanierParUtilisateurId(utilisateurId);
        return panier;
    }

    @Override
    public void viderPanier(Long utilisateurId) throws UtilisateurException{
        Utilisateur utilisateur = utilisateurService.chercherUtilisateurParIdParticulier(utilisateurId);
        Panier panier = panierRepository.chercherPanierParUtilisateurId(utilisateurId);


        panier.getElementsPanier().clear();
        panier.setCodeReduction(null);
        panier.setTva(0.0);
        panier.setMontantBase(0.0);
        panier.setPrixTotalTTC(0.0);
        panier.setPrixTotalHt(0.0);
        panier.setTotalElement(0);
        panier.setPrixTotalTTCReduit(0.0);
        panier.setPourcentageReduction(0.0);
        panier.setMontantReduit(0.0);

        panierRepository.save(panier);
    }

    @Override
    public String appliquerCodePromo(Long utilisateurId, String codePromo) throws CodeReductionException {
        Panier panier = panierRepository.chercherPanierParUtilisateurId(utilisateurId);

        CodeReduction codeReduction = codeReductionRepository.findByCode(codePromo);
        if (codeReduction == null) {
            throw new CodeReductionException("Le code promo est invalide !");
        }

        if (!codeReduction.isActif()) {
            throw new CodeReductionException("Le code promo n'est plus actif !");
        }

        Date dateActuelle = new Date();
        if (codeReduction.getDateDebut().after(dateActuelle)) {
            throw new CodeReductionException("Ce code focntionne à partir du : "+codeReduction.getDateDebut()+" !");
        }
        if (codeReduction.getDateFin().before(dateActuelle)) {
            throw new CodeReductionException("La durée du code promo a expiré !");
        }

        if (panier.getPrixTotalTTC() < codeReduction.getConditionReduction()) {
            throw new CodeReductionException("Le montant total ne dépasse pas "+codeReduction.getConditionReduction()+ "MAD !");
        }

        double reduction = (panier.getPrixTotalTTC() * codeReduction.getPourcentage()) / 100;
        double prixAvecReduction = panier.getPrixTotalTTC() - reduction;

        panier.setPourcentageReduction(codeReduction.getPourcentage());
        panier.setMontantReduit(prixAvecReduction);
        panier.setCodeReduction(codeReduction);
        panierRepository.save(panier);

        return "Le code promo a été appliqué avec succès !";
    }



}
