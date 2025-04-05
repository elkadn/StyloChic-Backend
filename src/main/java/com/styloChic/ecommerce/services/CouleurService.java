package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.dtos.CouleurDTO;
import com.styloChic.ecommerce.exceptions.CouleurException;
import com.styloChic.ecommerce.models.Couleur;

import java.util.List;

public interface CouleurService {

    List<Couleur> chercherToutesCouleurs(String jwt) throws CouleurException;

    Couleur ajouterCouleur(CouleurDTO couleurDTO, String jwt) throws CouleurException;

    Couleur chercherCouleurParId(Long id, String jwt) throws CouleurException;

    Couleur mettreAJourCouleur(Long id, CouleurDTO couleurDTO, String jwt) throws CouleurException;

    void supprimerCouleur(Long id, String jwt) throws CouleurException;
}
