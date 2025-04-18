package com.styloChic.ecommerce.responses;

public class ProduitBest10Response {
    private Long id;
    private String titre;
    private Long nombreVentes;

    public ProduitBest10Response() {
    }

    public ProduitBest10Response(Long id, String titre, Long nombreVentes) {
        this.id = id;
        this.titre = titre;
        this.nombreVentes = nombreVentes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Long getNombreVentes() {
        return nombreVentes;
    }

    public void setNombreVentes(Long nombreVentes) {
        this.nombreVentes = nombreVentes;
    }
}
