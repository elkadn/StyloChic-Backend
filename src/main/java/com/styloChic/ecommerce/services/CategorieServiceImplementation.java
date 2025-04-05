package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.config.JwtProvider;
import com.styloChic.ecommerce.dtos.CategorieDTO;
import com.styloChic.ecommerce.exceptions.CategorieException;
import com.styloChic.ecommerce.models.Categorie;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.repositories.CategorieRepository;
import com.styloChic.ecommerce.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategorieServiceImplementation implements CategorieService{

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public List<Categorie> chercherTousCategories(String jwt) throws CategorieException {
        verifierAdmin(jwt);
        return categorieRepository.findAll();
    }
    @Override
    public Categorie ajouterCategorie(CategorieDTO categorieDTO, String jwt) throws CategorieException {
        Utilisateur admin = verifierAdmin(jwt);

        List<Categorie> categoriesExistantes = categorieRepository.findAll();

        for (Categorie c : categoriesExistantes) {
            boolean memeNom = c.getNom().equalsIgnoreCase(categorieDTO.getNom());
            boolean memeParent = (c.getCategorieParente() == null && categorieDTO.getCategorieParenteId() == null)
                    || (c.getCategorieParente() != null
                    && c.getCategorieParente().getId().equals(categorieDTO.getCategorieParenteId()));
            if (memeNom && memeParent) {
                throw new CategorieException("Catégorie déjà existante avec le même nom et parent !");
            }
        }

        Categorie categorie = new Categorie();
        categorie.setNom(categorieDTO.getNom());
        categorie.setNiveau(categorieDTO.getNiveau());
        categorie.setDateCreation(LocalDateTime.now());
        categorie.setDateModification(LocalDateTime.now());
        categorie.setAdmin(admin);

        if (categorieDTO.getCategorieParenteId() != null) {
            Categorie categorieParente = categorieRepository.findById(categorieDTO.getCategorieParenteId())
                    .orElseThrow(() -> new CategorieException("Catégorie parente introuvable !"));
            categorie.setCategorieParente(categorieParente);
        }

        return categorieRepository.save(categorie);
    }

    public Categorie chercherCategorieParId(Long id, String jwt) throws CategorieException {
        verifierAdmin(jwt);
        return categorieRepository.findById(id)
                .orElseThrow(() -> new CategorieException("Catégorie non trouvée avec ID: " + id));
    }

    private Utilisateur verifierAdmin(String jwt) throws CategorieException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(email);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            throw new CategorieException("Accès interdit : vous devez être un administrateur !");
        }
        return admin;
    }

    @Override
    public Categorie mettreAJourCategorie(Long id, CategorieDTO categorieDTO, String jwt) throws CategorieException {
        Utilisateur admin = verifierAdmin(jwt);

        Categorie categorieExistante = categorieRepository.findById(id)
                .orElseThrow(() -> new CategorieException("Catégorie non trouvée avec l'id : " + id));

        for (Categorie existe : categorieRepository.findAll()) {
            boolean memeNom = existe.getNom().equalsIgnoreCase(categorieDTO.getNom());
            boolean memeParent = (existe.getCategorieParente() == null && categorieDTO.getCategorieParenteId() == null) ||
                    (existe.getCategorieParente() != null &&
                            existe.getCategorieParente().getId().equals(categorieDTO.getCategorieParenteId()));

            if (memeNom && memeParent && !existe.getId().equals(id)) {
                throw new CategorieException("Une catégorie avec ce nom et cette catégorie parente existe déjà !");
            }
        }

        categorieExistante.setNom(categorieDTO.getNom());
        categorieExistante.setNiveau(categorieDTO.getNiveau());
        categorieExistante.setDateModification(LocalDateTime.now());
        categorieExistante.setAdmin(admin);

        if (categorieDTO.getCategorieParenteId() != null) {
            Categorie parent = categorieRepository.findById(categorieDTO.getCategorieParenteId())
                    .orElseThrow(() -> new CategorieException("Catégorie parente introuvable !"));
            categorieExistante.setCategorieParente(parent);
        } else {
            categorieExistante.setCategorieParente(null);
        }

        return categorieRepository.save(categorieExistante);
    }


    @Override
    public void supprimerCategorie(Long id, String jwt) throws CategorieException {
        Utilisateur admin = verifierAdmin(jwt);

        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new CategorieException("Catégorie non trouvée avec l'id : " + id));

        if (categorieRepository.countByCategorieParente(categorie) > 0) {
            throw new CategorieException("Impossible de supprimer cette catégorie car elle est utilisée comme catégorie parente !");
        }

        categorieRepository.delete(categorie);
    }


}
