package com.styloChic.ecommerce.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String adresse;
    private String ville;
    private String region;
    private String zipCode;
    private String telephone;
    private LocalDateTime dateAjout;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    @JsonIgnore
    private Utilisateur utilisateur;


    public Adresse() {
    }

    public Adresse(Long id, String adresse, String ville, String region, String zipCode, String telephone, LocalDateTime dateAjout,Utilisateur utilisateur) {
        this.id = id;
        this.adresse = adresse;
        this.ville = ville;
        this.region = region;
        this.zipCode = zipCode;
        this.telephone = telephone;
        this.dateAjout = dateAjout;
        this.utilisateur = utilisateur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDateTime getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}

