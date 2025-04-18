package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.config.JwtProvider;
import com.styloChic.ecommerce.dtos.CodeReductionDTO;
import com.styloChic.ecommerce.exceptions.CodeReductionException;
import com.styloChic.ecommerce.models.CodeReduction;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.repositories.CodeReductionRepository;
import com.styloChic.ecommerce.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeReductionServiceImplementation implements CodeReductionService{

    @Autowired
    private CodeReductionRepository codeReductionRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public List<CodeReduction> chercherTousCodesPromo(String jwt) throws CodeReductionException {
        verifierAdmin(jwt);
        return codeReductionRepository.findAll();
    }

    @Override
    public CodeReduction ajouterCodePromo(CodeReductionDTO codeReductionDTO, String jwt) throws CodeReductionException {
        verifierAdmin(jwt);

        if (codeReductionRepository.findByCode(codeReductionDTO.getCode()) != null) {
            throw new CodeReductionException("Le code promo existe déjà !");
        }

        CodeReduction codeReduction = new CodeReduction();
        codeReduction.setCode(codeReductionDTO.getCode());
        codeReduction.setPourcentage(codeReductionDTO.getPourcentage());
        codeReduction.setDateDebut(codeReductionDTO.getDateDebut());
        codeReduction.setDateFin(codeReductionDTO.getDateFin());
        codeReduction.setActif(codeReductionDTO.isActif());
        codeReduction.setConditionReduction(codeReductionDTO.getConditionReduction());

        return codeReductionRepository.save(codeReduction);
    }

    @Override
    public CodeReduction chercherCodePromoParId(Long id, String jwt) throws CodeReductionException {
        verifierAdmin(jwt);
        return codeReductionRepository.findById(id)
                .orElseThrow(() -> new CodeReductionException("Code promo non trouvé avec ID: " + id));
    }

    @Override
    public CodeReduction mettreAJourCodePromo(Long id, CodeReductionDTO codeReductionDTO, String jwt) throws CodeReductionException {
        verifierAdmin(jwt);

        CodeReduction codeExistante = codeReductionRepository.findById(id)
                .orElseThrow(() -> new CodeReductionException("Code promo non trouvé avec l'ID: " + id));

        // Mise à jour
        codeExistante.setCode(codeReductionDTO.getCode());
        codeExistante.setPourcentage(codeReductionDTO.getPourcentage());
        codeExistante.setDateDebut(codeReductionDTO.getDateDebut());
        codeExistante.setDateFin(codeReductionDTO.getDateFin());
        codeExistante.setActif(codeReductionDTO.isActif());
        codeExistante.setConditionReduction(codeReductionDTO.getConditionReduction());

        return codeReductionRepository.save(codeExistante);
    }

    @Override
    public void supprimerCodePromo(Long id, String jwt) throws CodeReductionException {
        verifierAdmin(jwt);
        CodeReduction codePromo = codeReductionRepository.findById(id)
                .orElseThrow(() -> new CodeReductionException("Code promo non trouvé avec l'ID: " + id));
        codeReductionRepository.delete(codePromo);
    }

    private Utilisateur verifierAdmin(String jwt) throws CodeReductionException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(email);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            throw new CodeReductionException("Accès interdit : vous devez être un administrateur !");
        }
        return admin;
    }


    @Override
    public long compterCodePromos(String jwt) throws CodeReductionException {
        String adminEmail = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(adminEmail);

        if (admin == null || !admin.getRole().equals("ADMIN")) {
            throw new CodeReductionException("Accès interdit : vous devez être un administrateur pour effectuer cette action !");
        }

        return codeReductionRepository.count();
    }


}
