package com.styloChic.ecommerce.requests;

public class DemandePersonnalisationRequest {

    private Long produitId;
    private String commentaire;
    private String taille;
    private String couleur;
    private String design;


    public DemandePersonnalisationRequest(Long produitId, String commentaire, String taille, String couleur, String design) {
        this.produitId = produitId;
        this.commentaire = commentaire;
        this.taille = taille;
        this.couleur = couleur;
        this.design = design;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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
}
