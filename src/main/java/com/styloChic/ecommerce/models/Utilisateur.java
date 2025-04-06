package com.styloChic.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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


    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Adresse> adresse = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
    private List<Avis> avis = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
    private List<Vote> votes = new ArrayList<>();
    public Utilisateur() {
    }

    public Utilisateur(Long id, String prenom, String nom, String motDePasse, String email, String role, String telephone, LocalDateTime dateCreation, LocalDateTime dateModification, Utilisateur admin, List<Adresse> adresse, List<Avis> avis, List<Vote> votes) {
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
        this.adresse = adresse;
        this.avis = avis;
        this.votes = votes;
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

    public List<Adresse> getAdresse() {
        return adresse;
    }

    public void setAdresse(List<Adresse> adresse) {
        this.adresse = adresse;
    }

    public List<Avis> getAvis() {
        return avis;
    }

    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
