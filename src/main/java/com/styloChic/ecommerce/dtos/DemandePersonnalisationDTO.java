package com.styloChic.ecommerce.dtos;

import java.time.LocalDateTime;

public class DemandePersonnalisationDTO {

    private Long id;
    private LocalDateTime dateAjout;
    private String commentaire;
    private String reponse;
    private String taille;
    private String couleur;
    private String design;
    private String statut;
    private String titreProduit;
    private String nomUtilisateur;

    public DemandePersonnalisationDTO() {
    }

    public DemandePersonnalisationDTO(Long id, LocalDateTime dateAjout, String commentaire, String reponse, String taille, String couleur, String design, String statut, String titreProduit, String nomUtilisateur) {
        this.id = id;
        this.dateAjout = dateAjout;
        this.commentaire = commentaire;
        this.reponse = reponse;
        this.taille = taille;
        this.couleur = couleur;
        this.design = design;
        this.statut = statut;
        this.titreProduit = titreProduit;
        this.nomUtilisateur = nomUtilisateur;
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

    public String getTitreProduit() {
        return titreProduit;
    }

    public void setTitreProduit(String titreProduit) {
        this.titreProduit = titreProduit;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }
}
