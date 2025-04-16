package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.dtos.InspirationDTO;
import com.styloChic.ecommerce.services.InspirationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inspirations")
public class InspirationsClientController {

    @Autowired
    private InspirationService inspirationService;

    @GetMapping("/")
    public ResponseEntity<List<InspirationDTO>> recupererToutesInspirations() {
        List<InspirationDTO> inspirations = inspirationService.recupererToutesInspirationsAvecTitreProduit();
        return ResponseEntity.ok(inspirations);
    }
}
