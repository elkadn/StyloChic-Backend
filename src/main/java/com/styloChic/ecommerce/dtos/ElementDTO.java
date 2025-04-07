package com.styloChic.ecommerce.dtos;

public class ElementDTO {

    private Long id;

    private String nom;
    private String elementId;


    public ElementDTO() {
    }

    public ElementDTO(Long id, String nom, String elementId) {
        this.id = id;
        this.nom = nom;
        this.elementId = elementId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
}
