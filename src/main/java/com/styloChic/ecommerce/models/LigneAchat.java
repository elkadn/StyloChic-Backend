package com.styloChic.ecommerce.models;

import jakarta.persistence.*;

@Entity
public class LigneAchat extends LigneTransaction {

    @ManyToOne
    private Achat achat;

    public LigneAchat(Long id, Produit produit, String taille, int quantite, Double prixHT, Double prixTTC, double tva, Long utilisateurId, Achat achat) {
        super(id, produit, taille, quantite, prixHT, prixTTC, tva, utilisateurId);
        this.achat = achat;
    }

    public LigneAchat(Achat achat) {
        this.achat = achat;
    }


    public LigneAchat() {

    }

    public Achat getAchat() {
        return achat;
    }

    public void setAchat(Achat achat) {
        this.achat = achat;
    }
}
