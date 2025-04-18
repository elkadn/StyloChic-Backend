package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.dtos.CommandeDTO;
import com.styloChic.ecommerce.exceptions.CommandeException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.AdresseCommande;
import com.styloChic.ecommerce.models.Commande;
import com.styloChic.ecommerce.models.Utilisateur;

import java.time.LocalDateTime;
import java.util.List;

public interface CommandeService {

    public CommandeDTO creerCommande(Utilisateur utilisateur, AdresseCommande adresseCommande) throws UtilisateurException;

    public CommandeDTO chercherCommandeParId(Long commandeId,Long utilisateurId) throws CommandeException,UtilisateurException;

    public CommandeDTO chercherCommandeParIdPourAdmin(Long commandeId,String jwt) throws CommandeException;
    public Commande chercherCommandeParIdParticuliere(Long commandeId) throws CommandeException;


    public List<CommandeDTO> historiqueCommandesUtilisateur(Long utilisateurId) throws CommandeException;

    public CommandeDTO commandePlacee(Long commandeId,String jwt) throws CommandeException, UtilisateurException;

    public CommandeDTO commandeConfirmee(Long commandeId,String jwt) throws CommandeException;

    public CommandeDTO commandeExpediee(Long commandeId,String jwt) throws CommandeException;

    public CommandeDTO commandeLivree(Long commandeId,String jwt) throws CommandeException;

    public CommandeDTO commandeAnnulee(Long commandeId,String jwt) throws CommandeException;

    public List<CommandeDTO> avoirToutesCommandes(String jwt) throws CommandeException;

    public void supprimerCommande(Long commandeId,String jwt) throws CommandeException;

    Commande modifierCommandeavecPaiementEnCash(Long commandeId) throws CommandeException,UtilisateurException;

    public CommandeDTO mettreAJourDateLivraison(Long commandeId, LocalDateTime nouvelleDateLivraison, String jwt) throws CommandeException;

    public long compterCommandes(String jwt) throws CommandeException;
}
