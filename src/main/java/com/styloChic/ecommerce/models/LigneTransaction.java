package com.styloChic.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class LigneTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produit produit;

    private String taille;

    private int quantite;

    @Column(name = "prix_ht")
    private Double prixHT;

    @Column(name = "prix_ttc")
    private Double prixTTC;
    private double tva;

    @JsonIgnore
    private Long utilisateurId;

    public LigneTransaction(Long id, Produit produit, String taille, int quantite, Double prixHT, Double prixTTC, double tva, Long utilisateurId) {
        this.id = id;
        this.produit = produit;
        this.taille = taille;
        this.quantite = quantite;
        this.prixHT = prixHT;
        this.prixTTC = prixTTC;
        this.tva = tva;
        this.utilisateurId = utilisateurId;
    }


    public LigneTransaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Double getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(Double prixHT) {
        this.prixHT = prixHT;
    }

    public Double getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(Double prixTTC) {
        this.prixTTC = prixTTC;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
}
