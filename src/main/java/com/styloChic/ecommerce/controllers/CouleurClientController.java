package com.styloChic.ecommerce.controllers;


import com.styloChic.ecommerce.repositories.CouleurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/couleurs")
public class CouleurClientController {

    @Autowired
    private CouleurRepository couleurRepository;

    @GetMapping("/noms")
    public List<String> obtenirNomsCouleurs() {
        return couleurRepository.findAllNoms();
    }
}
