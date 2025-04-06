package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.dtos.QuantiteDTO;
import com.styloChic.ecommerce.exceptions.ElementPanierException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.ElementPanier;
import com.styloChic.ecommerce.models.Panier;
import com.styloChic.ecommerce.models.Produit;

public interface ElementPanierService {

    public ElementPanier creerElementPanier(ElementPanier elementPanier);

    public ElementPanier modifierElementPanier(Long utilisateurId, Long id, QuantiteDTO quantiteDTO) throws ElementPanierException, UtilisateurException;

    public ElementPanier elementPanierExisteDeja(Panier panier, Produit produit, String taille, Long utilisateurId);

    public void supprimerElementPanier(Long utilisateurId,Long elementPanierId) throws ElementPanierException,UtilisateurException;

    public ElementPanier chercherElementPanierParId(Long elementPanierId) throws ElementPanierException;
}
