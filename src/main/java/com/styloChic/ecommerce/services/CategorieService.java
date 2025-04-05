package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.dtos.CategorieDTO;
import com.styloChic.ecommerce.exceptions.CategorieException;
import com.styloChic.ecommerce.models.Categorie;
import java.util.List;

public interface CategorieService {

    public List<Categorie> chercherTousCategories(String jwt) throws CategorieException;

    public Categorie ajouterCategorie(CategorieDTO categorieDTO, String jwt) throws CategorieException;

    public Categorie chercherCategorieParId(Long id, String jwt) throws CategorieException;

    public Categorie mettreAJourCategorie(Long id, CategorieDTO categorieDTO, String jwt) throws CategorieException;

    public void supprimerCategorie(Long id, String jwt) throws CategorieException;

    }
