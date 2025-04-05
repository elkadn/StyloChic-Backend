package com.styloChic.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Couleur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String hexaCode;


    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreation;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateModification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Utilisateur admin;

    public Couleur(String nom) {
        this.nom = nom;
    }
    public Couleur() {
    }

    public Couleur(Long id, String nom, String hexaCode, LocalDateTime dateCreation, LocalDateTime dateModification, Utilisateur admin) {
        this.id = id;
        this.nom = nom;
        this.hexaCode = hexaCode;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.admin = admin;
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

    public String getHexaCode() {
        return hexaCode;
    }

    public void setHexaCode(String hexaCode) {
        this.hexaCode = hexaCode;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }

    public Utilisateur getAdmin() {
        return admin;
    }

    public void setAdmin(Utilisateur admin) {
        this.admin = admin;
    }
}
