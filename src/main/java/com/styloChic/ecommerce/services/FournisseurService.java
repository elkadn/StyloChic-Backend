package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.dtos.FournisseurDTO;
import com.styloChic.ecommerce.exceptions.FournisseurException;
import com.styloChic.ecommerce.models.Fournisseur;

import java.util.List;

public interface FournisseurService {

    public List<Fournisseur> chercherTousFournisseurs(String jwt) throws FournisseurException;

    public Fournisseur ajouterFournisseur(FournisseurDTO fournisseurDTO, String jwt) throws FournisseurException;

    public Fournisseur chercherFournisseurParId(Long id, String jwt) throws FournisseurException;

    public Fournisseur mettreAJourFournisseur(Long id, FournisseurDTO fournisseurDTO, String jwt) throws FournisseurException;

    public void supprimerFournisseur(Long id, String jwt) throws FournisseurException;
}
