package com.styloChic.ecommerce.models;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class AdresseAchat extends Adresse{
    private String nomSociete;

    public AdresseAchat(Long id, String adresse, String ville, String region, String zipCode, String telephone, LocalDateTime dateAjout, String nomSociete,Utilisateur utilisateur){
        super(id,adresse,ville,region,zipCode,telephone,dateAjout,utilisateur);
        this.nomSociete = nomSociete;
    }

    public AdresseAchat() {

    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }
}
