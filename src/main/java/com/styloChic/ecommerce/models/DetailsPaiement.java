package com.styloChic.ecommerce.models;

public class DetailsPaiement {
    private MethodePaiement methodePaiement;
    private String statusPaiement;
    private String idPaiement;
    private String idPaypalPaiement;
    private String idPaypalPayeur;
    private String stripePaiementIntentId;
    private String stripeClientSecret;
    private double montantPaye;
    private double fraisTransaction;
    private String paypalEmail;
    private String paypalAdresse;

    public void setStripeDetails(String intentId, String clientSecret, double amount) {
        this.methodePaiement = MethodePaiement.STRIPE;
        this.stripePaiementIntentId = intentId;
        this.stripeClientSecret = clientSecret;
        this.montantPaye = amount;
    }

    public void setPaypalDetails(String paypalId, String payerId, double amount, String email) {
        this.methodePaiement = MethodePaiement.PAYPAL;
        this.idPaypalPaiement = paypalId;
        this.idPaypalPayeur = payerId;
        this.montantPaye = amount;
        this.paypalEmail = email;
    }

    public DetailsPaiement() {
    }

    public DetailsPaiement(MethodePaiement methodePaiement, String statusPaiement, String idPaiement, String idPaypalPaiement, String idPaypalPayeur, String stripePaiementIntentId, String stripeClientSecret, double montantPaye, double fraisTransaction, String paypalEmail, String paypalAdresse) {
        this.methodePaiement = methodePaiement;
        this.statusPaiement = statusPaiement;
        this.idPaiement = idPaiement;
        this.idPaypalPaiement = idPaypalPaiement;
        this.idPaypalPayeur = idPaypalPayeur;
        this.stripePaiementIntentId = stripePaiementIntentId;
        this.stripeClientSecret = stripeClientSecret;
        this.montantPaye = montantPaye;
        this.fraisTransaction = fraisTransaction;
        this.paypalEmail = paypalEmail;
        this.paypalAdresse = paypalAdresse;
    }

    public MethodePaiement getMethodePaiement() {
        return methodePaiement;
    }

    public void setMethodePaiement(MethodePaiement methodePaiement) {
        this.methodePaiement = methodePaiement;
    }

    public String getStatusPaiement() {
        return statusPaiement;
    }

    public void setStatusPaiement(String statusPaiement) {
        this.statusPaiement = statusPaiement;
    }

    public String getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(String idPaiement) {
        this.idPaiement = idPaiement;
    }

    public String getIdPaypalPaiement() {
        return idPaypalPaiement;
    }

    public void setIdPaypalPaiement(String idPaypalPaiement) {
        this.idPaypalPaiement = idPaypalPaiement;
    }

    public String getIdPaypalPayeur() {
        return idPaypalPayeur;
    }

    public void setIdPaypalPayeur(String idPaypalPayeur) {
        this.idPaypalPayeur = idPaypalPayeur;
    }

    public String getStripePaiementIntentId() {
        return stripePaiementIntentId;
    }

    public void setStripePaiementIntentId(String stripePaiementIntentId) {
        this.stripePaiementIntentId = stripePaiementIntentId;
    }

    public String getStripeClientSecret() {
        return stripeClientSecret;
    }

    public void setStripeClientSecret(String stripeClientSecret) {
        this.stripeClientSecret = stripeClientSecret;
    }

    public double getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(double montantPaye) {
        this.montantPaye = montantPaye;
    }

    public double getFraisTransaction() {
        return fraisTransaction;
    }

    public void setFraisTransaction(double fraisTransaction) {
        this.fraisTransaction = fraisTransaction;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    public void setPaypalEmail(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }

    public String getPaypalAdresse() {
        return paypalAdresse;
    }

    public void setPaypalAdresse(String paypalAdresse) {
        this.paypalAdresse = paypalAdresse;
    }
}
