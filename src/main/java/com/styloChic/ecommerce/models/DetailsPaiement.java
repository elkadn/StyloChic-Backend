package com.styloChic.ecommerce.models;

public class DetailsPaiement {
    private String paymentMethod;
    private String status;
    private String paymentId;


    private String paypalPaymentId;
    private String paypalPayerId;


    private String stripePaymentIntentId;
    private String stripeClientSecret;

    public DetailsPaiement() {
    }

    public DetailsPaiement(String paymentMethod, String status, String paymentId, String paypalPaymentId, String paypalPayerId, String stripePaymentIntentId, String stripeClientSecret) {
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.paymentId = paymentId;
        this.paypalPaymentId = paypalPaymentId;
        this.paypalPayerId = paypalPayerId;
        this.stripePaymentIntentId = stripePaymentIntentId;
        this.stripeClientSecret = stripeClientSecret;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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
