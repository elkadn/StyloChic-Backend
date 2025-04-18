package com.styloChic.ecommerce.controllers;
import com.styloChic.ecommerce.dtos.CategorieDTO;
import com.styloChic.ecommerce.exceptions.CategorieException;
import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.models.Categorie;
import com.styloChic.ecommerce.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/styloChic/categories/admin")
public class CategorieAdminController {

    @Autowired
    private CategorieService categorieService;

    @GetMapping
    public ResponseEntity<List<Categorie>> getAllCategories(@RequestHeader("Authorization") String jwt) throws CategorieException {
        List<Categorie> categories = categorieService.chercherTousCategories(jwt);
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/ajouter")
    public ResponseEntity<Categorie> ajouterCategorie(@RequestBody CategorieDTO categorieDTO, @RequestHeader("Authorization") String jwt) throws CategorieException {
        Categorie categorieCree = categorieService.ajouterCategorie(categorieDTO, jwt);
        return ResponseEntity.ok(categorieCree);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategorieParId(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws CategorieException {
        Categorie categorie = categorieService.chercherCategorieParId(id, jwt);
        return ResponseEntity.ok(categorie);
    }

    @PutMapping("/{id}/modifier")
    public ResponseEntity<Categorie> mettreAJourCategorie(@PathVariable Long id, @RequestBody CategorieDTO categorieDTO, @RequestHeader("Authorization") String jwt) throws CategorieException {
        Categorie updated = categorieService.mettreAJourCategorie(id, categorieDTO, jwt);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/supprimer")
    public ResponseEntity<String> supprimerCategorie(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws CategorieException {
        categorieService.supprimerCategorie(id, jwt);
        return new ResponseEntity<>("Catégorie supprimée avec succès !", HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> compterCategories(@RequestHeader("Authorization") String jwt) throws CategorieException {
        long total = categorieService.compterCategories(jwt);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}
