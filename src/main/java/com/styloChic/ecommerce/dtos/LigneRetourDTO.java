package com.styloChic.ecommerce.dtos;

public class LigneRetourDTO {

    private Long id;
    private Long produitId;
    private String taille;
    private int quantite;
    private String raisonRetour;
    private String etatProduit;
    private String imageProduit;
    private String titreProduit;

    public LigneRetourDTO() {
    }

    public LigneRetourDTO(Long id, Long produitId, String taille, int quantite, String raisonRetour, String etatProduit, String imageProduit, String titreProduit) {
        this.id = id;
        this.produitId = produitId;
        this.taille = taille;
        this.quantite = quantite;
        this.raisonRetour = raisonRetour;
        this.etatProduit = etatProduit;
        this.imageProduit = imageProduit;
        this.titreProduit = titreProduit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
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

    public String getRaisonRetour() {
        return raisonRetour;
    }

    public void setRaisonRetour(String raisonRetour) {
        this.raisonRetour = raisonRetour;
    }

    public String getEtatProduit() {
        return etatProduit;
    }

    public void setEtatProduit(String etatProduit) {
        this.etatProduit = etatProduit;
    }

    public String getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(String imageProduit) {
        this.imageProduit = imageProduit;
    }

    public String getTitreProduit() {
        return titreProduit;
    }

    public void setTitreProduit(String titreProduit) {
        this.titreProduit = titreProduit;
    }
}
