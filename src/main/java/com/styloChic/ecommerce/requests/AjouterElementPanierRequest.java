package com.styloChic.ecommerce.requests;

public class AjouterElementPanierRequest {
    private Long idProduit;
    private String taille;
    private int quantite;
    private Integer prixTotal;

    public AjouterElementPanierRequest(Long idProduit, String taille, int quantite, Integer prixTotal) {
        this.idProduit = idProduit;
        this.taille = taille;
        this.quantite = quantite;
        this.prixTotal = prixTotal;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
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

    public Integer getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Integer prixTotal) {
        this.prixTotal = prixTotal;
    }
}
