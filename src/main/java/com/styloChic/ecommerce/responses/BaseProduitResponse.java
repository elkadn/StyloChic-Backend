package com.styloChic.ecommerce.responses;

import com.styloChic.ecommerce.models.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseProduitResponse {

    private Long id;
    private String titre;
    private String description;
    private double tva;
    private double prixVenteHT;
    private double prixVenteTTC;
    private double prixVenteTTCReduit;
    private double pourcentageReduction;
    private String categorieParente;
    private String categorieMoyenne;
    private String categorieBase;
    private int quantiteEnStock;
    private String imagePrincipale;
    private String saison;
    private String conseilEntretien;
    private String nomCouleur;
    private Set<Taille> tailles = new HashSet<>();
    private List<ImageProduit> imagesProduit = new ArrayList<>();
    private List<Vote> votes = new ArrayList<>();
    private List<Avis> avis = new ArrayList<>();
    private List<Inspiration> inspirations = new ArrayList<>();

    private int total_votes;
    private int total_avis;


    public BaseProduitResponse() {
    }

    public BaseProduitResponse(Long id, String titre, String description, double tva, double prixVenteHT, double prixVenteTTC, double prixVenteTTCReduit, double pourcentageReduction, String categorieParente, String categorieMoyenne, String categorieBase, int quantiteEnStock, String imagePrincipale, String saison, String conseilEntretien, String nomCouleur, Set<Taille> tailles, List<ImageProduit> imagesProduit, List<Vote> votes, List<Avis> avis,List<Inspiration> inspirations, int total_votes, int total_avis) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.tva = tva;
        this.prixVenteHT = prixVenteHT;
        this.prixVenteTTC = prixVenteTTC;
        this.prixVenteTTCReduit = prixVenteTTCReduit;
        this.pourcentageReduction = pourcentageReduction;
        this.categorieParente = categorieParente;
        this.categorieMoyenne = categorieMoyenne;
        this.categorieBase = categorieBase;
        this.quantiteEnStock = quantiteEnStock;
        this.imagePrincipale = imagePrincipale;
        this.saison = saison;
        this.conseilEntretien = conseilEntretien;
        this.nomCouleur = nomCouleur;
        this.tailles = tailles;
        this.imagesProduit = imagesProduit;
        this.votes = votes;
        this.avis = avis;
        this.inspirations = inspirations;
        this.total_votes = total_votes;
        this.total_avis = total_avis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public double getPrixVenteHT() {
        return prixVenteHT;
    }

    public void setPrixVenteHT(double prixVenteHT) {
        this.prixVenteHT = prixVenteHT;
    }

    public double getPrixVenteTTC() {
        return prixVenteTTC;
    }

    public void setPrixVenteTTC(double prixVenteTTC) {
        this.prixVenteTTC = prixVenteTTC;
    }

    public double getPrixVenteTTCReduit() {
        return prixVenteTTCReduit;
    }

    public void setPrixVenteTTCReduit(double prixVenteTTCReduit) {
        this.prixVenteTTCReduit = prixVenteTTCReduit;
    }

    public double getPourcentageReduction() {
        return pourcentageReduction;
    }

    public void setPourcentageReduction(double pourcentageReduction) {
        this.pourcentageReduction = pourcentageReduction;
    }

    public String getCategorieParente() {
        return categorieParente;
    }

    public void setCategorieParente(String categorieParente) {
        this.categorieParente = categorieParente;
    }

    public String getCategorieMoyenne() {
        return categorieMoyenne;
    }

    public void setCategorieMoyenne(String categorieMoyenne) {
        this.categorieMoyenne = categorieMoyenne;
    }

    public String getCategorieBase() {
        return categorieBase;
    }

    public void setCategorieBase(String categorieBase) {
        this.categorieBase = categorieBase;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }

    public String getImagePrincipale() {
        return imagePrincipale;
    }

    public void setImagePrincipale(String imagePrincipale) {
        this.imagePrincipale = imagePrincipale;
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

    public String getNomCouleur() {
        return nomCouleur;
    }

    public void setNomCouleur(String nomCouleur) {
        this.nomCouleur = nomCouleur;
    }

    public Set<Taille> getTailles() {
        return tailles;
    }

    public void setTailles(Set<Taille> tailles) {
        this.tailles = tailles;
    }

    public List<ImageProduit> getImagesProduit() {
        return imagesProduit;
    }

    public void setImagesProduit(List<ImageProduit> imagesProduit) {
        this.imagesProduit = imagesProduit;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<Avis> getAvis() {
        return avis;
    }

    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }

    public List<Inspiration> getInspirations() {
        return inspirations;
    }

    public void setInspirations(List<Inspiration> inspirations) {
        this.inspirations = inspirations;
    }

    public int getTotal_votes() {
        return total_votes;
    }

    public void setTotal_votes(int total_votes) {
        this.total_votes = total_votes;
    }

    public int getTotal_avis() {
        return total_avis;
    }

    public void setTotal_avis(int total_avis) {
        this.total_avis = total_avis;
    }
}
