package com.styloChic.ecommerce.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Publicite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urlImage;
    private String structure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Utilisateur admin;

    LocalDateTime dateAjout;
    LocalDateTime dateModification;

    public Publicite() {
    }

    public Publicite(Long id, String urlImage, String structure, Utilisateur admin, LocalDateTime dateAjout, LocalDateTime dateModification) {
        this.id = id;
        this.urlImage = urlImage;
        this.structure = structure;
        this.admin = admin;
        this.dateAjout = dateAjout;
        this.dateModification = dateModification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public Utilisateur getAdmin() {
        return admin;
    }

    public void setAdmin(Utilisateur admin) {
        this.admin = admin;
    }

    public LocalDateTime getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }
}
