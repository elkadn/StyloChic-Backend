package com.styloChic.ecommerce.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class AchatDTO {
    private Long id;
    private String numAchat;
    private LocalDateTime dateAchat;
    private LocalDateTime dateReception;
    private String statutAchat;
    private Double totalHT;
    private Double totalTTC;
    private Double tva;

    private String fournisseurNom;
    private String adminNom;
    private String adresseReception;

    private List<LigneAchatDTO> lignesAchat;


    public AchatDTO() {
    }

    public AchatDTO(Long id, String numAchat, LocalDateTime dateAchat, LocalDateTime dateReception, String statutAchat, Double totalHT, Double totalTTC, Double tva, String fournisseurNom, String adminNom, String adresseReception,List<LigneAchatDTO> lignesAchat) {
        this.id = id;
        this.numAchat = numAchat;
        this.dateAchat = dateAchat;
        this.dateReception = dateReception;
        this.statutAchat = statutAchat;
        this.totalHT = totalHT;
        this.totalTTC = totalTTC;
        this.tva = tva;
        this.fournisseurNom = fournisseurNom;
        this.adminNom = adminNom;
        this.adresseReception = adresseReception;
        this.lignesAchat = lignesAchat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumAchat() {
        return numAchat;
    }

    public void setNumAchat(String numAchat) {
        this.numAchat = numAchat;
    }

    public LocalDateTime getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDateTime dateAchat) {
        this.dateAchat = dateAchat;
    }

    public LocalDateTime getDateReception() {
        return dateReception;
    }

    public void setDateReception(LocalDateTime dateReception) {
        this.dateReception = dateReception;
    }

    public String getStatutAchat() {
        return statutAchat;
    }

    public void setStatutAchat(String statutAchat) {
        this.statutAchat = statutAchat;
    }

    public Double getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(Double totalHT) {
        this.totalHT = totalHT;
    }

    public Double getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(Double totalTTC) {
        this.totalTTC = totalTTC;
    }

    public Double getTva() {
        return tva;
    }

    public void setTva(Double tva) {
        this.tva = tva;
    }

    public String getFournisseurNom() {
        return fournisseurNom;
    }

    public void setFournisseurNom(String fournisseurNom) {
        this.fournisseurNom = fournisseurNom;
    }

    public String getAdminNom() {
        return adminNom;
    }

    public void setAdminNom(String adminNom) {
        this.adminNom = adminNom;
    }

    public String getAdresseReception() {
        return adresseReception;
    }

    public void setAdresseReception(String adresseReception) {
        this.adresseReception = adresseReception;
    }

    public List<LigneAchatDTO> getLignesAchat() {
        return lignesAchat;
    }

    public void setLignesAchat(List<LigneAchatDTO> lignesAchat) {
        this.lignesAchat = lignesAchat;
    }
}



