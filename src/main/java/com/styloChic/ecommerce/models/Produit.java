package com.styloChic.ecommerce.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titre;

    private String description;

    @Column(name = "prix_achat")
    private double prixAchat;

    private double tva;

    @Column(name = "prix_vente_HT")
    private double prixVenteHT;

    @Column(name = "prix_vente_TTC")
    private double prixVenteTTC;

    @Column(name = "prix_vente_TTC_reduit")
    private double prixVenteTTCReduit;

    @Column(name = "pourcentage_reduction")
    private double pourcentageRduction;


    @Column(name = "quantite_en_stock")
    private int quantiteEnStock;

    @Column(name = "image_principale")
    private String imagePrincipale;

    private String saison;

    @Column(name = "conseil_entretien")
    private String conseilEntretien;

    @ManyToOne()
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @ManyToOne()
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    @ManyToOne
    @JoinColumn(name = "couleur_id")
    private Couleur couleur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Utilisateur admin;

    @Embedded
    @ElementCollection
    @CollectionTable(name = "tailles_produit", joinColumns = @JoinColumn(name = "id_produit"))
    private Set<Taille> tailles = new HashSet<>();

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageProduit> imagesProduit = new ArrayList<>();


    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreation;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateModification;

    @OneToMany(mappedBy = "produit",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();

    @OneToMany(mappedBy = "produit",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Avis> avis = new ArrayList<>();

    @Column(name = "total_votes")
    private int total_votes;

    @Column(name = "total_avis")
    private int total_avis;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OffreSpeciale> offresSpeciales = new ArrayList<>();
    public Produit() {
    }

    public Produit(Long id, String titre, String description, double prixAchat, double tva, double prixVenteHT, double prixVenteTTC,double prixVenteTTCReduit,double pourcentageRduction, int quantiteEnStock, String imagePrincipale, String saison, String conseilEntretien, Categorie categorie,Fournisseur fournisseur, Couleur couleur, Utilisateur admin, Set<Taille> tailles, List<ImageProduit> imagesProduit, LocalDateTime dateCreation, LocalDateTime dateModification, List<Vote> votes, List<Avis> avis, int total_votes, int total_avis, List<OffreSpeciale> offresSpeciales) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prixAchat = prixAchat;
        this.tva = tva;
        this.prixVenteHT = prixVenteHT;
        this.prixVenteTTC = prixVenteTTC;
        this.prixVenteTTCReduit = prixVenteTTCReduit;
        this.pourcentageRduction = pourcentageRduction;
        this.quantiteEnStock = quantiteEnStock;
        this.imagePrincipale = imagePrincipale;
        this.saison = saison;
        this.conseilEntretien = conseilEntretien;
        this.categorie = categorie;
        this.fournisseur = fournisseur;
        this.couleur = couleur;
        this.admin = admin;
        this.tailles = tailles;
        this.imagesProduit = imagesProduit;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.votes = votes;
        this.avis = avis;
        this.total_votes = total_votes;
        this.total_avis = total_avis;
        this.offresSpeciales = offresSpeciales;
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

    public double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public Utilisateur getAdmin() {
        return admin;
    }

    public void setAdmin(Utilisateur admin) {
        this.admin = admin;
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

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
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

    public double getPrixVenteTTCReduit() {
        return prixVenteTTCReduit;
    }

    public void setPrixVenteTTCReduit(double prixVenteTTCReduit) {
        this.prixVenteTTCReduit = prixVenteTTCReduit;
    }

    public double getPourcentageRduction() {
        return pourcentageRduction;
    }

    public void setPourcentageRduction(double pourcentageRduction) {
        this.pourcentageRduction = pourcentageRduction;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public List<OffreSpeciale> getOffresSpeciales() {
        return offresSpeciales;
    }

    public void setOffresSpeciales(List<OffreSpeciale> offresSpeciales) {
        this.offresSpeciales = offresSpeciales;
    }
}



