package com.styloChic.ecommerce.dtos;

import java.util.List;

public class SectionDTO {

    private Long id;

    private String nom;

    private List<ElementDTO> elements;


    public SectionDTO() {
    }

    public SectionDTO(Long id, String nom, List<ElementDTO> elements) {
        this.id = id;
        this.nom = nom;
        this.elements = elements;
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

    public List<ElementDTO> getElements() {
        return elements;
    }

    public void setElements(List<ElementDTO> elements) {
        this.elements = elements;
    }
}
