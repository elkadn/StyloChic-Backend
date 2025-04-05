package com.styloChic.ecommerce.dtos;

import java.time.LocalDateTime;

public class CategorieDTO {

    private String nom;
    private Long categorieParenteId;
    private int niveau;

    public CategorieDTO() {
    }

    public CategorieDTO(String nom, Long categorieParenteId, int niveau) {
        this.nom = nom;
        this.categorieParenteId = categorieParenteId;
        this.niveau = niveau;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getCategorieParenteId() {
        return categorieParenteId;
    }

    public void setCategorieParenteId(Long categorieParenteId) {
        this.categorieParenteId = categorieParenteId;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
}
