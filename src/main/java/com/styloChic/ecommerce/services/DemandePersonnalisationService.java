package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.dtos.DemandePersonnalisationDTO;
import com.styloChic.ecommerce.exceptions.DemandePersonnalisationException;
import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.requests.DemandePersonnalisationRequest;

import java.util.List;

public interface DemandePersonnalisationService {

    public DemandePersonnalisationDTO creerDemandePersonnalisation(DemandePersonnalisationRequest demandeDTO, String jwt) throws ProduitException, UtilisateurException;


    List<DemandePersonnalisationDTO> recupererToutesDemandesPersonnalisation();

    DemandePersonnalisationDTO ajouterReponseAdmin(Long demandeId, String reponse, String statut, String jwt)
            throws UtilisateurException, ProduitException, DemandePersonnalisationException;

    public List<DemandePersonnalisationDTO> recupererDemandesPersonnalisationParUtilisateur(String jwt) throws UtilisateurException ;
}
