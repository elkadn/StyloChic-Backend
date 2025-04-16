package com.styloChic.ecommerce.dtos;

import com.styloChic.ecommerce.models.AdresseCommande;
import com.styloChic.ecommerce.models.Commande;
import com.styloChic.ecommerce.models.LigneCommande;

import java.time.LocalDateTime;
import java.util.List;

public class CommandeDTO {

    private Long id;
    private double totalHT;
    private double totalTTC;
    private double tva;
    private int totalElements;
    private LocalDateTime dateCreation;
    private String numCommande;
    private LocalDateTime dateCommande;
    private LocalDateTime dateLivraison;
    private String statutCommande;
    private AdresseCommande adresseLivrasion;
    private List<LigneCommandeDTO> ligneCommandeDTOS;
    private double prixTTCReduit;
    private double pourcentageReduction;
    private double montantBase;
    private double montantReduit;

    private String methodePaiement;

    public CommandeDTO() {
    }

    public CommandeDTO(Commande commande) {
        this.id = commande.getId();
        this.statutCommande = commande.getStatutCommande();
        this.totalHT = commande.getTotalHT();
        this.totalTTC = commande.getTotalTTC();
        this.tva = commande.getTva();
        this.totalElements = commande.getTotalElements();
        this.dateCreation = commande.getDateCreation();
        this.numCommande = commande.getNumCommande();
        this.dateCommande = commande.getDateCommande();
        this.dateLivraison = commande.getDateLivraison();
        this.adresseLivrasion = commande.getAdresseLivrasion();
        this.prixTTCReduit = commande.getPrixTTCReduit();
        this.pourcentageReduction = commande.getPourcentageReduction();
        this.montantBase = commande.getMontantBase();
        this.montantReduit = commande.getMontantReduit();
    }
    public CommandeDTO(Long id, double totalHT, double totalTTC, double tva, int totalElements, LocalDateTime dateCreation, String numCommande, LocalDateTime dateCommande, LocalDateTime dateLivraison, String statutCommande, AdresseCommande adresseLivrasion, double prixTTCReduit, double pourcentageReduction, double montantBase, double montantReduit,List<LigneCommandeDTO> ligneCommandeDTOS) {
        this.id = id;
        this.totalHT = totalHT;
        this.totalTTC = totalTTC;
        this.tva = tva;
        this.totalElements = totalElements;
        this.dateCreation = dateCreation;
        this.numCommande = numCommande;
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.statutCommande = statutCommande;
        this.adresseLivrasion = adresseLivrasion;
        this.prixTTCReduit = prixTTCReduit;
        this.pourcentageReduction = pourcentageReduction;
        this.montantBase = montantBase;
        this.montantReduit = montantReduit;
        this.ligneCommandeDTOS = ligneCommandeDTOS;
    }

    public CommandeDTO(Long id, double totalHT, double totalTTC, double tva, int totalElements, LocalDateTime dateCreation, String numCommande, LocalDateTime dateCommande, LocalDateTime dateLivraison, String statutCommande, AdresseCommande adresseLivrasion, List<LigneCommandeDTO> ligneCommandeDTOS, double prixTTCReduit, double pourcentageReduction, double montantBase, double montantReduit, String methodePaiement) {
        this.id = id;
        this.totalHT = totalHT;
        this.totalTTC = totalTTC;
        this.tva = tva;
        this.totalElements = totalElements;
        this.dateCreation = dateCreation;
        this.numCommande = numCommande;
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.statutCommande = statutCommande;
        this.adresseLivrasion = adresseLivrasion;
        this.ligneCommandeDTOS = ligneCommandeDTOS;
        this.prixTTCReduit = prixTTCReduit;
        this.pourcentageReduction = pourcentageReduction;
        this.montantBase = montantBase;
        this.montantReduit = montantReduit;
        this.methodePaiement = methodePaiement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(double totalHT) {
        this.totalHT = totalHT;
    }

    public double getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(double totalTTC) {
        this.totalTTC = totalTTC;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
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

    public String getStatutCommande() {
        return statutCommande;
    }

    public void setStatutCommande(String statutCommande) {
        this.statutCommande = statutCommande;
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

    public List<LigneCommandeDTO> getLigneCommandeDTOS() {
        return ligneCommandeDTOS;
    }

    public void setLigneCommandeDTOS(List<LigneCommandeDTO> ligneCommandeDTOS) {
        this.ligneCommandeDTOS = ligneCommandeDTOS;
    }

    public String getMethodePaiement() {
        return methodePaiement;
    }

    public void setMethodePaiement(String methodePaiement) {
        this.methodePaiement = methodePaiement;
    }
}
