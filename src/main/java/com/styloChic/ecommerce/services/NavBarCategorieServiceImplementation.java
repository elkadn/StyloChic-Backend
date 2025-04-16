package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.dtos.ElementDTO;
import com.styloChic.ecommerce.dtos.NavBarCategorieDTO;
import com.styloChic.ecommerce.dtos.OffreSpecialeDTO;
import com.styloChic.ecommerce.dtos.SectionDTO;
import com.styloChic.ecommerce.exceptions.CategorieException;
import com.styloChic.ecommerce.models.NavBarCategorie;
import com.styloChic.ecommerce.models.NavBarElement;
import com.styloChic.ecommerce.models.NavBarSection;
import com.styloChic.ecommerce.models.OffreSpeciale;
import com.styloChic.ecommerce.repositories.NavBarCategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NavBarCategorieServiceImplementation implements NavBarCategorieService {

    @Autowired
    private NavBarCategorieRepository navBarCategorieRepository;


    /*@Override
    public List<NavBarCategorie> avoirTousNavBarCategories() throws CategorieException {
        return navBarCategorieRepository.findAll();
    }*/

    @Override
    public List<NavBarCategorieDTO> avoirTousNavBarCategories() throws CategorieException {
        List<NavBarCategorie> categories = navBarCategorieRepository.findAll();
        List<NavBarCategorieDTO> categoryDTOs = new ArrayList<>();

        for (NavBarCategorie categorie : categories) {
            // Mapper les offres spéciales
            List<OffreSpecialeDTO> offreSpecialeDTOs = new ArrayList<>();
            for (OffreSpeciale offre : categorie.getOffresSpeciales()) {
                OffreSpecialeDTO dto = new OffreSpecialeDTO(
                        offre.getId(),
                        offre.getNom(),
                        offre.getUrlImage(),
                        offre.getDescriptionImage(),
                        offre.getLien(),
                        offre.isVisibilite(),
                        offre.getProduit() != null ? offre.getProduit().getId() : null,
                        offre.getNavBarCategorie() != null ? offre.getNavBarCategorie().getId() : null
                );
                offreSpecialeDTOs.add(dto);
            }

            // Mapper les sections et les éléments associés
            List<SectionDTO> sectionDTOs = new ArrayList<>();
            for (NavBarSection section : categorie.getNavBarSections()) {
                // Mapper les éléments de la section
                List<ElementDTO> elementDTOs = new ArrayList<>();
                for (NavBarElement element : section.getNavBarElements()) {
                    ElementDTO elementDTO = new ElementDTO(
                            element.getId(),
                            element.getNom(),
                            element.getElementId()
                    );
                    elementDTOs.add(elementDTO);
                }

                // Créer le SectionDTO
                SectionDTO sectionDTO = new SectionDTO(
                        section.getId(),
                        section.getNom(),
                        elementDTOs
                );
                sectionDTOs.add(sectionDTO);
            }

            // Créer le NavBarCategorieDTO avec les offres spéciales et les sections
            NavBarCategorieDTO categorieDTO = new NavBarCategorieDTO(
                    categorie.getId(),
                    categorie.getNom(),
                    offreSpecialeDTOs,
                    sectionDTOs
            );

            categoryDTOs.add(categorieDTO);
        }

        return categoryDTOs;
    }



    @Override
    public NavBarCategorie avoirNavBarCategorieParId(Long id) throws CategorieException {
        return navBarCategorieRepository.findById(id)
                .orElseThrow(() -> new CategorieException("Aucune nav bar categorie trouvé avec id " + id));
    }

    @Override
    public NavBarCategorieDTO avoirNavBarCategorieParIdParticulier(Long id) throws CategorieException {
        NavBarCategorie entity = navBarCategorieRepository.findById(id)
                .orElseThrow(() -> new CategorieException("Aucune nav bar categorie trouvé avec id " + id));

        return new NavBarCategorieDTO(
                entity.getId(),
                entity.getNom()
        );
    }

}
