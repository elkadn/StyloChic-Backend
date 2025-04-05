package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.models.Panier;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.requests.AjouterElementPanierRequest;

public interface PanierService {

    public Panier creerPanier(Utilisateur utilisateur);

    public String ajouterElementPanier(Long utilisateurId, AjouterElementPanierRequest requete) throws ProduitException;

    public Panier chercherPanierUtilisateur(Long utilisateurId);

    public void viderPanier(Long utilisateurId);
}
