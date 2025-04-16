package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.dtos.DemandePersonnalisationDTO;
import com.styloChic.ecommerce.exceptions.DemandePersonnalisationException;
import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.requests.DemandePersonnalisationRequest;
import com.styloChic.ecommerce.services.DemandePersonnalisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/styloChic/demandesPesonnalisation")
public class DemandePersonnalisationController {

    @Autowired
    private DemandePersonnalisationService demandeService;

    @PostMapping("/ajouter")
    public ResponseEntity<DemandePersonnalisationDTO> creerDemande(
            @RequestBody DemandePersonnalisationRequest demandeDTO,
            @RequestHeader("Authorization") String jwt) throws ProduitException, UtilisateurException {

        DemandePersonnalisationDTO demande = demandeService.creerDemandePersonnalisation(demandeDTO, jwt);
        return ResponseEntity.ok(demande);
    }

    @GetMapping("/")
    public ResponseEntity<List<DemandePersonnalisationDTO>> recupererToutesDemandes() {
        List<DemandePersonnalisationDTO> demandes = demandeService.recupererToutesDemandesPersonnalisation();
        return ResponseEntity.ok(demandes);
    }

    @PutMapping("/{demandeId}/reponse")
    public ResponseEntity<DemandePersonnalisationDTO> ajouterReponse(
            @PathVariable Long demandeId,
            @RequestParam String reponse,
            @RequestParam String statut,
            @RequestHeader("Authorization") String jwt) throws UtilisateurException, ProduitException, DemandePersonnalisationException {

        DemandePersonnalisationDTO demande = demandeService.ajouterReponseAdmin(
                demandeId, reponse, statut, jwt);
        return ResponseEntity.ok(demande);
    }

    @GetMapping("/utilisateur")
    public ResponseEntity<List<DemandePersonnalisationDTO>> recupererDemandesUtilisateur(
            @RequestHeader("Authorization") String jwt) throws UtilisateurException {

        List<DemandePersonnalisationDTO> demandes = demandeService.recupererDemandesPersonnalisationParUtilisateur(jwt);
        return ResponseEntity.ok(demandes);
    }
}
