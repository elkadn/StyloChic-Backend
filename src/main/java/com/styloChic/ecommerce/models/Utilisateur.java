package com.styloChic.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String prenom;
    private String nom;
    private String motDePasse;
    private String email;
    private String role;
    private String telephone;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreation;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateModification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Utilisateur admin;




    public Utilisateur() {
    }

    public Utilisateur(Long id, String prenom, String nom, String motDePasse, String email, String role, String telephone, LocalDateTime dateCreation, LocalDateTime dateModification,Utilisateur admin) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.motDePasse = motDePasse;
        this.email = email;
        this.role = role;
        this.telephone = telephone;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    //    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Address> address = new ArrayList<>();
//
//
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Rating> ratings = new ArrayList<>();
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    private List<Review> reviews = new ArrayList<>();
}
