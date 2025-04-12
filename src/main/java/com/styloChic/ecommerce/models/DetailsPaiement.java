package com.styloChic.ecommerce.models;

public class DetailsPaiement {

    private String methodePaiement;
    private String statusPaiement;


    private String paypalPaymentId;
    private String paypalPayerId;


    private String stripePaymentIntentId;
    private String stripeClientSecret;

    public DetailsPaiement() {
    }

    public DetailsPaiement(String methodePaiement, String statusPaiement, String paypalPaymentId, String paypalPayerId, String stripePaymentIntentId, String stripeClientSecret) {
        this.methodePaiement = methodePaiement;
        this.statusPaiement = statusPaiement;
        this.paypalPaymentId = paypalPaymentId;
        this.paypalPayerId = paypalPayerId;
        this.stripePaymentIntentId = stripePaymentIntentId;
        this.stripeClientSecret = stripeClientSecret;
    }

    public String getMethodePaiement() {
        return methodePaiement;
    }

    public void setMethodePaiement(String methodePaiement) {
        this.methodePaiement = methodePaiement;
    }

    public String getStatusPaiement() {
        return statusPaiement;
    }

    public void setStatusPaiement(String statusPaiement) {
        this.statusPaiement = statusPaiement;
    }


    public String getPaypalPaymentId() {
        return paypalPaymentId;
    }

    public void setPaypalPaymentId(String paypalPaymentId) {
        this.paypalPaymentId = paypalPaymentId;
    }

    public String getPaypalPayerId() {
        return paypalPayerId;
    }

    public void setPaypalPayerId(String paypalPayerId) {
        this.paypalPayerId = paypalPayerId;
    }

    public String getStripePaymentIntentId() {
        return stripePaymentIntentId;
    }

    public void setStripePaymentIntentId(String stripePaymentIntentId) {
        this.stripePaymentIntentId = stripePaymentIntentId;
    }

    public String getStripeClientSecret() {
        return stripeClientSecret;
    }

    public void setStripeClientSecret(String stripeClientSecret) {
        this.stripeClientSecret = stripeClientSecret;
    }
}
