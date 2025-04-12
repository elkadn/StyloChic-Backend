package com.styloChic.ecommerce.dtos;

public class TailleDTO {
    private String nom;

    public TailleDTO(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}