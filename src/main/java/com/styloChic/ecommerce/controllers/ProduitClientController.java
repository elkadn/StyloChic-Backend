package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.responses.ImageProduitClientResponse;
import com.styloChic.ecommerce.responses.ProduitClientResponse;
import com.styloChic.ecommerce.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produits")
public class ProduitClientController {
    @Autowired
    private ProduitService produitService;

    @GetMapping
    public ResponseEntity<Page<ProduitClientResponse>> chercherProduitParCategorie(@RequestParam String categorie,
                                                                      @RequestParam List<String> couleur,
                                                                      @RequestParam List<String> taille,
                                                                      @RequestParam Integer prixMin,
                                                                      @RequestParam Integer prixMax,
                                                                      @RequestParam String trie,
                                                                      @RequestParam String stock,
                                                                      @RequestParam Integer numPage,
                                                                      @RequestParam Integer taillePage) {

        Page<ProduitClientResponse> resultat = produitService.avoirTousProduits(categorie,couleur,taille,prixMin,prixMax,trie,stock,numPage,taillePage);
        return new ResponseEntity<>(resultat, HttpStatus.ACCEPTED);
    }

    @GetMapping("/rechercher")
    public ResponseEntity<Page<ProduitClientResponse>> chercherProduits(@RequestParam String requete,
                                                          @RequestParam Integer numPage,
                                                          @RequestParam Integer taillePage) {
        Page<ProduitClientResponse> resultat = produitService.chercherProduits(requete, numPage, taillePage);
        return new ResponseEntity<>(resultat, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitClientResponse> chercherProduitParId(@PathVariable Long id) throws ProduitException {
        ProduitClientResponse produit = produitService.chercherProduitParIdPourClient(id);
        return ResponseEntity.ok(produit);
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<List<ImageProduitClientResponse>> obtenirImagesProduitPourClient(@PathVariable Long id) throws ProduitException {
        List<ImageProduitClientResponse> images = produitService.obtenirImagesProduitPourClient(id);
        return ResponseEntity.ok(images);
    }



    @GetMapping("/{idProduit}/images/{imageId}")
    public ResponseEntity<ImageProduitClientResponse> avoirImageProduitAvecIdPourClient(@PathVariable Long idProduit,
                                                                                      @PathVariable Long imageId) throws ProduitException {
        ImageProduitClientResponse response = produitService.avoirImageProduitAvecIdPourClient(idProduit, imageId);
        return ResponseEntity.ok(response);
    }
}
