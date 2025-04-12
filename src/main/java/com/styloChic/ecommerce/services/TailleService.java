package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TailleService {

        @Autowired
        private ProduitRepository produitRepository;

    public List<String> getDistinctTailleNoms() {
        return produitRepository.findDistinctTailleNomsNative();
        // ou return produitRepository.findDistinctTailleNomsNative();
    }
    }
