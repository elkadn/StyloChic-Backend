package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.models.LigneCommande;
import org.springframework.stereotype.Service;

@Service
public interface LigneCommandeService {

    public LigneCommande creerLigneCommande(LigneCommande ligneCommande);
}
