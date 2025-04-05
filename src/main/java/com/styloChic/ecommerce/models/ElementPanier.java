package com.styloChic.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class ElementPanier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "panier_id")
    private Panier panier;

    @ManyToOne
    private Produit produit;

    private String taille;

    private int quantite;

    @Column(name = "total_ht")
    private Double totalHt;

    @Column(name = "total_ttc")
    private Double totalTTC;

    private Double tva;

    private Long utilisateurId;



    public ElementPanier() {
    }

    public ElementPanier(long id, Panier panier, Produit produit, String taille, int quantite, Double totalHt, Double totalTTC, Double tva,Long utilisateurId) {
        this.id = id;
        this.panier = panier;
        this.produit = produit;
        this.taille = taille;
        this.quantite = quantite;
        this.totalHt = totalHt;
        this.totalTTC = totalTTC;
        this.tva = tva;
        this.utilisateurId = utilisateurId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
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

    public Double getTotalHt() {
        return totalHt;
    }

    public void setTotalHt(Double totalHt) {
        this.totalHt = totalHt;
    }

    public Double getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(Double totalTTC) {
        this.totalTTC = totalTTC;
    }


    public Double getTva() {
        return tva;
    }

    public void setTva(Double tva) {
        this.tva = tva;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
}
