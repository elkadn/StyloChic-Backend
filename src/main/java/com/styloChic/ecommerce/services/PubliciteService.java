package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.config.JwtProvider;
import com.styloChic.ecommerce.dtos.PubliciteDTO;
import com.styloChic.ecommerce.exceptions.PubiliciteException;
import com.styloChic.ecommerce.models.Publicite;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.repositories.PubliciteRepository;
import com.styloChic.ecommerce.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PubliciteService {

    @Autowired
    private PubliciteRepository publiciteRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private JwtProvider jwtProvider;

    public List<Publicite> avoirToutesLesPublicites() {
        return publiciteRepository.findAll();
    }

    public Publicite avoirPubliciteParId(Long id) {
        return publiciteRepository.findById(id).orElse(null);
    }

    public PubliciteDTO ajouterPublicite(PubliciteDTO publiciteDTO, String jwt) throws PubiliciteException {
        Utilisateur admin = verifierAdmin(jwt);

        Publicite publicite = new Publicite();
        publicite.setUrlImage(publiciteDTO.getImageUrl());
        publicite.setStructure(publiciteDTO.getStructure());
        publicite.setDateAjout(LocalDateTime.now());
        publicite.setDateModification(LocalDateTime.now());
        publicite.setAdmin(admin);

        publiciteRepository.save(publicite);

        return new PubliciteDTO(publicite.getUrlImage(), publicite.getStructure());
    }

    public PubliciteDTO modifierPublicite(Long id, PubliciteDTO publiciteDTO, String jwt) throws PubiliciteException {
        Utilisateur admin = verifierAdmin(jwt);

        Publicite publiciteExistante = publiciteRepository.findById(id).orElseThrow(() -> new PubiliciteException("Publicité introuvable"));

        publiciteExistante.setUrlImage(publiciteDTO.getImageUrl());
        publiciteExistante.setStructure(publiciteDTO.getStructure());
        publiciteExistante.setDateModification(LocalDateTime.now());
        publiciteExistante.setAdmin(admin);

        publiciteRepository.save(publiciteExistante);

        return new PubliciteDTO(publiciteExistante.getUrlImage(), publiciteExistante.getStructure());
    }

    public void supprimerPublicite(Long id, String jwt) throws PubiliciteException {
        verifierAdmin(jwt);

        if (!publiciteRepository.existsById(id)) {
            throw new PubiliciteException("Publicité avec l'ID " + id + " introuvable !");
        }

        publiciteRepository.deleteById(id);
    }

    private Utilisateur verifierAdmin(String jwt) throws PubiliciteException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(email);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            throw new PubiliciteException("Accès interdit : vous devez être un administrateur !");
        }
        return admin;
    }
}
