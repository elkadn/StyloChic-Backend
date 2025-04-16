package com.styloChic.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;


@Entity
public class LigneRetour extends LigneTransaction{

    @ManyToOne
    private RetourClient retour;

    private String raisonRetour;
    private String etatProduit;


    public LigneRetour(Long id, Produit produit, String taille, int quantite, Double prixHT, Double prixTTC, double tva, Long utilisateurId, RetourClient retour, String raisonRetour, String etatProduit) {
        super(id, produit, taille, quantite, prixHT, prixTTC, tva, utilisateurId);
        this.retour = retour;
        this.raisonRetour = raisonRetour;
        this.etatProduit = etatProduit;
    }

    public LigneRetour(RetourClient retour, String raisonRetour, String etatProduit) {
        this.retour = retour;
        this.raisonRetour = raisonRetour;
        this.etatProduit = etatProduit;
    }

    public LigneRetour() {

    }

    public RetourClient getRetour() {
        return retour;
    }

    public void setRetour(RetourClient retour) {
        this.retour = retour;
    }

    public String getRaisonRetour() {
        return raisonRetour;
    }

    public void setRaisonRetour(String raisonRetour) {
        this.raisonRetour = raisonRetour;
    }

    public String getEtatProduit() {
        return etatProduit;
    }

    public void setEtatProduit(String etatProduit) {
        this.etatProduit = etatProduit;
    }
}
