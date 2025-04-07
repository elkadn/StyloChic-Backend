package com.styloChic.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nav_bar_sections")
public class NavBarSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @OneToMany(mappedBy = "navBarSection", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<NavBarElement> navBarElements = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "nav_bar_categorie_id")
    @JsonBackReference
    private NavBarCategorie navBarCategorie;

    @OneToOne
    @JoinColumn(name = "categorie_id")
    @JsonIgnore
    private Categorie categorie;

    public NavBarSection() {
    }

    public NavBarSection(Long id, String nom, List<NavBarElement> navBarElements, NavBarCategorie navBarCategorie,Categorie categorie) {
        this.id = id;
        this.nom = nom;
        this.navBarElements = navBarElements;
        this.navBarCategorie = navBarCategorie;
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

    public List<NavBarElement> getNavBarElements() {
        return navBarElements;
    }

    public void setNavBarElements(List<NavBarElement> navBarElements) {
        this.navBarElements = navBarElements;
    }

    public NavBarCategorie getNavBarCategorie() {
        return navBarCategorie;
    }

    public void setNavBarCategorie(NavBarCategorie navBarCategorie) {
        this.navBarCategorie = navBarCategorie;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}