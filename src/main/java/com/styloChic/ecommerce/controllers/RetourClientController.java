package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.config.JwtProvider;
import com.styloChic.ecommerce.dtos.RetourClientDTO;
import com.styloChic.ecommerce.exceptions.CommandeException;
import com.styloChic.ecommerce.exceptions.RetourException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.requests.DemandeRetourRequest;
import com.styloChic.ecommerce.services.RetourClientService;
import com.styloChic.ecommerce.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/styloChic/retours")
public class RetourClientController {

    @Autowired
    private RetourClientService retourClientService;

    @Autowired
    private UtilisateurService utilisateurService;



    @PostMapping("/")
    public ResponseEntity<RetourClientDTO> demanderRetour(
            @RequestBody DemandeRetourRequest request,
            @RequestHeader("Authorization") String jwt) throws CommandeException, UtilisateurException {

        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);

        RetourClientDTO retour = retourClientService.demanderRetour(
                request.getCommandeId(),
                utilisateur,
                request.getLignesRetour(),
                request.getMotif(),
                request.getCommentaire());

        return new ResponseEntity<>(retour, HttpStatus.CREATED);
    }

    @PutMapping("/{retourId}/traiter")
    public ResponseEntity<RetourClientDTO> traiterRetour(
            @PathVariable Long retourId,
            @RequestParam String statut,
            @RequestParam String mode,
            @RequestParam String reponse,
            @RequestHeader("Authorization") String jwt) throws RetourException {

        RetourClientDTO retour = retourClientService.traiterRetour(retourId, statut, jwt, mode,reponse);
        return ResponseEntity.ok(retour);
    }

    @GetMapping("/utilisateur")
    public ResponseEntity<List<RetourClientDTO>> getRetoursUtilisateur(
            @RequestHeader("Authorization") String jwt) throws UtilisateurException {

        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);

        List<RetourClientDTO> retours = retourClientService.getRetoursParUtilisateur(utilisateur.getId());
        return ResponseEntity.ok(retours);
    }

    @GetMapping
    public ResponseEntity<List<RetourClientDTO>> getTousRetours(
            @RequestHeader("Authorization") String jwt) throws RetourException {

        List<RetourClientDTO> retours = retourClientService.getTousRetours(jwt);
        return ResponseEntity.ok(retours);
    }

    @GetMapping("/commande/{commandeId}")
    public ResponseEntity<List<RetourClientDTO>> getRetoursParCommande(
            @PathVariable Long commandeId,
            @RequestHeader("Authorization") String jwt) throws UtilisateurException, CommandeException {

        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);

        List<RetourClientDTO> retours = retourClientService.getRetoursParCommande(commandeId, utilisateur.getId());
        return ResponseEntity.ok(retours);
    }
}