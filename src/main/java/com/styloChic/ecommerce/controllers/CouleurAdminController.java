package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.dtos.CouleurDTO;
import com.styloChic.ecommerce.exceptions.CouleurException;
import com.styloChic.ecommerce.models.Couleur;
import com.styloChic.ecommerce.services.CouleurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/styloChic/couleurs/admin")
public class CouleurAdminController {

    @Autowired
    private CouleurService couleurService;



    @GetMapping
    public List<Couleur> avoirToutesCouleurs(@RequestHeader("Authorization") String jwt) throws CouleurException {
        return couleurService.chercherToutesCouleurs(jwt);
    }

    @PostMapping("/ajouter")
    public Couleur ajouterCouleur(@RequestBody CouleurDTO couleurDTO,
                                  @RequestHeader("Authorization") String jwt) throws CouleurException {
        return couleurService.ajouterCouleur(couleurDTO, jwt);
    }

    @GetMapping("/{id}")
    public Couleur getCouleurParId(@PathVariable Long id,
                                   @RequestHeader("Authorization") String jwt) throws CouleurException {
        return couleurService.chercherCouleurParId(id, jwt);
    }

    @PutMapping("/{id}/modifier")
    public Couleur mettreAJourCouleur(@PathVariable Long id,
                                      @RequestBody CouleurDTO couleurDTO,
                                      @RequestHeader("Authorization") String jwt) throws CouleurException {
        return couleurService.mettreAJourCouleur(id, couleurDTO, jwt);
    }

    @DeleteMapping("/{id}/supprimer")
    public ResponseEntity<String> supprimerCouleur(@PathVariable Long id,
                                 @RequestHeader("Authorization") String jwt) throws CouleurException {
        couleurService.supprimerCouleur(id, jwt);
        return new ResponseEntity<>("Couleur supprimée avec succès !", HttpStatus.OK);
    }
}
