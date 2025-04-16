package com.styloChic.ecommerce.requests;

import com.styloChic.ecommerce.dtos.LigneRetourDTO;

import java.util.List;

public class DemandeRetourRequest {

    private Long commandeId;
    private List<LigneRetourDTO> lignesRetour;
    private String motif;
    private String commentaire;

    public DemandeRetourRequest() {
    }

    public DemandeRetourRequest(Long commandeId, List<LigneRetourDTO> lignesRetour, String motif, String commentaire) {
        this.commandeId = commandeId;
        this.lignesRetour = lignesRetour;
        this.motif = motif;
        this.commentaire = commentaire;
    }

    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }

    public List<LigneRetourDTO> getLignesRetour() {
        return lignesRetour;
    }

    public void setLignesRetour(List<LigneRetourDTO> lignesRetour) {
        this.lignesRetour = lignesRetour;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
