package com.styloChic.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class OffreSpeciale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String urlImage;
    private String descriptionImage;
    private String lien;

    @JsonIgnore
    private boolean visibilite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    @JsonIgnore
    private Utilisateur admin;

    @JsonIgnore
    private LocalDateTime dateAjout;
    @JsonIgnore
    private LocalDateTime dateModification;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_id")
    private Produit produit;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "nav_bar_categorie_id")
    private NavBarCategorie navBarCategorie;


    public OffreSpeciale() {
    }

    public OffreSpeciale(Long id, String nom, String urlImage, String descriptionImage, String lien, boolean visibilite, Utilisateur admin, LocalDateTime dateAjout, LocalDateTime dateModification, Produit produit, NavBarCategorie navBarCategorie) {
        this.id = id;
        this.nom = nom;
        this.urlImage = urlImage;
        this.descriptionImage = descriptionImage;
        this.lien = lien;
        this.visibilite = visibilite;
        this.admin = admin;
        this.dateAjout = dateAjout;
        this.dateModification = dateModification;
        this.produit = produit;
        this.navBarCategorie = navBarCategorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDescriptionImage() {
        return descriptionImage;
    }

    public void setDescriptionImage(String descriptionImage) {
        this.descriptionImage = descriptionImage;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public boolean isVisibilite() {
        return visibilite;
    }

    public void setVisibilite(boolean visibilite) {
        this.visibilite = visibilite;
    }

    public Utilisateur getAdmin() {
        return admin;
    }

    public void setAdmin(Utilisateur admin) {
        this.admin = admin;
    }

    public LocalDateTime getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public NavBarCategorie getNavBarCategorie() {
        return navBarCategorie;
    }

    public void setNavBarCategorie(NavBarCategorie navBarCategorie) {
        this.navBarCategorie = navBarCategorie;
    }
}
