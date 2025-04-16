package com.styloChic.ecommerce.requests;

import com.styloChic.ecommerce.models.AdresseAchat;

import java.util.List;

public class AchatRequest {
    private Long fournisseurId;
    private Double totalHT;
    private Double totalTTC;
    private Double tva;
    private List<LigneAchatRequest> lignesAchat;
    private AdresseAchat adresseAchat;


    public AchatRequest(Long fournisseurId, Double totalHT, Double totalTTC, Double tva, List<LigneAchatRequest> lignesAchat,AdresseAchat adresseAchat) {
        this.fournisseurId = fournisseurId;
        this.totalHT = totalHT;
        this.totalTTC = totalTTC;
        this.tva = tva;
        this.lignesAchat = lignesAchat;
        this.adresseAchat = adresseAchat;
    }

    public Long getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(Long fournisseurId) {
        this.fournisseurId = fournisseurId;
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

    public List<LigneAchatRequest> getLignesAchat() {
        return lignesAchat;
    }

    public void setLignesAchat(List<LigneAchatRequest> lignesAchat) {
        this.lignesAchat = lignesAchat;
    }

    public AdresseAchat getAdresseAchat() {
        return adresseAchat;
    }

    public void setAdresseAchat(AdresseAchat adresseAchat) {
        this.adresseAchat = adresseAchat;
    }
}