package com.styloChic.ecommerce.controllers;


import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Panier;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.requests.AjouterElementPanierRequest;
import com.styloChic.ecommerce.responses.ApiResponse;
import com.styloChic.ecommerce.services.PanierService;
import com.styloChic.ecommerce.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/styloChic/panier")
public class PanierController {

    @Autowired
    private PanierService panierService;

    @Autowired
    private UtilisateurService utilisateurService;


    @GetMapping("/")
    private ResponseEntity<Panier> chercherPanierUtilisateur(@RequestHeader("Authorization") String jwt) throws UtilisateurException {
        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);
        Panier panier = panierService.chercherPanierUtilisateur(utilisateur.getId());

        return new ResponseEntity<>(panier, HttpStatus.OK);
    }

    @PutMapping("/ajouter")
    public ResponseEntity<ApiResponse>addItemToCart(@RequestBody AjouterElementPanierRequest requete, @RequestHeader("Authorization") String jwt)
            throws UtilisateurException, ProduitException {
        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);
        panierService.ajouterElementPanier(utilisateur.getId(),requete);

        ApiResponse reponse = new ApiResponse();
        reponse.setMessage("Produti ajouté au panier");
        reponse.setStatus(true);

        return new ResponseEntity<>(reponse,HttpStatus.OK);
    }
}
