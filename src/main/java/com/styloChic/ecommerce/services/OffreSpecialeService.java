package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.dtos.OffreSpecialeDTO;
import com.styloChic.ecommerce.exceptions.CategorieException;
import com.styloChic.ecommerce.exceptions.OffreSpecialeException;
import com.styloChic.ecommerce.exceptions.ProduitException;

import java.util.List;

public interface OffreSpecialeService {

    public List<OffreSpecialeDTO> chercherToutesOffres(String jwt) throws OffreSpecialeException;

    public OffreSpecialeDTO chercherOffreSpecialeParId(Long id, String jwt) throws OffreSpecialeException;


    public OffreSpecialeDTO ajouterOffreSpeciale(OffreSpecialeDTO offreDTO, String jwt) throws OffreSpecialeException, ProduitException, CategorieException;

    public OffreSpecialeDTO modifierOffreSpeciale(Long id, OffreSpecialeDTO offreDTO, String jwt) throws OffreSpecialeException,ProduitException,CategorieException;

    public void supprimerOffreSpeciale(Long id, String jwt) throws OffreSpecialeException;

    public void changerVisibiliteOffreSpeciale(Long id,String jwt,boolean visibilite) throws OffreSpecialeException;

}
