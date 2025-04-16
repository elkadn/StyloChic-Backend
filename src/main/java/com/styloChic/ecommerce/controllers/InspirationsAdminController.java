package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.dtos.InspirationDTO;
import com.styloChic.ecommerce.exceptions.InspirationException;
import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Inspiration;
import com.styloChic.ecommerce.services.InspirationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/styloChic/inspirations")
public class InspirationsAdminController {

    @Autowired
    private InspirationService inspirationService;

    @PostMapping("/ajouter/{produitId}")
    public ResponseEntity<Inspiration> ajouterInspiration(
            @PathVariable Long produitId,
            @RequestParam("image") MultipartFile image,
            @RequestParam("commentaire") String commentaire,
            @RequestHeader("Authorization") String jwt)
     {
        try {
            Inspiration inspiration = inspirationService.ajouterInspiration(produitId, image, commentaire,jwt);
            return ResponseEntity.ok(inspiration);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/admin/visibilite/{inspirationId}/modifier")
    public ResponseEntity<InspirationDTO> changerVisibiliteInspiration(
            @PathVariable Long inspirationId,
            @RequestParam boolean visibilite,
            @RequestHeader("Authorization") String jwt) throws UtilisateurException {
        try {
            InspirationDTO inspirationDTO = inspirationService.changerVisibiliteInspiration(inspirationId, visibilite,jwt);
            return ResponseEntity.ok(inspirationDTO);
        } catch (InspirationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
