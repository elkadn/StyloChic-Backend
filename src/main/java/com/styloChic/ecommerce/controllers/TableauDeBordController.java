package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.exceptions.CategorieException;
import com.styloChic.ecommerce.responses.ProduitBest10Response;
import com.styloChic.ecommerce.responses.ProduitClientResponse;
import com.styloChic.ecommerce.services.CategorieService;
import com.styloChic.ecommerce.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/styloChic/tableauDeBord/admin")
public class TableauDeBordController {


    @Autowired
    private CategorieService categorieService;

    @Autowired
    private ProduitService produitService;


    @GetMapping("/top10Produits")
    public ResponseEntity<List<ProduitBest10Response>> getTop10ProduitsLesPlusVendus() {
        List<ProduitBest10Response> topProduits = produitService.getTop10ProduitsLesPlusVendus();
        return ResponseEntity.ok(topProduits);
    }
    @GetMapping("/venteParCategorie")
    public ResponseEntity<Map<String, Double>> getSalesDistributionByCategory(
            @RequestHeader("Authorization") String jwt) throws CategorieException {

        Map<String, Double> distribution = categorieService.avoirVentePaCategorie(jwt);
        return ResponseEntity.ok(distribution);

    }
}
