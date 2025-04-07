package com.styloChic.ecommerce.requests;
import com.styloChic.ecommerce.models.Taille;

import java.util.HashSet;
import java.util.Set;

public class CreationProduitRequest {

    private String titre;
    private String description;
    private String saison;
    private String conseilEntretien;

    private double prixAchat;

    private double prixVenteHT;
    private double tva;
    private double pourcentageReduction;


    private Long couleurId;

    private Set<Taille> tailles = new HashSet<>();

    private String imagePrincipale;

    private Long categorieId;

    private Long fournisseurId;


    public CreationProduitRequest() {
    }

    public CreationProduitRequest(String titre, String description, String saison, String conseilEntretien, double prixAchat, double prixVenteHT, double tva,double pourcentageReduction, Long couleurId, Set<Taille> tailles, String imagePrincipale, Long categorieId,Long fournisseurId) {
        this.titre = titre;
        this.description = description;
        this.saison = saison;
        this.conseilEntretien = conseilEntretien;
        this.prixAchat = prixAchat;
        this.prixVenteHT = prixVenteHT;
        this.tva = tva;
        this.couleurId = couleurId;
        this.tailles = tailles;
        this.imagePrincipale = imagePrincipale;
        this.categorieId = categorieId;
        this.pourcentageReduction = pourcentageReduction;
        this.fournisseurId = fournisseurId;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSaison() {
        return saison;
    }

    public void setSaison(String saison) {
        this.saison = saison;
    }

    public String getConseilEntretien() {
        return conseilEntretien;
    }

    public void setConseilEntretien(String conseilEntretien) {
        this.conseilEntretien = conseilEntretien;
    }

    public double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public double getPrixVenteHT() {
        return prixVenteHT;
    }

    public void setPrixVenteHT(double prixVenteHT) {
        this.prixVenteHT = prixVenteHT;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public Long getCouleurId() {
        return couleurId;
    }

    public void setCouleurId(Long couleurId) {
        this.couleurId = couleurId;
    }

    public Set<Taille> getTailles() {
        return tailles;
    }

    public void setTailles(Set<Taille> tailles) {
        this.tailles = tailles;
    }

    public String getImagePrincipale() {
        return imagePrincipale;
    }

    public void setImagePrincipale(String imagePrincipale) {
        this.imagePrincipale = imagePrincipale;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    public double getPourcentageReduction() {
        return pourcentageReduction;
    }

    public void setPourcentageReduction(double pourcentageReduction) {
        this.pourcentageReduction = pourcentageReduction;
    }

    public Long getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(Long fournisseurId) {
        this.fournisseurId = fournisseurId;
    }
}
