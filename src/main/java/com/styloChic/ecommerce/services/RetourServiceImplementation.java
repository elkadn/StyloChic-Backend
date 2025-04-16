package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.config.JwtProvider;
import com.styloChic.ecommerce.dtos.LigneRetourDTO;
import com.styloChic.ecommerce.dtos.RetourClientDTO;
import com.styloChic.ecommerce.exceptions.CommandeException;
import com.styloChic.ecommerce.exceptions.RetourException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.*;
import com.styloChic.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetourServiceImplementation implements RetourClientService{

    @Autowired
    private RetourClientRepository retourClientRepository;

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private LigneRetourRepository ligneRetourRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public RetourClientDTO demanderRetour(Long commandeId, Utilisateur utilisateur, List<LigneRetourDTO> lignesRetourDTO, String motif, String commentaire) throws CommandeException{
        Commande commande = commandeService.chercherCommandeParIdParticuliere(commandeId);

        if (!"Livrée".equals(commande.getStatutCommande())) {
            throw new IllegalStateException("Les retours ne sont possibles que pour les commandes livrées");
        }

        RetourClient retour = new RetourClient();
        retour.setCommande(commande);
        retour.setMotifRetour(motif);
        retour.setCommentaireClient(commentaire);
        retour.setDateDemande(LocalDateTime.now());
        retour.setRaison(motif);
        retour.setStatut("En attente");
        retour.setTypeRetour("Client");
        retour.setUtilisateur(utilisateur);
        retour.setDateCreation(LocalDateTime.now());

        List<LigneRetour> lignes = new ArrayList<>();
        for (LigneRetourDTO ligneDTO : lignesRetourDTO) {
            boolean produitDansCommande = commande.getLigneCommande().stream()
                    .anyMatch(lc -> lc.getProduit().getId().equals(ligneDTO.getProduitId())
                            && lc.getTaille().equals(ligneDTO.getTaille()));

            if (!produitDansCommande) {
                throw new IllegalArgumentException("Le produit avec id " + ligneDTO.getProduitId() + " et taille " + ligneDTO.getTaille() + " ne fait pas partie de cette commande");
            }

            LigneRetour ligne = new LigneRetour();
            ligne.setProduit(produitRepository.findById(ligneDTO.getProduitId()).orElseThrow());
            ligne.setTaille(ligneDTO.getTaille());
            ligne.setQuantite(ligneDTO.getQuantite());
            ligne.setRaisonRetour(ligneDTO.getRaisonRetour());
            ligne.setEtatProduit(ligneDTO.getEtatProduit());
            ligne.setRetour(retour);
            ligne.setUtilisateurId(utilisateur.getId());
            lignes.add(ligne);
        }

        retour.setLignesRetour(lignes);
        RetourClient savedRetour = retourClientRepository.save(retour);

        return convertToDTO(savedRetour);
    }

    @Override
    public RetourClientDTO traiterRetour(Long retourId, String statut, String jwt, String modeRetour,String commentaireAdmin) throws RetourException {
        verifierAdmin(jwt);


        RetourClient retour = retourClientRepository.findById(retourId)
                .orElseThrow(() -> new RetourException("Retour non trouvé"));

        retour.setStatut(statut);
        retour.setDateTraitement(LocalDateTime.now());
        retour.setModeRetour(modeRetour);
        retour.setDateApplication(LocalDate.now());
        retour.setReponse(commentaireAdmin);

        if ("Approuvé".equals(statut)) {
            restockerProduits(retour);
        }

        RetourClient updatedRetour = retourClientRepository.save(retour);
        return convertToDTO(updatedRetour);
    }

    private void restockerProduits(RetourClient retour) {
        for (LigneRetour ligne : retour.getLignesRetour()) {
            Produit produit = ligne.getProduit();
            String taille = ligne.getTaille();
            int quantite = ligne.getQuantite();

            produit.getTailles().stream()
                    .filter(t -> t.getNom().equals(taille))
                    .findFirst()
                    .ifPresent(t -> {
                        t.setQuantiteEnStock(t.getQuantiteEnStock() + quantite);
                        produitRepository.save(produit);
                    });
        }
    }

    @Override
    public List<RetourClientDTO> getRetoursParUtilisateur(Long utilisateurId) {
        List<RetourClient> retours = retourClientRepository.findByCommandeUtilisateurId(utilisateurId);
        return retours.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RetourClientDTO> getTousRetours(String jwt) throws RetourException {
        verifierAdmin(jwt);
        return retourClientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private RetourClientDTO convertToDTO(RetourClient retour) {
        RetourClientDTO dto = new RetourClientDTO();
        dto.setId(retour.getId());
        dto.setCommandeId(retour.getCommande().getId());
        dto.setNumeroCommande(retour.getCommande().getNumCommande());
        dto.setTypeRetour(retour.getTypeRetour());
        dto.setStatut(retour.getStatut());
        dto.setMotifRetour(retour.getMotifRetour());
        dto.setCommentaireClient(retour.getCommentaireClient());
        dto.setDateDemande(retour.getDateDemande());
        dto.setDateTraitement(retour.getDateTraitement());
        dto.setReponseAdmin(retour.getReponse());

        List<LigneRetourDTO> lignesDTO = retour.getLignesRetour().stream()
                .map(this::convertLigneToDTO)
                .collect(Collectors.toList());
        dto.setLignesRetour(lignesDTO);

        return dto;
    }

    private LigneRetourDTO convertLigneToDTO(LigneRetour ligne) {
        LigneRetourDTO dto = new LigneRetourDTO();
        dto.setId(ligne.getId());
        dto.setProduitId(ligne.getProduit().getId());
        dto.setTaille(ligne.getTaille());
        dto.setQuantite(ligne.getQuantite());
        dto.setRaisonRetour(ligne.getRaisonRetour());
        dto.setEtatProduit(ligne.getEtatProduit());
        dto.setImageProduit(ligne.getProduit().getImagePrincipale());
        dto.setTitreProduit(ligne.getProduit().getTitre());

        return dto;
    }

    private Utilisateur verifierAdmin(String jwt) throws RetourException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(email);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            throw new RetourException("Accès interdit : vous devez être un administrateur !");
        }
        return admin;
    }

    @Override
    public List<RetourClientDTO> getRetoursParCommande(Long commandeId, Long utilisateurId) throws CommandeException {
        Commande commande = commandeService.chercherCommandeParIdParticuliere(commandeId);

        if (!commande.getUtilisateur().getId().equals(utilisateurId)) {
            throw new CommandeException("Cette commande ne vous appartient pas !");
        }

        List<RetourClient> retours = retourClientRepository.findByCommandeId(commandeId);

        return retours.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}
