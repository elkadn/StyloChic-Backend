package com.styloChic.ecommerce.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String avis;

    @ManyToOne
    @JoinColumn(name = "id_produit")
    @JsonIgnore
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreation;

    private boolean visibilite;

    public Avis() {
    }

    public Avis(Long id, String avis, Produit produit, Utilisateur utilisateur, LocalDateTime dateCreation, boolean visibilite) {
        this.id = id;
        this.avis = avis;
        this.produit = produit;
        this.utilisateur = utilisateur;
        this.dateCreation = dateCreation;
        this.visibilite = visibilite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public boolean isVisibilite() {
        return visibilite;
    }

    public void setVisibilite(boolean visibilite) {
        this.visibilite = visibilite;
    }
}