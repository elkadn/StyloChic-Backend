package com.styloChic.ecommerce.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nav_bar_categories")
public class NavBarCategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @OneToMany(mappedBy = "navBarCategorie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OffreSpeciale> offresSpeciales = new ArrayList<>();


    @OneToMany(mappedBy = "navBarCategorie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<NavBarSection> navBarSections = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "categorie_id")
    @JsonIgnore
    private Categorie categorie;

    public NavBarCategorie() {
    }

    public NavBarCategorie(Long id, String nom, List<OffreSpeciale> offresSpeciales, List<NavBarSection> navBarSections,Categorie categorie) {
        this.id = id;
        this.nom = nom;
        this.offresSpeciales = offresSpeciales;
        this.navBarSections = navBarSections;
        this.categorie = categorie;
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

    public List<OffreSpeciale> getOffresSpeciales() {
        return offresSpeciales;
    }

    public void setOffresSpeciales(List<OffreSpeciale> offresSpeciales) {
        this.offresSpeciales = offresSpeciales;
    }

    public List<NavBarSection> getNavBarSections() {
        return navBarSections;
    }

    public void setNavBarSections(List<NavBarSection> navBarSections) {
        this.navBarSections = navBarSections;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}