package com.styloChic.ecommerce.services;


import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.models.ElementPanier;
import com.styloChic.ecommerce.models.Panier;
import com.styloChic.ecommerce.models.Produit;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.repositories.PanierRepository;
import com.styloChic.ecommerce.requests.AjouterElementPanierRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PanierServiceImplementation implements PanierService{

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private ElementPanierService elementPanierService;

    @Autowired
    private ProduitService produitService;


    @Override
    public Panier creerPanier(Utilisateur utilisateur) {
        Panier panier = new Panier();
        panier.setUtilisateur(utilisateur);
        return panierRepository.save(panier);
    }

    @Override
    public String ajouterElementPanier(Long utilisateurId, AjouterElementPanierRequest requete) throws ProduitException {
        Panier panier = panierRepository.chercherPanierParUtilisateurId(utilisateurId);
        Produit produit = produitService.chercherProduit(requete.getIdProduit());
        ElementPanier elementEstDejaPresent = elementPanierService.elementPanierExisteDeja(panier, produit, requete.getTaille(), utilisateurId);

        if (elementEstDejaPresent == null) {
            ElementPanier elementPanier = new ElementPanier();
            elementPanier.setProduit(produit);
            elementPanier.setPanier(panier);
            elementPanier.setQuantite(requete.getQuantite());
            elementPanier.setUtilisateurId(utilisateurId);

            double totalHT = requete.getQuantite() * produit.getPrixVenteHT();
            double totalTTC = requete.getQuantite() * produit.getPrixVenteTTC();
            elementPanier.setTotalHt(totalHT);
            elementPanier.setTotalTTC(totalTTC);
            elementPanier.setTaille(requete.getTaille());

            ElementPanier elementPanierCree = elementPanierService.creerElementPanier(elementPanier);
            panier.getElementsPanier().add(elementPanierCree);
        }
        return "Produit ajouté au panier !";
    }

    @Override
    public Panier chercherPanierUtilisateur(Long utilisateurId) {
        Panier panier = panierRepository.chercherPanierParUtilisateurId(utilisateurId);

        double totalTTC = 0;
        double totalHT = 0;
        int totalElements = 0;

        for (ElementPanier elementPanier : panier.getElementsPanier()) {
            totalTTC = totalTTC + elementPanier.getTotalHt();
            totalHT = totalHT + elementPanier.getTotalHt();
            totalElements = totalElements + elementPanier.getQuantite();
        }

        panier.setPrixTotalHt(totalHT);
        panier.setPrixTotalTTC(totalTTC);
        panier.setTotalElement(totalElements);
        panier.setMontantBase(totalTTC);
        panier.setMontantReduit(totalTTC);
        panier.setPourcentageReduction(0.00);


        return panier;
    }

    @Override
    public void viderPanier(Long utilisateurId) {
        Panier panier = panierRepository.chercherPanierParUtilisateurId(utilisateurId);
        panier.getElementsPanier().clear();
        panierRepository.save(panier);
    }


}
