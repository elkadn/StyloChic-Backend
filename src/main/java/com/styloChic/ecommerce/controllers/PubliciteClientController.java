package com.styloChic.ecommerce.controllers;


import com.styloChic.ecommerce.models.Publicite;
import com.styloChic.ecommerce.services.PubliciteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publicites")
public class PubliciteClientController {

    @Autowired
    private PubliciteService publiciteService;
    @GetMapping("/")
    public ResponseEntity<?> avoirToutesLesPublicites() {
        return ResponseEntity.ok(publiciteService.avoirToutesLesPublicites());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> avoirPubliciteParId(@PathVariable Long id) {
        Publicite publicite = publiciteService.avoirPubliciteParId(id);
        if (publicite == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publicité introuvable !");
        }
        return ResponseEntity.ok(publicite);
    }
}
