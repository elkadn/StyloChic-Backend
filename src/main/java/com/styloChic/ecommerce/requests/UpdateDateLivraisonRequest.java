package com.styloChic.ecommerce.requests;

import java.time.LocalDateTime;

public class UpdateDateLivraisonRequest {
    private LocalDateTime nouvelleDateLivraison;

    public LocalDateTime getNouvelleDateLivraison() {
        return nouvelleDateLivraison;
    }

    public void setNouvelleDateLivraison(LocalDateTime nouvelleDateLivraison) {
        this.nouvelleDateLivraison = nouvelleDateLivraison;
    }
}