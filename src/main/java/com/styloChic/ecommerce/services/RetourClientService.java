package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.dtos.LigneRetourDTO;
import com.styloChic.ecommerce.dtos.RetourClientDTO;
import com.styloChic.ecommerce.exceptions.CommandeException;
import com.styloChic.ecommerce.exceptions.RetourException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Utilisateur;

import java.util.List;

public interface RetourClientService {

    public RetourClientDTO traiterRetour(Long retourId, String statut, String jwt, String modeRetour,String commentaireAdmin) throws RetourException;

        public RetourClientDTO demanderRetour(Long commandeId, Utilisateur utilisateur, List<LigneRetourDTO> lignesRetourDTO, String motif, String commentaire) throws CommandeException;

    public List<RetourClientDTO> getRetoursParUtilisateur(Long utilisateurId);

    public List<RetourClientDTO> getTousRetours(String jwt) throws RetourException ;

    List<RetourClientDTO> getRetoursParCommande(Long commandeId, Long utilisateurId) throws CommandeException;

}
