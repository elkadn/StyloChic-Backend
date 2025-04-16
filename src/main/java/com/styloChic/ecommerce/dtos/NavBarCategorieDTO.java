package com.styloChic.ecommerce.dtos;

import java.util.List;

public class NavBarCategorieDTO {

    private Long id;

    private String nom;

    private List<OffreSpecialeDTO> offresSpeciales;

    private List<SectionDTO> sections;

    public NavBarCategorieDTO() {}

    public NavBarCategorieDTO(Long id,String nom){
        this.id = id;
        this.nom = nom;
    }
    public NavBarCategorieDTO(Long id, String nom, List<OffreSpecialeDTO> offresSpeciales,List<SectionDTO> sections) {
        this.id = id;
        this.nom = nom;
        this.offresSpeciales = offresSpeciales;
        this.sections = sections;
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

    public List<OffreSpecialeDTO> getOffresSpeciales() {
        return offresSpeciales;
    }

    public void setOffresSpeciales(List<OffreSpecialeDTO> offresSpeciales) {
        this.offresSpeciales = offresSpeciales;
    }

    public List<SectionDTO> getSections() {
        return sections;
    }

    public void setSections(List<SectionDTO> sections) {
        this.sections = sections;
    }
}
