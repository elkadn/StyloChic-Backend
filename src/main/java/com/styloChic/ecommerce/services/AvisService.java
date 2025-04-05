package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.models.Avis;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.requests.AvisRequest;

import java.util.List;

public interface AvisService {
    public Avis creerAvis(AvisRequest req, Utilisateur user) throws ProduitException;

    public List<Avis> avoirTousAvis(Long idProduit) throws ProduitException;

    public Long compterAvis(Long idProduit) throws ProduitException;

    List<Avis> avoirAvisAleatoire(Long idProduit) throws ProduitException;

    public void supprimerAvis(Long idProduit) throws ProduitException;

    public void changerVisibiliteAvis(Long avisId,String jwt,boolean visibilite) throws ProduitException;
}
