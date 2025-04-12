package com.styloChic.ecommerce.dtos;

public class LigneCommandeDTO {

    private Long id;
    private String taille;
    private int quantite;
    private Double prixHT;
    private Double prixTTC;
    private Double tva;
    private Double prixTTCReduit;

    private Long idProduit;
    private String imageProduit;
    private String couleurProduit;
    private Double prixProduitHT;
    private Double tvaProduit;
    private Double prixProduitTTC;
    private Double prixProduitReduit;
    private String saisonProduit;
    private Double pourcentageReductionProduit;
    private String titreProduit;
    private String descriptionProduit;
    private String conseilEntretienProduit;


    public LigneCommandeDTO() {
    }

    public LigneCommandeDTO(Long id, String taille, int quantite, Double prixHT, Double prixTTC, Double tva, Double prixTTCReduit,Long idProduit, String imageProduit, String couleurProduit, Double prixProduitHT, Double tvaProduit, Double prixProduitTTC, Double prixProduitReduit, String saisonProduit,Double pourcentageReductionProduit, String titreProduit, String descriptionProduit, String conseilEntretienProduit) {
        this.id = id;
        this.taille = taille;
        this.quantite = quantite;
        this.prixHT = prixHT;
        this.prixTTC = prixTTC;
        this.tva = tva;
        this.prixTTCReduit = prixTTCReduit;
        this.idProduit = idProduit;
        this.imageProduit = imageProduit;
        this.couleurProduit = couleurProduit;
        this.prixProduitHT = prixProduitHT;
        this.tvaProduit = tvaProduit;
        this.prixProduitTTC = prixProduitTTC;
        this.prixProduitReduit = prixProduitReduit;
        this.saisonProduit = saisonProduit;
        this.pourcentageReductionProduit = pourcentageReductionProduit;
        this.titreProduit = titreProduit;
        this.descriptionProduit = descriptionProduit;
        this.conseilEntretienProduit = conseilEntretienProduit;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Double getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(Double prixHT) {
        this.prixHT = prixHT;
    }

    public Double getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(Double prixTTC) {
        this.prixTTC = prixTTC;
    }

    public Double getTva() {
        return tva;
    }

    public void setTva(Double tva) {
        this.tva = tva;
    }

    public Double getPrixTTCReduit() {
        return prixTTCReduit;
    }

    public void setPrixTTCReduit(Double prixTTCReduit) {
        this.prixTTCReduit = prixTTCReduit;
    }

    public String getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(String imageProduit) {
        this.imageProduit = imageProduit;
    }

    public String getCouleurProduit() {
        return couleurProduit;
    }

    public void setCouleurProduit(String couleurProduit) {
        this.couleurProduit = couleurProduit;
    }

    public Double getPrixProduitHT() {
        return prixProduitHT;
    }

    public void setPrixProduitHT(Double prixProduitHT) {
        this.prixProduitHT = prixProduitHT;
    }

    public Double getTvaProduit() {
        return tvaProduit;
    }

    public void setTvaProduit(Double tvaProduit) {
        this.tvaProduit = tvaProduit;
    }

    public Double getPrixProduitTTC() {
        return prixProduitTTC;
    }

    public void setPrixProduitTTC(Double prixProduitTTC) {
        this.prixProduitTTC = prixProduitTTC;
    }

    public Double getPrixProduitReduit() {
        return prixProduitReduit;
    }

    public void setPrixProduitReduit(Double prixProduitReduit) {
        this.prixProduitReduit = prixProduitReduit;
    }

    public String getSaisonProduit() {
        return saisonProduit;
    }

    public void setSaisonProduit(String saisonProduit) {
        this.saisonProduit = saisonProduit;
    }

    public String getTitreProduit() {
        return titreProduit;
    }

    public void setTitreProduit(String titreProduit) {
        this.titreProduit = titreProduit;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

    public String getConseilEntretienProduit() {
        return conseilEntretienProduit;
    }

    public void setConseilEntretienProduit(String conseilEntretienProduit) {
        this.conseilEntretienProduit = conseilEntretienProduit;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public Double getPourcentageReductionProduit() {
        return pourcentageReductionProduit;
    }

    public void setPourcentageReductionProduit(Double pourcentageReductionProduit) {
        this.pourcentageReductionProduit = pourcentageReductionProduit;
    }
}
