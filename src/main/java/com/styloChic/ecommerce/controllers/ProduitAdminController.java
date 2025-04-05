package com.styloChic.ecommerce.controllers;


import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.models.Produit;
import com.styloChic.ecommerce.requests.CreationProduitRequest;
import com.styloChic.ecommerce.responses.ImageProduitAdminResponse;
import com.styloChic.ecommerce.responses.ProduitAdminResponse;
import com.styloChic.ecommerce.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/styloChic/produits/admin")
public class ProduitAdminController {

    @Autowired
    private ProduitService produitService;


    @GetMapping("/{id}")
    public ResponseEntity<ProduitAdminResponse> chercherProduitParId(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws ProduitException{
        ProduitAdminResponse produit = produitService.chercherProduitParIdPourAdmin(id,jwt);
        return ResponseEntity.ok(produit);
    }
    @PostMapping("/ajouter")
    public Produit ajouterProduit(@RequestBody CreationProduitRequest creationProduitRequest,
                                  @RequestHeader("Authorization") String jwt) throws ProduitException {
        return produitService.creerProduit(creationProduitRequest, jwt);
    }


    @DeleteMapping("/{id}/supprimer")
    public ResponseEntity<String> supprimerProduit(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws ProduitException {
        produitService.supprimerProduit(id,jwt);
        return new ResponseEntity<>("Produit supprimé avec succès !", HttpStatus.OK);
    }

    @PostMapping("/{id}/images/ajouter")
    public ResponseEntity<String> ajouterImageProduit(@PathVariable Long id, @RequestParam String image,@RequestHeader("Authorization") String jwt)  throws ProduitException {
        produitService.ajouterImageProduit(id, image,jwt);
        return new ResponseEntity<>("Image ajoutée avec succès", HttpStatus.CREATED);

    }

    @PutMapping("/{id}/modifier")
    public ResponseEntity<Produit> modifierProduit(@PathVariable Long id,
                                                   @RequestBody CreationProduitRequest creationProduitRequest,
                                                   @RequestHeader("Authorization") String jwt) throws ProduitException {
        Produit produitMisAJour = produitService.miseAjourProduit(id, creationProduitRequest,jwt);
        return new ResponseEntity<>(produitMisAJour, HttpStatus.OK);
    }

    @DeleteMapping("/{idProduit}/images/{imageId}")
    public ResponseEntity<String> supprimerImageProduit(@PathVariable Long idProduit, @PathVariable Long imageId,@RequestHeader("Authorization") String jwt) throws ProduitException {
        produitService.supprimerImageProduit(idProduit, imageId,jwt);
        return new ResponseEntity<>("Image supprimée avec succès", HttpStatus.OK);
    }

    @PutMapping("/{idProduit}/images/{imageId}")
    public ResponseEntity<String> modifierImageProduit(@PathVariable Long idProduit,
                                                            @PathVariable Long imageId,
                                                            @RequestParam String nouvelleImage,
                                                            @RequestHeader("Authorization") String jwt) throws ProduitException{
        produitService.modifierImageProduit(idProduit, imageId, nouvelleImage,jwt);
        return new ResponseEntity<>("Image modifiée avec succès", HttpStatus.OK);
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<List<ImageProduitAdminResponse>> obtenirImagesProduitPourAdmin(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws ProduitException {
        List<ImageProduitAdminResponse> images = produitService.obtenirImagesProduitPourAdmin(id,jwt);
        return ResponseEntity.ok(images);
    }


    @GetMapping("/{idProduit}/images/{imageId}")
    public ResponseEntity<ImageProduitAdminResponse> avoirImageProduitAvecIdPourAdmin(@PathVariable Long idProduit,
                                                                                      @PathVariable Long imageId,
                                                                                      @RequestHeader("Authorization") String jwt) throws ProduitException {
        ImageProduitAdminResponse response = produitService.avoirImageProduitAvecIdPourAdmin(idProduit, imageId,jwt);
        return ResponseEntity.ok(response);
    }


}
