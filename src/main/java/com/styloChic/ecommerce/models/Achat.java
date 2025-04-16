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

    @OneToOne(cascade = CascadeType.ALL)
    private AdresseAchat adresseReception;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Utilisateur admin;

    @OneToMany(mappedBy = "achat", cascade = CascadeType.ALL)
    private List<LigneAchat> lignesAchat = new ArrayList<>();



    public Achat(Long id, Utilisateur utilisateur, double totalHT, double totalTTC, double tva, int totalElements, LocalDateTime dateCreation, String numAchat, LocalDateTime dateAchat, LocalDateTime dateReception, String statutAchat, AdresseAchat adresseReception, Fournisseur fournisseur, Utilisateur admin, List<LigneAchat> lignesAchat) {
        super(id, utilisateur, totalHT, totalTTC, tva, totalElements, dateCreation);
        this.numAchat = numAchat;
        this.dateAchat = dateAchat;
        this.dateReception = dateReception;
        this.statutAchat = statutAchat;
        this.adresseReception = adresseReception;
        this.fournisseur = fournisseur;
        this.admin = admin;
        this.lignesAchat = lignesAchat;
    }

    public Achat(String numAchat, LocalDateTime dateAchat, LocalDateTime dateReception, String statutAchat, AdresseAchat adresseReception, Fournisseur fournisseur, Utilisateur admin, List<LigneAchat> lignesAchat) {
        this.numAchat = numAchat;
        this.dateAchat = dateAchat;
        this.dateReception = dateReception;
        this.statutAchat = statutAchat;
        this.adresseReception = adresseReception;
        this.fournisseur = fournisseur;
        this.admin = admin;
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

    public String getStatutAchat() {
        return statutAchat;
    }

    public void setStatutAchat(String statutAchat) {
        this.statutAchat = statutAchat;
    }

    public AdresseAchat getAdresseReception() {
        return adresseReception;
    }

    public void setAdresseReception(AdresseAchat adresseReception) {
        this.adresseReception = adresseReception;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Utilisateur getAdmin() {
        return admin;
    }

    public void setAdmin(Utilisateur admin) {
        this.admin = admin;
    }

    public List<LigneAchat> getLignesAchat() {
        return lignesAchat;
    }

    public void setLignesAchat(List<LigneAchat> lignesAchat) {
        this.lignesAchat = lignesAchat;
    }
}
