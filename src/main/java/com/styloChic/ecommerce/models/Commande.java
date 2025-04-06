package com.styloChic.ecommerce.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Commande extends Transaction {

    @Column(name = "numero_commande",unique = true)
    private String numCommande;

    private LocalDateTime dateCommande;
    private LocalDateTime dateLivraison;

    @Column(name = "statut_commande")
    private String statutCommande;

    @OneToOne
    private AdresseCommande adresseLivrasion;

    @Column(name = "prix_ttc_reduit")
    private double prixTTCReduit;

    private double pourcentageReduction;
    private double montantBase;
    private double montantReduit;



    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<LigneCommande> ligneCommande = new ArrayList<>();

    @Embedded
    private DetailsPaiement detailsPaiement = new DetailsPaiement();

    public Commande(String numCommande, LocalDateTime dateCommande, LocalDateTime dateLivraison,String statutCommande, AdresseCommande adresseLivrasion, double prixTTCReduit, double pourcentageReduction, double montantBase, double montantReduit, List<LigneCommande> ligneCommande,DetailsPaiement detailsPaiement) {
        this.numCommande = numCommande;
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.statutCommande = statutCommande;
        this.adresseLivrasion = adresseLivrasion;
        this.prixTTCReduit = prixTTCReduit;
        this.pourcentageReduction = pourcentageReduction;
        this.montantBase = montantBase;
        this.montantReduit = montantReduit;
        this.ligneCommande = ligneCommande;
        this.detailsPaiement = detailsPaiement;
    }

    public Commande() {
    }

    public String getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(String numCommande) {
        this.numCommande = numCommande;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    public LocalDateTime getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(LocalDateTime dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public AdresseCommande getAdresseLivrasion() {
        return adresseLivrasion;
    }

    public void setAdresseLivrasion(AdresseCommande adresseLivrasion) {
        this.adresseLivrasion = adresseLivrasion;
    }

    public double getPrixTTCReduit() {
        return prixTTCReduit;
    }

    public void setPrixTTCReduit(double prixTTCReduit) {
        this.prixTTCReduit = prixTTCReduit;
    }

    public double getPourcentageReduction() {
        return pourcentageReduction;
    }

    public void setPourcentageReduction(double pourcentageReduction) {
        this.pourcentageReduction = pourcentageReduction;
    }

    public double getMontantBase() {
        return montantBase;
    }

    public void setMontantBase(double montantBase) {
        this.montantBase = montantBase;
    }

    public double getMontantReduit() {
        return montantReduit;
    }

    public void setMontantReduit(double montantReduit) {
        this.montantReduit = montantReduit;
    }

    public List<LigneCommande> getLigneCommande() {
        return ligneCommande;
    }

    public void setLigneCommande(List<LigneCommande> ligneCommande) {
        this.ligneCommande = ligneCommande;
    }

    public String getStatutCommande() {
        return statutCommande;
    }

    public void setStatutCommande(String statutCommande) {
        this.statutCommande = statutCommande;
    }

    public DetailsPaiement getDetailsPaiement() {
        return detailsPaiement;
    }

    public void setDetailsPaiement(DetailsPaiement detailsPaiement) {
        this.detailsPaiement = detailsPaiement;
    }
}
