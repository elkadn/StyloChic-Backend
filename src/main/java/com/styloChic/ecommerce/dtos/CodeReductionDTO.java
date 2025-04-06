package com.styloChic.ecommerce.dtos;

import java.util.Date;

public class CodeReductionDTO {

    private String code;
    private double pourcentage;
    private Date dateDebut;
    private Date dateFin;
    private boolean actif;
    private double conditionReduction;


    public CodeReductionDTO() {
    }

    public CodeReductionDTO(String code, double pourcentage, Date dateDebut, Date dateFin, boolean actif, double conditionReduction) {
        this.code = code;
        this.pourcentage = pourcentage;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.actif = actif;
        this.conditionReduction = conditionReduction;
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

    public void setConditionReduction(double conditionReduction) {
        this.conditionReduction = conditionReduction;
    }
}
