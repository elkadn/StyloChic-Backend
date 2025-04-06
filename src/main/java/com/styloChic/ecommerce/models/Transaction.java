package com.styloChic.ecommerce.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "typeTransaction", discriminatorType = DiscriminatorType.STRING)
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Utilisateur utilisateur;

    @Column(name = "total_ht")
    private double totalHT;

    @Column(name = "total_ttc")
    private double totalTTC;

    private double tva;

    private int totalElements;
    private LocalDateTime dateCreation;

    public Transaction(Long id, Utilisateur utilisateur, double totalHT, double totalTTC, double tva, int totalElements, LocalDateTime dateCreation) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.totalHT = totalHT;
        this.totalTTC = totalTTC;
        this.tva = tva;
        this.totalElements = totalElements;
        this.dateCreation = dateCreation;
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public double getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(double totalHT) {
        this.totalHT = totalHT;
    }

    public double getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(double totalTTC) {
        this.totalTTC = totalTTC;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }


    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
}
