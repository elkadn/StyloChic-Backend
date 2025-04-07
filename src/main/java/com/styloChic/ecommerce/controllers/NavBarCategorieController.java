package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.dtos.NavBarCategorieDTO;
import com.styloChic.ecommerce.exceptions.CategorieException;
import com.styloChic.ecommerce.models.NavBarCategorie;
import com.styloChic.ecommerce.services.NavBarCategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/navBarCategories")
public class NavBarCategorieController {

    @Autowired
    private NavBarCategorieService navBarCategorieService;

    @GetMapping
    public List<NavBarCategorieDTO> avoirTousNavBarCategories() throws CategorieException {
        return navBarCategorieService.avoirTousNavBarCategories();
    }

    @GetMapping("/{id}")
    public NavBarCategorie avoirNavBarCategorieParId(@PathVariable Long id) throws CategorieException {
        return navBarCategorieService.avoirNavBarCategorieParId(id);
    }

}