package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.dtos.CommandeDTO;
import com.styloChic.ecommerce.exceptions.CommandeException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.AdresseCommande;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.services.CommandeService;
import com.styloChic.ecommerce.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/styloChic/commandes")
public class CommandeClientController {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/ajouter")
    public ResponseEntity<CommandeDTO> creerCommande(@RequestBody AdresseCommande adresseCommande, @RequestHeader("Authorization") String jwt) throws UtilisateurException {
        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);
        CommandeDTO commandeDTO = commandeService.creerCommande(utilisateur,adresseCommande);

        return new ResponseEntity<>(commandeDTO, HttpStatus.CREATED);
    }

    @GetMapping("/utilisateur")
    public ResponseEntity<List<CommandeDTO>> historiqueCommandeUtilisateur(@RequestHeader("Authorization") String jwt) throws UtilisateurException,CommandeException{
        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);

        List<CommandeDTO> commandeDTOS = commandeService.historiqueCommandesUtilisateur(utilisateur.getId());
        return  new ResponseEntity<>(commandeDTOS,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeDTO> chercherCommandeParId(@PathVariable("id") Long commandeId,
                                               @RequestHeader("Authorization") String jwt) throws UtilisateurException, CommandeException {
        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);

        CommandeDTO commandeDTO = commandeService.chercherCommandeParId(commandeId,utilisateur.getId());

        return new ResponseEntity<>(commandeDTO,HttpStatus.CREATED);
    }


}