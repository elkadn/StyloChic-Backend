package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.config.JwtProvider;
import com.styloChic.ecommerce.dtos.CategorieDTO;
import com.styloChic.ecommerce.exceptions.CategorieException;
import com.styloChic.ecommerce.models.*;
import com.styloChic.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategorieServiceImplementation implements CategorieService{

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private NavBarCategorieRepository navBarCategorieRepository;

    @Autowired
    private NavBarSectionRepository navBarSectionRepository;

    @Autowired
    private NavBarElementRepository navBarElementRepository;

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
            Categorie parent = categorieRepository.findById(categorieDTO.getCategorieParenteId())
                    .orElseThrow(() -> new CategorieException("Catégorie parente non trouvée !"));
            categorie.setCategorieParente(parent);
        }

        Categorie savedCategorie = categorieRepository.save(categorie);

        switch (savedCategorie.getNiveau()) {
            case 1 -> {
                NavBarCategorie navBarCategorie = new NavBarCategorie();
                navBarCategorie.setNom(savedCategorie.getNom());
                navBarCategorie.setCategorie(savedCategorie);
                navBarCategorieRepository.save(navBarCategorie);
            }
            case 2 -> {
                Categorie parentCategorie = savedCategorie.getCategorieParente();
                if (parentCategorie == null) throw new CategorieException("La catégorie de niveau 2 doit avoir une catégorie parente de niveau 1");

                NavBarCategorie navBarCategorieParent = navBarCategorieRepository.findByNom(parentCategorie.getNom())
                        .orElseThrow(() -> new CategorieException("NavBarCategorie parent non trouvé"));

                NavBarSection navBarSection = new NavBarSection();
                navBarSection.setNom(savedCategorie.getNom());
                navBarSection.setNavBarCategorie(navBarCategorieParent);
                navBarSection.setCategorie(savedCategorie);
                navBarSectionRepository.save(navBarSection);
            }
            case 3 -> {
                Categorie parentCategorie = savedCategorie.getCategorieParente();
                if (parentCategorie == null) throw new CategorieException("La catégorie de niveau 3 doit avoir une catégorie parente de niveau 2");

                NavBarSection navBarSectionParent = navBarSectionRepository.findByNom(parentCategorie.getNom())
                        .orElseThrow(() -> new CategorieException("NavBarSection parent non trouvé"));

                NavBarElement navBarElement = new NavBarElement();
                navBarElement.setNom(savedCategorie.getNom());
                navBarElement.setNavBarSection(navBarSectionParent);
                navBarElement.setCategorie(savedCategorie);
                navBarElement.setElementId(savedCategorie.getNom().toLowerCase().replace(" ", "_"));
                navBarElementRepository.save(navBarElement);
            }
            default -> throw new CategorieException("Niveau de catégorie invalide !");
        }

        return savedCategorie;
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

        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new CategorieException("Catégorie non trouvée avec l'ID : " + id));

        List<Categorie> categoriesExistantes = categorieRepository.findAll();
        for (Categorie c : categoriesExistantes) {
            if (!c.getId().equals(id)) {
                boolean memeNom = c.getNom().equalsIgnoreCase(categorieDTO.getNom());
                boolean memeParent = (c.getCategorieParente() == null && categorieDTO.getCategorieParenteId() == null)
                        || (c.getCategorieParente() != null
                        && c.getCategorieParente().getId().equals(categorieDTO.getCategorieParenteId()));
                if (memeNom && memeParent) {
                    throw new CategorieException("Une autre catégorie existe déjà avec le même nom et parent !");
                }
            }
        }

        categorie.setNom(categorieDTO.getNom());
        categorie.setNiveau(categorieDTO.getNiveau());
        categorie.setDateModification(LocalDateTime.now());
        categorie.setAdmin(admin);

        if (categorieDTO.getCategorieParenteId() != null) {
            Categorie parent = categorieRepository.findById(categorieDTO.getCategorieParenteId())
                    .orElseThrow(() -> new CategorieException("Catégorie parente non trouvée !"));
            categorie.setCategorieParente(parent);
        } else {
            categorie.setCategorieParente(null);
        }

        Categorie savedCategorie = categorieRepository.save(categorie);

        switch (savedCategorie.getNiveau()) {
            case 1 -> {
                NavBarCategorie navBarCategorie = navBarCategorieRepository.findByCategorie(savedCategorie);
                if (navBarCategorie != null) {
                    navBarCategorie.setNom(savedCategorie.getNom());
                    navBarCategorieRepository.save(navBarCategorie);
                }
            }
            case 2 -> {
                NavBarSection navBarSection = navBarSectionRepository.findByCategorie(savedCategorie);
                if (navBarSection != null) {
                    navBarSection.setNom(savedCategorie.getNom());
                    navBarSectionRepository.save(navBarSection);
                }
            }
            case 3 -> {
                NavBarElement navBarElement = navBarElementRepository.findByCategorie(savedCategorie);
                if (navBarElement != null) {
                    navBarElement.setNom(savedCategorie.getNom());
                    navBarElement.setElementId(savedCategorie.getNom().toLowerCase().replace(" ", "_"));
                    navBarElementRepository.save(navBarElement);
                }
            }
            default -> throw new CategorieException("Niveau de catégorie invalide !");
        }

        return savedCategorie;
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

    @Override
    public List<String> getNomsCategoriesNiveau3() {
        return categorieRepository.findNomsByNiveau3();
    }


    @Override
    public long compterCategories(String jwt) throws CategorieException {
        String adminEmail = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(adminEmail);

        if (admin == null || !admin.getRole().equals("ADMIN")) {
            throw new CategorieException("Accès interdit : vous devez être un administrateur pour effectuer cette action !");
        }

        return categorieRepository.compterCategories();
    }


    @Override
    public Map<String, Double> avoirVentePaCategorie(String jwt) throws CategorieException {
        verifierAdmin(jwt);

        List<Object[]> results = categorieRepository.avoirVentePaCategorie();
        Map<String, Double> salesDistribution = new LinkedHashMap<>();

        List<Categorie> mainCategories = categorieRepository.findByNiveau(1);
        mainCategories.forEach(c -> salesDistribution.put(c.getNom(), 0.0));

        results.forEach(result -> {
            String categoryName = (String) result[0];
            Double totalSales = ((Number) result[1]).doubleValue();
            salesDistribution.put(categoryName, totalSales);
        });

        return salesDistribution;
    }

}
