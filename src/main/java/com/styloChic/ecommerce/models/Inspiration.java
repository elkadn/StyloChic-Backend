package com.styloChic.ecommerce.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Inspiration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String commentaire;

    private LocalDateTime dateAjout;

    private boolean visibilite;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    public Inspiration() {
    }

    public Inspiration(Long id, String image, String commentaire, LocalDateTime dateAjout, boolean visibilite, Produit produit) {
        this.id = id;
        this.image = image;
        this.commentaire = commentaire;
        this.dateAjout = dateAjout;
        this.visibilite = visibilite;
        this.produit = produit;
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

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
