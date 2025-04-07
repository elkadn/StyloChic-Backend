package com.styloChic.ecommerce.mappers;

import com.styloChic.ecommerce.dtos.OffreSpecialeDTO;
import com.styloChic.ecommerce.models.OffreSpeciale;

import java.util.List;
import java.util.stream.Collectors;

public class OffreSpecialeMapper {

    public static OffreSpecialeDTO toDTO(OffreSpeciale offre) {
        OffreSpecialeDTO dto = new OffreSpecialeDTO();
        dto.setNom(offre.getNom());
        dto.setUrlImage(offre.getUrlImage());
        dto.setDescriptionImage(offre.getDescriptionImage());
        dto.setLien(offre.getLien());
        dto.setProduitId(offre.getProduit().getId());
        dto.setNavBarCategorieId(offre.getNavBarCategorie().getId());
        dto.setVisibilite(offre.isVisibilite());
        return dto;
    }

    public static List<OffreSpecialeDTO> toDTOList(List<OffreSpeciale> offres) {
        return offres.stream().map(OffreSpecialeMapper::toDTO).collect(Collectors.toList());
    }
}
