package com.styloChic.ecommerce.requests;

public class AjouterElementPanierRequest {
    private Long idProduit;
    private String taille;
    private String operation;

    public AjouterElementPanierRequest(Long idProduit, String taille, String operation) {
        this.idProduit = idProduit;
        this.taille = taille;
        this.operation = operation;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
