package com.styloChic.ecommerce.requests;

public class VoteRequest {

    private Long idProduit;
    private double vote;

    public VoteRequest() {
    }

    public VoteRequest(Long idProduit, double vote) {
        this.idProduit = idProduit;
        this.vote = vote;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public double getVote() {
        return vote;
    }

    public void setVote(double vote) {
        this.vote = vote;
    }
}
