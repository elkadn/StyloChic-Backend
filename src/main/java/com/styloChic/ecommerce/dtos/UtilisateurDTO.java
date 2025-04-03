package com.styloChic.ecommerce.dtos;

public class UtilisateurDTO {
    private Long id;
    private String prenom;
    private String nom;
    private String email;
    private String telephone;
    private String role;

    public UtilisateurDTO() {
    }

    public UtilisateurDTO(Long id, String prenom, String nom, String email, String telephone, String role) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

