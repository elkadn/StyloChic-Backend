package com.styloChic.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class LigneCommande extends LigneTransaction {

    @ManyToOne
    private Commande commande;

    @Column(name = "prix_ttc_reduit")
    private Double prixTTCReduit;


    public LigneCommande(Commande commande, double prixTTCReduit) {
        this.commande = commande;
        this.prixTTCReduit = prixTTCReduit;
    }

    public LigneCommande() {
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public double getPrixTTCReduit() {
        return prixTTCReduit;
    }

    public void setPrixTTCReduit(double prixTTCReduit) {
        this.prixTTCReduit = prixTTCReduit;
    }
}
