package com.styloChic.ecommerce.dtos;


public class OffreSpecialeDTO {

    private String nom;
    private String urlImage;
    private String descriptionImage;
    private String lien;
    private boolean visibilite;
    private Long produitId;
    private Long navBarCategorieId;


    public OffreSpecialeDTO() {
    }

    public OffreSpecialeDTO(String nom, String urlImage, String descriptionImage, String lien, boolean visibilite, Long produitId,Long navBarCategorieId) {
        this.nom = nom;
        this.urlImage = urlImage;
        this.descriptionImage = descriptionImage;
        this.lien = lien;
        this.visibilite = visibilite;
        this.produitId = produitId;
        this.navBarCategorieId = navBarCategorieId;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDescriptionImage() {
        return descriptionImage;
    }

    public void setDescriptionImage(String descriptionImage) {
        this.descriptionImage = descriptionImage;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public boolean isVisibilite() {
        return visibilite;
    }

    public void setVisibilite(boolean visibilite) {
        this.visibilite = visibilite;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Long getNavBarCategorieId() {
        return navBarCategorieId;
    }

    public void setNavBarCategorieId(Long navBarCategorieId) {
        this.navBarCategorieId = navBarCategorieId;
    }
}
