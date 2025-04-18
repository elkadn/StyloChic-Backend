package com.styloChic.ecommerce.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Evenements")
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @Column(name = "debut")
    private LocalDateTime debut;

    @Column(name = "fin")
    private LocalDateTime fin;

    @Column(name = "tousJours")
    private Boolean tousJours;

    public Evenement() {
    }

    public Evenement(Long id, String titre, LocalDateTime debut, LocalDateTime fin, Boolean tousJours) {
        this.id = id;
        this.titre = titre;
        this.debut = debut;
        this.fin = fin;
        this.tousJours = tousJours;
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

    public LocalDateTime getDebut() {
        return debut;
    }

    public void setDebut(LocalDateTime debut) {
        this.debut = debut;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    public Boolean getTousJours() {
        return tousJours;
    }

    public void setTousJours(Boolean tousJours) {
        this.tousJours = tousJours;
    }
}
