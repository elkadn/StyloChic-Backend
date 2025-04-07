package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.exceptions.InspirationException;
import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.models.Inspiration;
import org.springframework.web.multipart.MultipartFile;

public interface InspirationService {

    public Inspiration ajouterInspiration(Long produitId, MultipartFile image, String commentaire) throws InspirationException, ProduitException,Exception;

}
