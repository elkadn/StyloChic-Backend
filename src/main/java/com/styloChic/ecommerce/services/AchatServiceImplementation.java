package com.styloChic.ecommerce.services;


import com.styloChic.ecommerce.config.JwtProvider;
import com.styloChic.ecommerce.dtos.AchatDTO;
import com.styloChic.ecommerce.dtos.LigneAchatDTO;
import com.styloChic.ecommerce.exceptions.AchatException;
import com.styloChic.ecommerce.models.*;
import com.styloChic.ecommerce.repositories.*;
import com.styloChic.ecommerce.requests.AchatRequest;
import com.styloChic.ecommerce.requests.LigneAchatRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AchatServiceImplementation implements AchatService {

    @Autowired
    private AchatRepository achatRepository;

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private LigneAchatRepository ligneAchatRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private AdresseAchatRepository adresseAchatRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    @Transactional
    public AchatDTO creerAchat(AchatRequest request, String jwt) throws Exception {
        Utilisateur admin = verifierAdmin(jwt);
        Fournisseur fournisseur = fournisseurRepository.findById(request.getFournisseurId())
                .orElseThrow(() -> new Exception("Fournisseur non trouvé"));

        AdresseAchat adresseAchat = new AdresseAchat();
        adresseAchat.setNomSociete(request.getAdresseAchat().getNomSociete());
        adresseAchat.setAdresse(request.getAdresseAchat().getAdresse());
        adresseAchat.setVille(request.getAdresseAchat().getVille());
        adresseAchat.setRegion(request.getAdresseAchat().getRegion());
        adresseAchat.setZipCode(request.getAdresseAchat().getZipCode());
        adresseAchat.setTelephone(request.getAdresseAchat().getTelephone());
        adresseAchat.setDateAjout(LocalDateTime.now());

        Achat achat = new Achat();
        achat.setNumAchat(generateUniqueAchatNumber());
        achat.setDateAchat(LocalDateTime.now());
        achat.setDateCreation(LocalDateTime.now());
        achat.setStatutAchat("En attente");
        achat.setFournisseur(fournisseur);
        achat.setAdmin(admin);
        achat.setTotalHT(request.getTotalHT());
        achat.setTotalTTC(request.getTotalTTC());
        achat.setTva(request.getTva());
        achat.setUtilisateur(admin);
        achat.setAdresseReception(adresseAchat);

        Achat achatEnregistre = achatRepository.save(achat);

        List<LigneAchat> lignesAchat = new ArrayList<>();
        for (LigneAchatRequest ligneRequest : request.getLignesAchat()) {
            LigneAchat ligneAchat = new LigneAchat();
            Produit produit = produitRepository.findById(ligneRequest.getProduitId())
                    .orElseThrow(() -> new Exception("Produit non trouvé"));

            ligneAchat.setProduit(produit);
            ligneAchat.setQuantite(ligneRequest.getQuantite());
            ligneAchat.setPrixHT(ligneRequest.getPrixHT());
            ligneAchat.setPrixTTC(ligneRequest.getPrixTTC());
            ligneAchat.setTva(ligneRequest.getTva());
            ligneAchat.setAchat(achatEnregistre);
            ligneAchat.setTaille(ligneRequest.getTaille());
            ligneAchat.setUtilisateurId(admin.getId());

            lignesAchat.add(ligneAchatRepository.save(ligneAchat));
        }

        achatEnregistre.setLignesAchat(lignesAchat);
        return convertToDTO(achatRepository.save(achatEnregistre));
    }
    @Override
    public AchatDTO chercherAchatParId(Long achatId, String jwt) throws AchatException {
        verifierAdmin(jwt);
        Achat achat = achatRepository.findById(achatId)
                .orElseThrow(() -> new AchatException("Achat non trouvé"));
        return convertToDTO(achat);
    }

    @Override
    public List<AchatDTO> historiqueAchatsFournisseur(Long fournisseurId, String jwt) throws AchatException {
        verifierAdmin(jwt);
        List<Achat> achats = achatRepository.findByFournisseurId(fournisseurId);
        return achats.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public AchatDTO confirmerAchat(Long achatId, String jwt) throws Exception {
        verifierAdmin(jwt);
        Achat achat = achatRepository.findById(achatId)
                .orElseThrow(() -> new Exception("Achat non trouvé"));
        achat.setStatutAchat("Confirmé");
        return convertToDTO(achatRepository.save(achat));
    }

    @Override
    public AchatDTO receptionnerAchat(Long achatId, String jwt) throws Exception {
        verifierAdmin(jwt);
        Achat achat = achatRepository.findById(achatId)
                .orElseThrow(() -> new Exception("Achat non trouvé"));
        achat.setStatutAchat("Réceptionné");
        achat.setDateReception(LocalDateTime.now());

        // Mise à jour des stocks
        for (LigneAchat ligne : achat.getLignesAchat()) {
            Produit produit = ligne.getProduit();
            produit.setQuantiteEnStock(produit.getQuantiteEnStock() + ligne.getQuantite());
            produitRepository.save(produit);
        }

        return convertToDTO(achatRepository.save(achat));
    }

    @Override
    public AchatDTO annulerAchat(Long achatId, String jwt) throws Exception {
        verifierAdmin(jwt);
        Achat achat = achatRepository.findById(achatId)
                .orElseThrow(() -> new Exception("Achat non trouvé"));
        achat.setStatutAchat("Annulé");
        return convertToDTO(achatRepository.save(achat));
    }

    @Override
    public List<AchatDTO> avoirTousAchats(String jwt) throws AchatException {
        verifierAdmin(jwt);
        List<Achat> achats = achatRepository.findAll();
        return achats.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private String generateUniqueAchatNumber() {
        LocalDateTime now = LocalDateTime.now();
        String datePart = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String uniquePart = UUID.randomUUID().toString().substring(0, 8);
        return "ACH-" + datePart + "-" + uniquePart;
    }

    private AchatDTO convertToDTO(Achat achat) {
        AchatDTO dto = new AchatDTO();
        dto.setId(achat.getId());
        dto.setNumAchat(achat.getNumAchat());
        dto.setDateAchat(achat.getDateAchat());
        dto.setDateReception(achat.getDateReception());
        dto.setStatutAchat(achat.getStatutAchat());
        dto.setTotalHT(achat.getTotalHT());
        dto.setTotalTTC(achat.getTotalTTC());
        dto.setTva(achat.getTva());
        dto.setFournisseurNom(achat.getFournisseur().getNom());
        dto.setAdminNom(achat.getAdmin().getNom() + " "+ achat.getAdmin().getPrenom());
        dto.setAdresseReception(
                achat.getAdresseReception().getNomSociete() + ", " +
                        achat.getAdresseReception().getAdresse() + ", " +
                        achat.getAdresseReception().getVille() + " " +
                        achat.getAdresseReception().getRegion() + ", " +
                        achat.getAdresseReception().getZipCode()
        );

        List<LigneAchatDTO> lignesDTO = new ArrayList<>();
        for (LigneAchat ligne : achat.getLignesAchat()) {
            LigneAchatDTO ligneDTO = new LigneAchatDTO();
            ligneDTO.setId(ligne.getId());
            ligneDTO.setIdProduit(ligne.getProduit().getId());
            ligneDTO.setTitreProduit(ligne.getProduit().getTitre());
            ligneDTO.setCouleurProduit(ligne.getProduit().getCouleur().getNom());
            ligneDTO.setImageProduit(ligne.getProduit().getImagePrincipale());
            ligneDTO.setPrixProduitHT(ligne.getProduit().getPrixVenteHT());
            ligneDTO.setPrixProduitTTC(ligne.getProduit().getPrixVenteTTC());
            ligneDTO.setSaisonProduit(ligne.getProduit().getSaison());
            ligneDTO.setConseilEntretienProduit(ligne.getProduit().getConseilEntretien());
            ligneDTO.setPrixProduitReduit(ligne.getProduit().getPrixVenteTTCReduit());
            ligneDTO.setPourcentageReductionProduit(ligne.getProduit().getPourcentageReduction());
            ligneDTO.setDescriptionProduit(ligne.getProduit().getDescription());


            ligneDTO.setTaille(ligne.getTaille());
            ligneDTO.setQuantite(ligne.getQuantite());
            ligneDTO.setPrixHT(ligne.getPrixHT());
            ligneDTO.setPrixTTC(ligne.getPrixTTC());
            ligneDTO.setTva(ligne.getTva());
            lignesDTO.add(ligneDTO);
        }
        dto.setLignesAchat(lignesDTO);

        return dto;
    }

    private Utilisateur verifierAdmin(String jwt) throws AchatException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(email);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            throw new AchatException("Accès interdit : vous devez être un administrateur !");
        }
        return admin;
    }

    @Override
    public AchatDTO mettreAJourDateReception(Long achatId, LocalDateTime nouvelleDateReception, String jwt) throws AchatException {
        verifierAdmin(jwt);
        Achat achat = achatRepository.findById(achatId)
                .orElseThrow(() -> new AchatException("Achat non trouvé"));

        achat.setDateReception(nouvelleDateReception);
        achatRepository.save(achat);

        return convertToDTO(achat);
    }

}