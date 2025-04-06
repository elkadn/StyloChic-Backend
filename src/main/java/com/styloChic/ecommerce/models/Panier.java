package com.styloChic.ecommerce.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id",nullable = false)
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ElementPanier> elementsPanier = new HashSet<>();

    @Column(name = "prix_total_ht")
    private double prixTotalHt;

    @Column(name = "prix_total_ttc")
    private double prixTotalTTC;

    @Column(name = "prix_total_ttc_reduit")
    private double prixTotalTTCReduit;

    @Column(name = "tva")
    private double tva;

    @Column(name = "total_elements")
    private int totalElement;

    @Column(name = "pourcentage_reduction")
    private double pourcentageReduction;

    @Column(name = "montant_base")
    private double montantBase;

    @Column(name = "montant_reduit")
    private double montantReduit;

    @ManyToOne()
    @JoinColumn(name = "code_reduction_id")
    private CodeReduction codeReduction;

    public Panier() {
    }

    public Panier(Long id, Utilisateur utilisateur, Set<ElementPanier> elementsPanier, double prixTotalHt, double prixTotalTTC, double prixTotalTTCReduit, double tva, int totalElement, double pourcentageReduction, double montantBase, double montantReduit, CodeReduction codeReduction) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.elementsPanier = elementsPanier;
        this.prixTotalHt = prixTotalHt;
        this.prixTotalTTC = prixTotalTTC;
        this.prixTotalTTCReduit = prixTotalTTCReduit;
        this.tva = tva;
        this.totalElement = totalElement;
        this.pourcentageReduction = pourcentageReduction;
        this.montantBase = montantBase;
        this.montantReduit = montantReduit;
        this.codeReduction = codeReduction;
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

    public Set<ElementPanier> getElementsPanier() {
        return elementsPanier;
    }

    public void setElementsPanier(Set<ElementPanier> elementsPanier) {
        this.elementsPanier = elementsPanier;
    }

    public double getPrixTotalHt() {
        return prixTotalHt;
    }

    public void setPrixTotalHt(double prixTotalHt) {
        this.prixTotalHt = prixTotalHt;
    }

    public double getPrixTotalTTC() {
        return prixTotalTTC;
    }

    public void setPrixTotalTTC(double prixTotalTTC) {
        this.prixTotalTTC = prixTotalTTC;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
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

    public double getPrixTotalTTCReduit() {
        return prixTotalTTCReduit;
    }

    public CodeReduction getCodeReduction() {
        return codeReduction;
    }

    public void setCodeReduction(CodeReduction codeReduction) {
        this.codeReduction = codeReduction;
    }

    public void setPrixTotalTTCReduit(double prixTotalTTCReduit) {
        this.prixTotalTTCReduit = prixTotalTTCReduit;
    }
}
