package com.styloChic.ecommerce.controllers;


import com.styloChic.ecommerce.dtos.FournisseurDTO;
import com.styloChic.ecommerce.exceptions.FournisseurException;
import com.styloChic.ecommerce.models.Fournisseur;
import com.styloChic.ecommerce.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/styloChic/fournisseurs/admin")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping("/")
    public ResponseEntity<List<Fournisseur>> getAllFournisseurs(@RequestHeader("Authorization") String jwt) throws FournisseurException {
        List<Fournisseur> fournisseurs = fournisseurService.chercherTousFournisseurs(jwt);
        return ResponseEntity.ok(fournisseurs);
    }

    @PostMapping("/ajouter")
    public ResponseEntity<Fournisseur> ajouterFournisseur(@RequestBody FournisseurDTO fournisseurDTO, @RequestHeader("Authorization") String jwt) throws FournisseurException {
        Fournisseur fournisseurCree = fournisseurService.ajouterFournisseur(fournisseurDTO, jwt);
        return ResponseEntity.ok(fournisseurCree);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> getFournisseurParId(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws FournisseurException {
        Fournisseur fournisseur = fournisseurService.chercherFournisseurParId(id, jwt);
        return ResponseEntity.ok(fournisseur);
    }

    @PutMapping("/{id}/modifier")
    public ResponseEntity<Fournisseur> mettreAJourFournisseur(@PathVariable Long id, @RequestBody FournisseurDTO fournisseurDTO, @RequestHeader("Authorization") String jwt) throws FournisseurException {
        Fournisseur updated = fournisseurService.mettreAJourFournisseur(id, fournisseurDTO, jwt);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/supprimer")
    public ResponseEntity<String> supprimerFournisseur(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws FournisseurException {
        fournisseurService.supprimerFournisseur(id, jwt);
        return new ResponseEntity<>("Fournisseur supprimé avec succès !", HttpStatus.OK);
    }
}