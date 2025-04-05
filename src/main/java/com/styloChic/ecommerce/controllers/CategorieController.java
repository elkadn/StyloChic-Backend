package com.styloChic.ecommerce.controllers;
import com.styloChic.ecommerce.dtos.CategorieDTO;
import com.styloChic.ecommerce.exceptions.CategorieException;
import com.styloChic.ecommerce.models.Categorie;
import com.styloChic.ecommerce.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/styloChic/categories")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @GetMapping("/admin")
    public ResponseEntity<List<Categorie>> getAllCategories(@RequestHeader("Authorization") String jwt) throws CategorieException {
        List<Categorie> categories = categorieService.chercherTousCategories(jwt);
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/admin/ajouter")
    public ResponseEntity<Categorie> ajouterCategorie(@RequestBody CategorieDTO categorieDTO, @RequestHeader("Authorization") String jwt) throws CategorieException {
        Categorie categorieCree = categorieService.ajouterCategorie(categorieDTO, jwt);
        return ResponseEntity.ok(categorieCree);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Categorie> getCategorieParId(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws CategorieException {
        Categorie categorie = categorieService.chercherCategorieParId(id, jwt);
        return ResponseEntity.ok(categorie);
    }

    @PutMapping("/admin/{id}/modifier")
    public ResponseEntity<Categorie> mettreAJourCategorie(@PathVariable Long id, @RequestBody CategorieDTO categorieDTO, @RequestHeader("Authorization") String jwt) throws CategorieException {
        Categorie updated = categorieService.mettreAJourCategorie(id, categorieDTO, jwt);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}/supprimer")
    public ResponseEntity<String> supprimerCategorie(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws CategorieException {
        categorieService.supprimerCategorie(id, jwt);
        return new ResponseEntity<>("Catégorie supprimée avec succès !", HttpStatus.OK);
    }
}
