package com.styloChic.ecommerce.responses;

public class AuthentificationResponse {

    private String jwt;
    private String message;
    private String role;
    private String email;
    private String nom;
    private String prenom;


    public AuthentificationResponse() {
    }

    public AuthentificationResponse(String jwt, String message, String role, String email, String nom, String prenom) {
        this.jwt = jwt;
        this.message = message;
        this.role = role;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
