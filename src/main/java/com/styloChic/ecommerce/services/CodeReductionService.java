package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.dtos.CodeReductionDTO;
import com.styloChic.ecommerce.exceptions.CodeReductionException;
import com.styloChic.ecommerce.models.CodeReduction;

import java.util.List;

public interface CodeReductionService {

    List<CodeReduction> chercherTousCodesPromo(String jwt) throws CodeReductionException;
    CodeReduction ajouterCodePromo(CodeReductionDTO codeReductionDTO, String jwt) throws CodeReductionException;
    CodeReduction chercherCodePromoParId(Long id, String jwt) throws CodeReductionException;
    CodeReduction mettreAJourCodePromo(Long id, CodeReductionDTO codeReductionDTO, String jwt) throws CodeReductionException;
    void supprimerCodePromo(Long id, String jwt) throws CodeReductionException;

    public long compterCodePromos(String jwt) throws CodeReductionException;
}
