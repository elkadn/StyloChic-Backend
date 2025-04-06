package com.styloChic.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class AdresseCommande extends Adresse{

    private String prenom;
    private String nom;

    public AdresseCommande(Long id, String adresse, String ville, String region, String zipCode, String telephone, LocalDateTime dateAjout,String nom,String prenom,Utilisateur utilisateur){
        super(id,adresse,ville,region,zipCode,telephone,dateAjout,utilisateur);
        this.nom = nom;
        this.prenom = prenom;
    }

    public AdresseCommande() {

    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


}
