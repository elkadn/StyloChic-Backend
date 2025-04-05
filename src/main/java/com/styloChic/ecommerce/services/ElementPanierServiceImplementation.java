package com.styloChic.ecommerce.services;


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
    private PanierRepository panierRepository;

    @Override
    public ElementPanier creerElementPanier(ElementPanier elementPanier) {
        elementPanier.setQuantite(1);
        elementPanier.setTotalHt(elementPanier.getProduit().getPrixVenteHT() * elementPanier.getQuantite());
        elementPanier.setTotalTTC(elementPanier.getProduit().getPrixVenteTTC() * elementPanier.getQuantite());
        elementPanier.setTva(elementPanier.getTva());

        ElementPanier elementPanierCree = elementPanierRepository.save(elementPanier);
        return elementPanierCree;
    }

    @Override
    public ElementPanier modifierElementPanier(Long utilisateurId, Long id, ElementPanier elementPanier) throws ElementPanierException, UtilisateurException {

        ElementPanier element = chercherElementPanierParId(id);
        Utilisateur utilisateur = utilisateurService.chercherUtilisateurParIdParticulier(element.getUtilisateurId());

        if (utilisateur.getId().equals(utilisateurId)) {
            element.setQuantite(elementPanier.getQuantite());
            element.setTotalTTC(element.getQuantite() * element.getProduit().getPrixVenteTTC());
            element.setTotalHt(element.getQuantite() * element.getProduit().getPrixVenteHT());
        }
        return elementPanierRepository.save(element);
    }

    @Override
    public ElementPanier elementPanierExisteDeja(Panier panier, Produit produit, String taille, Long utilisateurId) {
        ElementPanier elementPanier = elementPanierRepository.elementPanierExiste(panier, produit, taille, utilisateurId);
        return elementPanier;
    }

    @Override
    public void supprimerElementPanier(Long utilisateurId, Long elementPnaierId) throws ElementPanierException, UtilisateurException {
        ElementPanier elementPanier = chercherElementPanierParId(elementPnaierId);
        Utilisateur utilisateur = utilisateurService.chercherUtilisateurParIdParticulier(elementPanier.getUtilisateurId());
        Utilisateur utilisateurRequete = utilisateurService.chercherUtilisateurParIdParticulier(utilisateurId);

        if(utilisateur.getId().equals(utilisateurRequete.getId())){
            elementPanierRepository.deleteById(elementPnaierId);
        }else{
            throw new UtilisateurException("Ce n'est pas votre panier !");
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
