package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.dtos.PubliciteDTO;
import com.styloChic.ecommerce.exceptions.PubiliciteException;
import com.styloChic.ecommerce.models.Publicite;
import com.styloChic.ecommerce.services.PubliciteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/styloChic/publicites/admin")
public class PubliciteAdminController {

    @Autowired
    private PubliciteService publiciteService;


    @PostMapping("/ajouter")
    public ResponseEntity<?> addPublicite(@RequestBody PubliciteDTO publicite, @RequestHeader("Authorization") String jwt) {
        try {
            PubliciteDTO createdPublicite = publiciteService.ajouterPublicite(publicite, jwt);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPublicite);
        } catch (PubiliciteException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/modifier")
    public ResponseEntity<?> updatePublicite(@PathVariable Long id, @RequestBody PubliciteDTO publicite, @RequestHeader("Authorization") String jwt) {
        try {
            PubliciteDTO updatedPublicite = publiciteService.modifierPublicite(id, publicite, jwt);
            return ResponseEntity.ok(updatedPublicite);
        } catch (PubiliciteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/supprimer")
    public ResponseEntity<String> deletePublicite(@PathVariable Long id, @RequestHeader("Authorization") String jwt) {
        try {
            publiciteService.supprimerPublicite(id, jwt);
            return new ResponseEntity<>("Publication supprimée avec succès",HttpStatus.OK);
        } catch (PubiliciteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
