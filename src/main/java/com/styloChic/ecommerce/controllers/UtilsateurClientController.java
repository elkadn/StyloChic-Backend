package com.styloChic.ecommerce.controllers;


import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utilisateurs")
public class UtilsateurClientController {

    @Autowired
    private UtilisateurService utilisateurService;
    @GetMapping("/admin/{id}")
    public ResponseEntity<Utilisateur> chercherUtilisateurParIdParticulier(@PathVariable Long id) throws UtilisateurException {
        Utilisateur utilisateur = utilisateurService.chercherUtilisateurParIdParticulier(id);
        return new ResponseEntity<>(utilisateur, HttpStatus.OK);
    }
}
