package com.styloChic.ecommerce.models;

public class Taille {

    private String nom;
    private int quantiteEnStock;

    public Taille() {
    }

    public Taille(String nom, int quantiteEnStock) {
        this.nom = nom;
        this.quantiteEnStock = quantiteEnStock;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }
}
