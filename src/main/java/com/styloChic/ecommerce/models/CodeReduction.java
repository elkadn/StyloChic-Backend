package com.styloChic.ecommerce.models;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class CodeReduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    private double pourcentage;

    private Date dateDebut;

    private Date dateFin;

    private boolean actif;

    private double conditionReduction;

    public CodeReduction() {
    }

    public CodeReduction(Long id, String code, double pourcentage, Date dateDebut, Date dateFin, boolean actif, double conditionReduction) {
        this.id = id;
        this.code = code;
        this.pourcentage = pourcentage;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.actif = actif;
        this.conditionReduction = conditionReduction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public double getConditionReduction() {
        return conditionReduction;
    }

    public void setConditionReduction(double condition) {
        this.conditionReduction = condition;
    }
}
