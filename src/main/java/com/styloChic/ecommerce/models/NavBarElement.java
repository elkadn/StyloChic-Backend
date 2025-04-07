package com.styloChic.ecommerce.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "nav_bar_element")
public class NavBarElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String elementId;

    @ManyToOne
    @JoinColumn(name = "nav_bar_section_id")
    @JsonBackReference
    private NavBarSection navBarSection;

    @OneToOne
    @JoinColumn(name = "categorie_id")
    @JsonIgnore
    private Categorie categorie;

    public NavBarElement() {
    }

    public NavBarElement(Long id, String nom, NavBarSection navBarSection,String elementId,Categorie categorie) {
        this.id = id;
        this.nom = nom;
        this.navBarSection = navBarSection;
        this.elementId = elementId;
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

    public NavBarSection getNavBarSection() {
        return navBarSection;
    }

    public void setNavBarSection(NavBarSection navBarSection) {
        this.navBarSection = navBarSection;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
