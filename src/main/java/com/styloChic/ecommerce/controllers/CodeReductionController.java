package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.dtos.CodeReductionDTO;
import com.styloChic.ecommerce.exceptions.CategorieException;
import com.styloChic.ecommerce.exceptions.CodeReductionException;
import com.styloChic.ecommerce.models.CodeReduction;
import com.styloChic.ecommerce.services.CodeReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/styloChic/codesReduction")
public class CodeReductionController {

    @Autowired
    private CodeReductionService codeReductionService;

    @GetMapping("/admin")
    public ResponseEntity<List<CodeReduction>> getAllCodesPromo(@RequestHeader("Authorization") String jwt) throws CodeReductionException {
        List<CodeReduction> codesPromo = codeReductionService.chercherTousCodesPromo(jwt);
        return ResponseEntity.ok(codesPromo);
    }

    @PostMapping("/admin/ajouter")
    public ResponseEntity<CodeReduction> ajouterCodePromo(@RequestBody CodeReductionDTO codeReductionDTO, @RequestHeader("Authorization") String jwt) throws CodeReductionException {
        CodeReduction codePromoCree = codeReductionService.ajouterCodePromo(codeReductionDTO, jwt);
        return ResponseEntity.ok(codePromoCree);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<CodeReduction> getCodePromoParId(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws CodeReductionException {
        CodeReduction codePromo = codeReductionService.chercherCodePromoParId(id, jwt);
        return ResponseEntity.ok(codePromo);
    }

    @PutMapping("/admin/{id}/modifier")
    public ResponseEntity<CodeReduction> mettreAJourCodePromo(@PathVariable Long id, @RequestBody CodeReductionDTO codeReductionDTO, @RequestHeader("Authorization") String jwt) throws CodeReductionException {
        CodeReduction updated = codeReductionService.mettreAJourCodePromo(id, codeReductionDTO, jwt);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}/supprimer")
    public ResponseEntity<String> supprimerCodePromo(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws CodeReductionException {
        codeReductionService.supprimerCodePromo(id, jwt);
        return new ResponseEntity<>("Code promo supprimé avec succès !", HttpStatus.OK);
    }

    @GetMapping("/admin/count")
    public ResponseEntity<Long> compterCodePromos(@RequestHeader("Authorization") String jwt) throws CodeReductionException {
        long total = codeReductionService.compterCodePromos(jwt);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}
