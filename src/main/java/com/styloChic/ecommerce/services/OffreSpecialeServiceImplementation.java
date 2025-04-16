package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.config.JwtProvider;
import com.styloChic.ecommerce.dtos.OffreSpecialeDTO;
import com.styloChic.ecommerce.exceptions.CategorieException;
import com.styloChic.ecommerce.exceptions.OffreSpecialeException;
import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.mappers.OffreSpecialeMapper;
import com.styloChic.ecommerce.models.*;
import com.styloChic.ecommerce.repositories.OffreSpecialeRepository;
import com.styloChic.ecommerce.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class OffreSpecialeServiceImplementation implements OffreSpecialeService{

    @Autowired
    private OffreSpecialeRepository offreSpecialeRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private NavBarCategorieService navBarCategorieService;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public List<OffreSpecialeDTO> chercherToutesOffres(String jwt) throws OffreSpecialeException {
        verifierAdmin(jwt);
        List<OffreSpeciale> offres = offreSpecialeRepository.findAll();
        return OffreSpecialeMapper.toDTOList(offres);
    }

    @Override
    public OffreSpecialeDTO chercherOffreSpecialeParId(Long id, String jwt) throws OffreSpecialeException {
        verifierAdmin(jwt);
        OffreSpeciale offre = offreSpecialeRepository.findById(id)
                .orElseThrow(() -> new OffreSpecialeException("Offre spéciale non trouvée avec l'id : " + id));
        return OffreSpecialeMapper.toDTO(offre);
    }



    @Override
    public OffreSpecialeDTO ajouterOffreSpeciale(OffreSpecialeDTO offreDTO, String jwt) throws OffreSpecialeException , ProduitException, CategorieException {
        Utilisateur admin = verifierAdmin(jwt);

        Produit produit = produitService.chercherProduit(offreDTO.getProduitId());
        NavBarCategorie navBarCategorie = navBarCategorieService.avoirNavBarCategorieParId(offreDTO.getNavBarCategorieId());

        OffreSpeciale offre = new OffreSpeciale();
        offre.setUrlImage(offreDTO.getUrlImage());
        offre.setDescriptionImage(offreDTO.getDescriptionImage());
        offre.setLien(offreDTO.getLien());
        offre.setNom(offreDTO.getNom());
        offre.setVisibilite(offreDTO.isVisibilite());
        offre.setProduit(produit);
        offre.setDateAjout(LocalDateTime.now());
        offre.setDateModification(LocalDateTime.now());
        offre.setAdmin(admin);
        offre.setNavBarCategorie(navBarCategorie);

        OffreSpeciale enregistree =  offreSpecialeRepository.save(offre);
        return OffreSpecialeMapper.toDTO(enregistree);
    }

    @Override
    public OffreSpecialeDTO modifierOffreSpeciale(Long id, OffreSpecialeDTO offreDTO, String jwt) throws OffreSpecialeException,ProduitException,CategorieException {
        Utilisateur admin = verifierAdmin(jwt);
        Produit produit = produitService.chercherProduit(offreDTO.getProduitId());
        NavBarCategorie navBarCategorie = navBarCategorieService.avoirNavBarCategorieParId(offreDTO.getNavBarCategorieId());

        OffreSpeciale offre = offreSpecialeRepository.findById(id)
                .orElseThrow(() -> new OffreSpecialeException("Offre spéciale non trouvée avec id : "+id));

        offre.setUrlImage(offreDTO.getUrlImage());
        offre.setDescriptionImage(offreDTO.getDescriptionImage());
        offre.setLien(offreDTO.getLien());
        offre.setNom(offreDTO.getNom());
        offre.setVisibilite(offreDTO.isVisibilite());
        offre.setProduit(produit);
        offre.setDateModification(LocalDateTime.now());
        offre.setAdmin(admin);
        offre.setNavBarCategorie(navBarCategorie);

        OffreSpeciale enregistree =  offreSpecialeRepository.save(offre);
        return OffreSpecialeMapper.toDTO(enregistree);
    }

    @Override
    public void supprimerOffreSpeciale(Long id, String jwt) throws OffreSpecialeException {
        verifierAdmin(jwt);

        OffreSpeciale offre = offreSpecialeRepository.findById(id)
                .orElseThrow(() -> new OffreSpecialeException("Offre spéciale trouvée avec id : "+id));

        offreSpecialeRepository.delete(offre);
    }

    private Utilisateur verifierAdmin(String jwt) throws OffreSpecialeException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(email);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            throw new OffreSpecialeException("Accès interdit : vous devez être un administrateur !");
        }
        return admin;
    }

    public void changerVisibiliteOffreSpeciale(Long id, String jwt, boolean visibilite) throws OffreSpecialeException {
        verifierAdmin(jwt);
        OffreSpeciale offreSpeciale = offreSpecialeRepository.findById(id)
                .orElseThrow(() -> new OffreSpecialeException("Offre non trouvée avec l'id : " + id));

        offreSpeciale.setVisibilite(visibilite);
        offreSpecialeRepository.save(offreSpeciale);
    }
}
