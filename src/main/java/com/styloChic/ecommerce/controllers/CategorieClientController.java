package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategorieClientController {

    @Autowired
    private CategorieService categorieService;
    @GetMapping("/niveau3/noms")
    public ResponseEntity<List<String>> getNomsCategoriesNiveau3() {
        List<String> noms = categorieService.getNomsCategoriesNiveau3();
        return ResponseEntity.ok(noms);
    }

}
