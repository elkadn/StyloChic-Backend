package com.styloChic.ecommerce.services;


import com.styloChic.ecommerce.config.JwtProvider;
import com.styloChic.ecommerce.dtos.DemandePersonnalisationDTO;
import com.styloChic.ecommerce.exceptions.DemandePersonnalisationException;
import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.DemmandePersonnalisation;
import com.styloChic.ecommerce.models.Produit;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.repositories.DemandePersonnalisationRepository;
import com.styloChic.ecommerce.repositories.UtilisateurRepository;
import com.styloChic.ecommerce.requests.DemandePersonnalisationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemandePersonnalisationServiceImplementation implements DemandePersonnalisationService{

    @Autowired
    private DemandePersonnalisationRepository demandeRepository;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurService utilisateurService;


    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public DemandePersonnalisationDTO creerDemandePersonnalisation(DemandePersonnalisationRequest demandeDTO, String jwt)
            throws ProduitException, UtilisateurException {

        Produit produit = produitService.chercherProduit(demandeDTO.getProduitId());
        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);

        DemmandePersonnalisation demande = new DemmandePersonnalisation();
        demande.setDateAjout(LocalDateTime.now());
        demande.setCommentaire(demandeDTO.getCommentaire());
        demande.setTaille(demandeDTO.getTaille());
        demande.setCouleur(demandeDTO.getCouleur());
        demande.setDesign(demandeDTO.getDesign());
        demande.setStatut("En attente");
        demande.setReponse(null);
        demande.setProduit(produit);
        demande.setUtilisateur(utilisateur);

        DemmandePersonnalisation savedDemande = demandeRepository.save(demande);
        return convertToDTO(savedDemande);
    }
    @Override
    public List<DemandePersonnalisationDTO> recupererToutesDemandesPersonnalisation() {
        return demandeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DemandePersonnalisationDTO ajouterReponseAdmin(Long demandeId, String reponse, String statut, String jwt)
            throws UtilisateurException,DemandePersonnalisationException {

        verifierAdmin(jwt);

        DemmandePersonnalisation demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new DemandePersonnalisationException("Demande non trouvée avec id: " + demandeId));

        demande.setReponse(reponse);
        demande.setStatut(statut);

        DemmandePersonnalisation updatedDemande = demandeRepository.save(demande);
        return convertToDTO(updatedDemande);
    }

    private DemandePersonnalisationDTO convertToDTO(DemmandePersonnalisation demande) {
        DemandePersonnalisationDTO dto = new DemandePersonnalisationDTO();
        dto.setId(demande.getId());
        dto.setDateAjout(demande.getDateAjout());
        dto.setCommentaire(demande.getCommentaire());
        dto.setReponse(demande.getReponse());
        dto.setTaille(demande.getTaille());
        dto.setCouleur(demande.getCouleur());
        dto.setDesign(demande.getDesign());
        dto.setStatut(demande.getStatut());
        dto.setTitreProduit(demande.getProduit().getTitre());
        dto.setNomUtilisateur(demande.getUtilisateur().getNom() + " " + demande.getUtilisateur().getPrenom());
        return dto;
    }

    private void verifierAdmin(String jwt) throws UtilisateurException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(email);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            throw new UtilisateurException("Accès interdit : vous devez être administrateur !");
        }
    }

    @Override
    public List<DemandePersonnalisationDTO> recupererDemandesPersonnalisationParUtilisateur(String jwt)
            throws UtilisateurException {
        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);
        return demandeRepository.findByUtilisateur(utilisateur).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
