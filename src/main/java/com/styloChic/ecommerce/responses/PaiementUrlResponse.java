package com.styloChic.ecommerce.responses;

public class PaiementUrlResponse {
    private String paiement_liaison_url;
    private String paiement_liaison_id;


    public PaiementUrlResponse(){

    }

    public PaiementUrlResponse(String paiement_liaison_url, String paiement_liaison_id) {
        this.paiement_liaison_url = paiement_liaison_url;
        this.paiement_liaison_id = paiement_liaison_id;
    }

    public String getPaiement_liaison_url() {
        return paiement_liaison_url;
    }

    public void setPaiement_liaison_url(String paiement_liaison_url) {
        this.paiement_liaison_url = paiement_liaison_url;
    }

    public String getPaiement_liaison_id() {
        return paiement_liaison_id;
    }

    public void setPaiement_liaison_id(String paiement_liaison_id) {
        this.paiement_liaison_id = paiement_liaison_id;
    }
}
