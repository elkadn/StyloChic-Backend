package com.styloChic.ecommerce.requests;


public class LigneAchatRequest {
    private Long produitId;
    private Integer quantite;
    private Double prixHT;
    private Double prixTTC;
    private Double tva;
    private String taille;


    public LigneAchatRequest(Long produitId, Integer quantite, Double prixHT, Double prixTTC, Double tva,String taille) {
        this.produitId = produitId;
        this.quantite = quantite;
        this.prixHT = prixHT;
        this.prixTTC = prixTTC;
        this.tva = tva;
        this.taille = taille;
    }

    public LigneAchatRequest() {
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
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

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

}