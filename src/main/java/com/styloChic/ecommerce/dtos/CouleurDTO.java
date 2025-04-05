package com.styloChic.ecommerce.dtos;

public class CouleurDTO {

    private String nom;

    private String hexaCode;
    public CouleurDTO(){}

    public CouleurDTO(String nom,String hexaCode) {
        this.nom = nom;
        this.hexaCode = hexaCode;
    }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public String getHexaCode() {
        return hexaCode;
    }

    public void setHexaCode(String hexaCode) {
        this.hexaCode = hexaCode;
    }
}
