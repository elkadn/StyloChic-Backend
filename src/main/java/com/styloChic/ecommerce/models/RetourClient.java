package com.styloChic.ecommerce.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RetourClient extends Retour{
    @ManyToOne
    private Commande commande;

    @OneToMany(mappedBy = "retour", cascade = CascadeType.ALL)
    private List<LigneRetour> lignesRetour = new ArrayList<>();

    private String motifRetour;
    private String commentaireClient;
    private LocalDateTime dateDemande;
    private LocalDateTime dateTraitement;
    private String reponse;

    public RetourClient(Long id, Utilisateur utilisateur, double totalHT, double totalTTC, double tva, int totalElements, LocalDateTime dateCreation, String typeRetour, String raison, String statut, String modeRetour, LocalDate dateApplication) {
        super(id, utilisateur, totalHT, totalTTC, tva, totalElements, dateCreation, typeRetour, raison, statut, modeRetour, dateApplication);
    }

    public RetourClient(String typeRetour, String raison, String statut, String modeRetour, LocalDate dateApplication) {
        super(typeRetour, raison, statut, modeRetour, dateApplication);
    }

    public RetourClient(Long id, Utilisateur utilisateur, double totalHT, double totalTTC, double tva, int totalElements, LocalDateTime dateCreation, String typeRetour, String raison, String statut, String modeRetour, LocalDate dateApplication, Commande commande, List<LigneRetour> lignesRetour, String motifRetour, String commentaireClient, LocalDateTime dateDemande, LocalDateTime dateTraitement,String reponse) {
        super(id, utilisateur, totalHT, totalTTC, tva, totalElements, dateCreation, typeRetour, raison, statut, modeRetour, dateApplication);
        this.commande = commande;
        this.lignesRetour = lignesRetour;
        this.motifRetour = motifRetour;
        this.commentaireClient = commentaireClient;
        this.dateDemande = dateDemande;
        this.dateTraitement = dateTraitement;
        this.reponse = reponse;
    }

    public RetourClient(String typeRetour, String raison, String statut, String modeRetour, LocalDate dateApplication, Commande commande, List<LigneRetour> lignesRetour, String motifRetour, String commentaireClient, LocalDateTime dateDemande, LocalDateTime dateTraitement,String reponse) {
        super(typeRetour, raison, statut, modeRetour, dateApplication);
        this.commande = commande;
        this.lignesRetour = lignesRetour;
        this.motifRetour = motifRetour;
        this.commentaireClient = commentaireClient;
        this.dateDemande = dateDemande;
        this.dateTraitement = dateTraitement;
        this.reponse = reponse;
    }

    public RetourClient(Commande commande, List<LigneRetour> lignesRetour, String motifRetour, String commentaireClient, LocalDateTime dateDemande, LocalDateTime dateTraitement,String reponse) {
        this.commande = commande;
        this.lignesRetour = lignesRetour;
        this.motifRetour = motifRetour;
        this.commentaireClient = commentaireClient;
        this.dateDemande = dateDemande;
        this.dateTraitement = dateTraitement;
        this.reponse = reponse;
    }

    public RetourClient() {

    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public List<LigneRetour> getLignesRetour() {
        return lignesRetour;
    }

    public void setLignesRetour(List<LigneRetour> lignesRetour) {
        this.lignesRetour = lignesRetour;
    }

    public String getMotifRetour() {
        return motifRetour;
    }

    public void setMotifRetour(String motifRetour) {
        this.motifRetour = motifRetour;
    }

    public String getCommentaireClient() {
        return commentaireClient;
    }

    public void setCommentaireClient(String commentaireClient) {
        this.commentaireClient = commentaireClient;
    }

    public LocalDateTime getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDateTime dateDemande) {
        this.dateDemande = dateDemande;
    }

    public LocalDateTime getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(LocalDateTime dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}
