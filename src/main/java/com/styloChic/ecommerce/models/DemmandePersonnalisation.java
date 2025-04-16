package com.styloChic.ecommerce.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class DemmandePersonnalisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateAjout;

    @Column(nullable = true)
    private String commentaire;

    private String reponse;
    private String taille;
    private String couleur;
    private String design;

    private String statut;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    @JsonIgnore
    private Produit produit;


    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    @JsonIgnore
    private Utilisateur utilisateur;

    public DemmandePersonnalisation(Long id, LocalDateTime dateAjout, String commentaire, String reponse, String taille, String couleur, String design, String statut, Produit produit, Utilisateur utilisateur) {
        this.id = id;
        this.dateAjout = dateAjout;
        this.commentaire = commentaire;
        this.reponse = reponse;
        this.taille = taille;
        this.couleur = couleur;
        this.design = design;
        this.statut = statut;
        this.produit = produit;
        this.utilisateur = utilisateur;
    }

    public DemmandePersonnalisation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
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
}
