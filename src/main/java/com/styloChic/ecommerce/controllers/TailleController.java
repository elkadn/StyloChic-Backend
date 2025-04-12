package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.services.TailleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tailles")
public class TailleController {

    @Autowired
    private TailleService tailleService;

    @GetMapping("/noms")
    public ResponseEntity<List<String>> getDistinctTailleNoms() {
        return ResponseEntity.ok(tailleService.getDistinctTailleNoms());
    }
}