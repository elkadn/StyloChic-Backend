package com.styloChic.ecommerce.controllers;


import com.styloChic.ecommerce.dtos.OffreSpecialeDTO;
import com.styloChic.ecommerce.exceptions.CategorieException;
import com.styloChic.ecommerce.exceptions.OffreSpecialeException;
import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.models.OffreSpeciale;
import com.styloChic.ecommerce.services.OffreSpecialeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/styloChic/offresSpeciales/admin")
public class OffreSpecialeAdminController {

    @Autowired
    private OffreSpecialeService offreSpecialeService;
    @GetMapping("/")
    public ResponseEntity<List<OffreSpecialeDTO>> getToutesOffres(@RequestHeader("Authorization") String jwt) throws OffreSpecialeException {
        List<OffreSpecialeDTO> offres = offreSpecialeService.chercherToutesOffres(jwt);
        return ResponseEntity.ok(offres);
    }

    @PostMapping("/ajouter")
    public ResponseEntity<OffreSpecialeDTO> ajouterOffre(@RequestBody OffreSpecialeDTO offreDTO,
                                                      @RequestHeader("Authorization") String jwt) throws OffreSpecialeException,ProduitException, CategorieException {
        OffreSpecialeDTO offre = offreSpecialeService.ajouterOffreSpeciale(offreDTO, jwt);
        return ResponseEntity.ok(offre);
    }

    @PutMapping("/{id}/modifier")
    public ResponseEntity<OffreSpecialeDTO> modifierOffre(@PathVariable Long id,
                                                       @RequestBody OffreSpecialeDTO offreDTO,
                                                       @RequestHeader("Authorization") String jwt) throws OffreSpecialeException, ProduitException,CategorieException {
        OffreSpecialeDTO offre = offreSpecialeService.modifierOffreSpeciale(id, offreDTO, jwt);
        return ResponseEntity.ok(offre);
    }

    @DeleteMapping("/{id}/supprimer")
    public ResponseEntity<String> supprimerOffre(@PathVariable Long id,
                                               @RequestHeader("Authorization") String jwt) throws OffreSpecialeException{
        offreSpecialeService.supprimerOffreSpeciale(id, jwt);
        return new ResponseEntity<>("Offre spéciale supprimée avec succès", HttpStatus.OK);
    }
}
