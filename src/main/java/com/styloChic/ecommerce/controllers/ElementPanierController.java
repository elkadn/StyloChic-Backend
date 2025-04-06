package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.dtos.QuantiteDTO;
import com.styloChic.ecommerce.exceptions.ElementPanierException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.ElementPanier;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.responses.ApiResponse;
import com.styloChic.ecommerce.services.ElementPanierService;
import com.styloChic.ecommerce.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/styloChic/elementPanier")
public class ElementPanierController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ElementPanierService elementPanierService;


    @DeleteMapping("/{elementPanierId}")
    public ResponseEntity<ApiResponse> supprimerElementPanier(@PathVariable Long elementPanierId,
                                                              @RequestHeader("Authorization") String jwt) throws UtilisateurException, ElementPanierException {

        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);
        elementPanierService.supprimerElementPanier(utilisateur.getId(),elementPanierId);

        ApiResponse reponse = new ApiResponse();
        reponse.setMessage("Produit retiré du panier !");
        reponse.setStatus(true);

        return new ResponseEntity<>(reponse, HttpStatus.OK);

    }

    @PutMapping("/{elementPanierId}")
    public ResponseEntity<ElementPanier> modifierElementPanier(@RequestBody QuantiteDTO quantiteDTO, @PathVariable Long elementPanierId, @RequestHeader("Authorization") String jwt)
            throws UtilisateurException,ElementPanierException{
        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);
        ElementPanier elementPanierModifie = elementPanierService.modifierElementPanier(utilisateur.getId(),elementPanierId,quantiteDTO);

        return new ResponseEntity<>(elementPanierModifie,HttpStatus.OK);
    }
}
