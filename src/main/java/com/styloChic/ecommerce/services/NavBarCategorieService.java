package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.dtos.NavBarCategorieDTO;
import com.styloChic.ecommerce.exceptions.CategorieException;
import com.styloChic.ecommerce.models.NavBarCategorie;

import java.util.List;

public interface NavBarCategorieService {

    public List<NavBarCategorieDTO> avoirTousNavBarCategories() throws CategorieException;

    public NavBarCategorie avoirNavBarCategorieParId(Long id) throws CategorieException;

    public NavBarCategorieDTO avoirNavBarCategorieParIdParticulier(Long id) throws CategorieException;
}
