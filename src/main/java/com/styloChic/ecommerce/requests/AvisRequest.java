package com.styloChic.ecommerce.requests;

public class AvisRequest {

    private Long idProduit;
    private String avis;

    public AvisRequest() {
    }

    public AvisRequest(Long idProduit, String avis) {
        this.idProduit = idProduit;
        this.avis = avis;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }
}
