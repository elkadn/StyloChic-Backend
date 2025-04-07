package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.models.Inspiration;
import com.styloChic.ecommerce.services.InspirationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/styloChic/inspirations")
public class InspirationController {

    @Autowired
    private InspirationService inspirationService;

    @PostMapping("/ajouter/{produitId}")
    public ResponseEntity<Inspiration> ajouterInspiration(
            @PathVariable Long produitId,
            @RequestParam("image") MultipartFile image,
            @RequestParam("commentaire") String commentaire) {
        try {
            Inspiration inspiration = inspirationService.ajouterInspiration(produitId, image, commentaire);
            return ResponseEntity.ok(inspiration);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
