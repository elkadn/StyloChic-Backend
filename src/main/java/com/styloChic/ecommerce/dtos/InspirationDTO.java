package com.styloChic.ecommerce.dtos;

import java.time.LocalDateTime;

public class InspirationDTO {

    private Long id;

    private String image;

    private String commentaire;

    private LocalDateTime dateAjout;

    private boolean visibilite;

    private String produitTitre;

    private String nomUtilisateur;

    public InspirationDTO() {
    }

    public InspirationDTO(Long id, String image, String commentaire, LocalDateTime dateAjout, boolean visibilite, String produitTitre,String nomUtilisateur) {
        this.id = id;
        this.image = image;
        this.commentaire = commentaire;
        this.dateAjout = dateAjout;
        this.visibilite = visibilite;
        this.produitTitre = produitTitre;
        this.nomUtilisateur = nomUtilisateur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public LocalDateTime getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }

    public boolean isVisibilite() {
        return visibilite;
    }

    public void setVisibilite(boolean visibilite) {
        this.visibilite = visibilite;
    }

    public String getProduitTitre() {
        return produitTitre;
    }

    public void setProduitTitre(String produitTitre) {
        this.produitTitre = produitTitre;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }
}
