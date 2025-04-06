package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.exceptions.CodeReductionException;
import com.styloChic.ecommerce.exceptions.ElementPanierException;
import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Panier;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.requests.AjouterElementPanierRequest;

public interface PanierService {

    public Panier creerPanier(Utilisateur utilisateur);

    public String ajouterElementPanier(Long utilisateurId, AjouterElementPanierRequest requete) throws ProduitException, ElementPanierException, UtilisateurException;

    public Panier chercherPanierUtilisateur(Long utilisateurId);

    public void viderPanier(Long utilisateurId) throws UtilisateurException;

    public String appliquerCodePromo(Long utilisateurId, String codePromo) throws CodeReductionException;
}
