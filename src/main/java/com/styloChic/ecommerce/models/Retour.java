package com.styloChic.ecommerce.models;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public abstract class Retour extends Transaction{

    private String typeRetour;
    private String raison;
    private String statut;
    private String modeRetour;
    private LocalDate dateApplication;

    public Retour(Long id, Utilisateur utilisateur, double totalHT, double totalTTC, double tva, int totalElements, LocalDateTime dateCreation, String typeRetour, String raison, String statut, String modeRetour, LocalDate dateApplication) {
        super(id, utilisateur, totalHT, totalTTC, tva, totalElements, dateCreation);
        this.typeRetour = typeRetour;
        this.raison = raison;
        this.statut = statut;
        this.modeRetour = modeRetour;
        this.dateApplication = dateApplication;
    }

    public Retour(String typeRetour, String raison, String statut, String modeRetour, LocalDate dateApplication) {
        this.typeRetour = typeRetour;
        this.raison = raison;
        this.statut = statut;
        this.modeRetour = modeRetour;
        this.dateApplication = dateApplication;
    }

    public Retour() {

    }

    public String getTypeRetour() {
        return typeRetour;
    }

    public void setTypeRetour(String typeRetour) {
        this.typeRetour = typeRetour;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getModeRetour() {
        return modeRetour;
    }

    public void setModeRetour(String modeRetour) {
        this.modeRetour = modeRetour;
    }

    public LocalDate getDateApplication() {
        return dateApplication;
    }

    public void setDateApplication(LocalDate dateApplication) {
        this.dateApplication = dateApplication;
    }
}
