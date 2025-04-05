package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Avis;
import com.styloChic.ecommerce.services.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avis")
public class AvisClientController {

    @Autowired
    private AvisService avisService;


    @GetMapping("/produit/{idProduit}")
    public ResponseEntity<List<Avis>> avoirAvisProduit(@PathVariable Long idProduit) throws UtilisateurException, ProduitException{
        List<Avis> avis = avisService.avoirTousAvis(idProduit);
        return new ResponseEntity<>(avis,HttpStatus.ACCEPTED);
    }

    @GetMapping("/produit/totalAvis/{idProduit}")
    public ResponseEntity<Long> compterAvisProduit(@PathVariable Long idProduit) throws ProduitException{
        Long total = avisService.compterAvis(idProduit);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    @GetMapping("/produit/aleatoire/{idProduit}")
    public ResponseEntity<List<Avis>> avoirAvisAleatoireProduit(@PathVariable Long idProduit) throws ProduitException {
        List<Avis> avis = avisService.avoirAvisAleatoire(idProduit);
        return new ResponseEntity<>(avis, HttpStatus.OK);
    }


}
