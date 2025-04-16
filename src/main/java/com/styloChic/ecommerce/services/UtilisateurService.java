package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.dtos.UtilisateurDTO;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    public Utilisateur chercherUtilisateurParIdParticulier(Long utilisateurId) throws UtilisateurException;

    public Utilisateur chercherUtilisateurParId(Long utilisateurId, String jwt) throws UtilisateurException;

    public Utilisateur chercherProfileUtilisateurParJwt(String jwt) throws UtilisateurException;

    public List<Utilisateur> chercherTousUtilisateurs(String jwt) throws UtilisateurException;

    public UtilisateurDTO modifierRoleUtilisateur(Long id, String role,String jwt) throws UtilisateurException;

    public Utilisateur creerUtilisateur(Utilisateur utilisateur, String jwt) throws  UtilisateurException;

    public void supprimerUtilisateur(Long id,String jwt) throws UtilisateurException;

    public UtilisateurDTO mettreAJourUtilisateur(Long id, Utilisateur utilisateur, String jwt) throws UtilisateurException;

    public void changerMotDePasse(Long utilisateurId, String nouveauMotDePasse, String jwt) throws UtilisateurException;

}


