package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.dtos.InspirationDTO;
import com.styloChic.ecommerce.exceptions.InspirationException;
import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Inspiration;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InspirationService {

    public Inspiration ajouterInspiration(Long produitId, MultipartFile image, String commentaire,String jwt) throws ProduitException, UtilisateurException,Exception;
    public List<InspirationDTO> recupererToutesInspirationsAvecTitreProduit();

    public InspirationDTO changerVisibiliteInspiration(Long inspirationId, boolean visibilite,String jwt) throws InspirationException, UtilisateurException;
}
