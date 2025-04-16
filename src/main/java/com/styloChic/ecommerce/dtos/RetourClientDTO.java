package com.styloChic.ecommerce.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RetourClientDTO {

    private Long id;
    private Long commandeId;
    private String numeroCommande;
    private String typeRetour;
    private String statut;
    private String motifRetour;
    private String commentaireClient;
    private LocalDateTime dateDemande;
    private LocalDateTime dateTraitement;
    private String reponseAdmin;
    private List<LigneRetourDTO> lignesRetour;



    public RetourClientDTO() {
    }

    public RetourClientDTO(Long id, Long commandeId, String numeroCommande, String typeRetour, String statut, String motifRetour, String commentaireClient, LocalDateTime dateDemande, LocalDateTime dateTraitement,String reponseAdmin, List<LigneRetourDTO> lignesRetour) {
        this.id = id;
        this.commandeId = commandeId;
        this.numeroCommande = numeroCommande;
        this.typeRetour = typeRetour;
        this.statut = statut;
        this.motifRetour = motifRetour;
        this.commentaireClient = commentaireClient;
        this.dateDemande = dateDemande;
        this.dateTraitement = dateTraitement;
        this.reponseAdmin = reponseAdmin;
        this.lignesRetour = lignesRetour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }

    public String getNumeroCommande() {
        return numeroCommande;
    }

    public void setNumeroCommande(String numeroCommande) {
        this.numeroCommande = numeroCommande;
    }

    public String getTypeRetour() {
        return typeRetour;
    }

    public void setTypeRetour(String typeRetour) {
        this.typeRetour = typeRetour;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
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

    public String getReponseAdmin() {
        return reponseAdmin;
    }

    public void setReponseAdmin(String reponseAdmin) {
        this.reponseAdmin = reponseAdmin;
    }

    public List<LigneRetourDTO> getLignesRetour() {
        return lignesRetour;
    }

    public void setLignesRetour(List<LigneRetourDTO> lignesRetour) {
        this.lignesRetour = lignesRetour;
    }
}
