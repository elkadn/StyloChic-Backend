package com.styloChic.ecommerce.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Achat extends Transaction {

    @Column(name = "numero_achat")
    private String numAchat;

    private LocalDateTime dateAchat;
    private LocalDateTime dateReception;

    @Column(name = "staut_achat")
    private String statutAchat;
    @OneToOne
    private AdresseAchat adresseReception;

    @OneToMany(mappedBy = "achat", cascade = CascadeType.ALL)
    private List<LigneAchat> lignesAchat = new ArrayList<>();

    public Achat(String numAchat, LocalDateTime dateAchat, LocalDateTime dateReception,String statutAchat, AdresseAchat adresseReception, List<LigneAchat> lignesAchat) {
        this.numAchat = numAchat;
        this.dateAchat = dateAchat;
        this.dateReception = dateReception;
        this.statutAchat = statutAchat;
        this.adresseReception = adresseReception;
        this.lignesAchat = lignesAchat;
    }

    public Achat() {
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

    public AdresseAchat getAdresseReception() {
        return adresseReception;
    }

    public void setAdresseReception(AdresseAchat adresseReception) {
        this.adresseReception = adresseReception;
    }

    public List<LigneAchat> getLignesAchat() {
        return lignesAchat;
    }

    public void setLignesAchat(List<LigneAchat> lignesAchat) {
        this.lignesAchat = lignesAchat;
    }

    public String getStatutAchat() {
        return statutAchat;
    }

    public void setStatutAchat(String statutAchat) {
        this.statutAchat = statutAchat;
    }
}
