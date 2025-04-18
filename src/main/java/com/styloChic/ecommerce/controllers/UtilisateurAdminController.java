package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.dtos.UtilisateurDTO;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.services.UtilisateurService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/styloChic/utilisateurs")
public class UtilisateurAdminController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/profile")
    public ResponseEntity<Utilisateur> obtenirProfileUtilisateur(@RequestHeader("Authorization") String jwt) throws UtilisateurException {
        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);
        return new ResponseEntity<Utilisateur>(utilisateur, HttpStatus.ACCEPTED);
    }




    @GetMapping("/admin")
    public ResponseEntity<List<Utilisateur>> obtenirTousUtilisateurs(@RequestHeader("Authorization") String jwt) throws UtilisateurException {
        List<Utilisateur> utilisateurs = utilisateurService.chercherTousUtilisateurs(jwt);
        return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Utilisateur> chercherUtilisateurParId(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws UtilisateurException {
        Utilisateur utilisateur = utilisateurService.chercherUtilisateurParId(id, jwt);
        return new ResponseEntity<>(utilisateur, HttpStatus.OK);
    }

    @PutMapping("/admin/{id}/roleUtilisateur")
    public ResponseEntity<UtilisateurDTO> miseAjourRoleUtilisateur(@RequestHeader("Authorization") String jwt,@PathVariable Long id, @RequestParam String role) throws UtilisateurException {
        UtilisateurDTO utilisateurAmodifier = utilisateurService.modifierRoleUtilisateur(id, role,jwt);
        return new ResponseEntity<>(utilisateurAmodifier, HttpStatus.OK);
    }

    @PostMapping("/admin/ajouter")
    public ResponseEntity<Utilisateur> creerUtilisateur(@RequestBody Utilisateur utilisateur, @RequestHeader("Authorization") String jwt) throws UtilisateurException {
        Utilisateur utilisateurCree = utilisateurService.creerUtilisateur(utilisateur, jwt);
        return new ResponseEntity<>(utilisateurCree, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/{id}/supprimer")
    public ResponseEntity<String> supprimerUtilisateur(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws UtilisateurException {
        utilisateurService.supprimerUtilisateur(id, jwt);
        return new ResponseEntity<>("Utilisateur supprimé avec succès !", HttpStatus.OK);
    }

    @PutMapping("/admin/{id}/modifier")
    public ResponseEntity<UtilisateurDTO> mettreAJourUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur, @RequestHeader("Authorization") String jwt) throws UtilisateurException {
        UtilisateurDTO utilisateurMisAJour = utilisateurService.mettreAJourUtilisateur(id, utilisateur, jwt);
        return new ResponseEntity<>(utilisateurMisAJour, HttpStatus.OK);
    }

    @GetMapping("/admin/count")
    public ResponseEntity<Long> compterUtilisateurs(@RequestHeader("Authorization") String jwt) throws UtilisateurException {
        long total = utilisateurService.compterUtilisateurs(jwt);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }


}
