package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.models.LigneCommande;
import com.styloChic.ecommerce.repositories.LigneCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LigneCommandeServiceImplementation implements LigneCommandeService{

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;


    @Override
    public LigneCommande creerLigneCommande(LigneCommande ligneCommande) {
        return ligneCommandeRepository.save(ligneCommande);
    }

}
