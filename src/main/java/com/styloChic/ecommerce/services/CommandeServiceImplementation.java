package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.config.JwtProvider;
import com.styloChic.ecommerce.dtos.CommandeDTO;
import com.styloChic.ecommerce.exceptions.CommandeException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.*;
import com.styloChic.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommandeServiceImplementation implements CommandeService{

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private PanierService panierService;

    @Autowired
    private AdresseCommandeRepository adresseCommandeRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private UtilisateurService utilisateurService;


    @Override
    public CommandeDTO creerCommande(Utilisateur utilisateur, AdresseCommande adresseCommande) {

        adresseCommande.setUtilisateur(utilisateur);
        adresseCommande.setDateAjout(LocalDateTime.now());
        AdresseCommande addresse = adresseCommandeRepository.save(adresseCommande);

        utilisateur.getAdresse().add(addresse);
        utilisateurRepository.save(utilisateur);

        Panier panier = panierService.chercherPanierUtilisateur(utilisateur.getId());

        List<LigneCommande> lignesCommande = new ArrayList<>();

        for (ElementPanier elementPanier : panier
                .getElementsPanier()) {
            LigneCommande ligneCommande = new LigneCommande();
            ligneCommande.setPrixHT(elementPanier.getTotalHt());
            ligneCommande.setPrixTTC(elementPanier.getTotalTTC());
            ligneCommande.setPrixTTCReduit(elementPanier.getTotalTTCReduit());
            ligneCommande.setProduit(elementPanier.getProduit());
            ligneCommande.setQuantite(elementPanier.getQuantite());
            ligneCommande.setTaille(elementPanier.getTaille());
            ligneCommande.setTva(elementPanier.getTva());
            ligneCommande.setUtilisateurId(elementPanier.getUtilisateurId());
            lignesCommande.add(ligneCommande);
        }


        Commande commandeCree = new Commande();

        commandeCree.setUtilisateur(utilisateur);
        commandeCree.setLigneCommande(lignesCommande);
        commandeCree.setTotalHT(panier.getPrixTotalHt());
        commandeCree.setTotalTTC(panier.getPrixTotalTTC());
        commandeCree.setTva(panier.getTva());
        commandeCree.setPrixTTCReduit(panier.getPrixTotalTTCReduit());
        commandeCree.setMontantBase(panier.getMontantBase());
        commandeCree.setMontantReduit(panier.getMontantReduit());
        commandeCree.setPourcentageReduction(panier.getPourcentageReduction());
        commandeCree.setTotalElements(panier.getTotalElement());
        commandeCree.setAdresseLivrasion(addresse);
        commandeCree.setDateCommande(LocalDateTime.now());
        commandeCree.setStatutCommande("EN ATTENTE");
        commandeCree.setDateCreation(LocalDateTime.now());
        String numCommande = generateUniqueCommandeNumber();
        commandeCree.setNumCommande(numCommande);


        Commande commandeEnregistre = commandeRepository.save(commandeCree);

        for (LigneCommande ligneCommande : lignesCommande) {
            ligneCommande.setCommande(commandeCree);
            ligneCommandeRepository.save(ligneCommande);
        }
        CommandeDTO commandeDTO = new CommandeDTO();
        commandeDTO.setId(commandeCree.getId());
        commandeDTO.setTotalHT(commandeCree.getTotalHT());
        commandeDTO.setTotalTTC(commandeCree.getTotalTTC());
        commandeDTO.setMontantReduit(commandeCree.getMontantReduit());
        commandeDTO.setMontantBase(commandeCree.getMontantBase());
        commandeDTO.setPrixTTCReduit(commandeCree.getPrixTTCReduit());
        commandeDTO.setTva(commandeCree.getTva());
        commandeDTO.setStatutCommande(commandeCree.getStatutCommande());
        commandeDTO.setDateCommande(commandeCree.getDateCommande());
        commandeDTO.setDateLivraison(commandeCree.getDateLivraison());
        commandeDTO.setDateCreation(commandeCree.getDateCreation());
        commandeDTO.setPourcentageReduction(commandeCree.getPourcentageReduction());
        commandeDTO.setNumCommande(commandeCree.getNumCommande());
        commandeDTO.setTotalElements(commandeCree.getTotalElements());
        commandeDTO.setAdresseLivrasion(commandeCree.getAdresseLivrasion());



        return commandeDTO;
    }

    private String generateUniqueCommandeNumber() {
        LocalDateTime now = LocalDateTime.now();
        String datePart = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String uniquePart = UUID.randomUUID().toString().substring(0, 8);

        return "CMD-" + datePart + "-" + uniquePart;
    }
    @Override
    public CommandeDTO chercherCommandeParId(Long commandeId, Long utilisateurId) throws CommandeException, UtilisateurException {
        Utilisateur utilisateur = utilisateurService.chercherUtilisateurParIdParticulier(utilisateurId);

        Optional<Commande> optionnel = commandeRepository.findById(commandeId);

        if (optionnel.isPresent()) {
            Commande commande = optionnel.get();

            if (utilisateurId.equals(commande.getUtilisateur().getId())) {
                CommandeDTO commandeDTO = new CommandeDTO();
                commandeDTO.setId(commande.getId());
                commandeDTO.setTotalHT(commande.getTotalHT());
                commandeDTO.setTotalTTC(commande.getTotalTTC());
                commandeDTO.setMontantReduit(commande.getMontantReduit());
                commandeDTO.setMontantBase(commande.getMontantBase());
                commandeDTO.setPrixTTCReduit(commande.getPrixTTCReduit());
                commandeDTO.setTva(commande.getTva());
                commandeDTO.setStatutCommande(commande.getStatutCommande());
                commandeDTO.setDateCommande(commande.getDateCommande());
                commandeDTO.setDateLivraison(commande.getDateLivraison());
                commandeDTO.setDateCreation(commande.getDateCreation());
                commandeDTO.setPourcentageReduction(commande.getPourcentageReduction());
                commandeDTO.setNumCommande(commande.getNumCommande());
                commandeDTO.setTotalElements(commande.getTotalElements());
                commandeDTO.setAdresseLivrasion(commande.getAdresseLivrasion());

                return commandeDTO;
            } else {
                throw new CommandeException("Ce n'est pas votre commande !");
            }
        }

        throw new CommandeException("Commande non trouvée avec id : " + commandeId);
    }


    @Override
    public Commande chercherCommandeParIdParticuliere(Long commandeId) throws CommandeException {

        Optional<Commande> optionnel = commandeRepository.findById(commandeId);
        if (optionnel.isPresent()) {
            return optionnel.get();
        }

        throw new CommandeException("Commande non trouvée avec id : " + commandeId);
    }


    @Override
    public List<CommandeDTO> historiqueCommandesUtilisateur(Long utilisateurId) throws CommandeException {
        List<Commande> commandes = commandeRepository.avoirCommandesUtilisateur(utilisateurId);
        if (commandes.isEmpty()) {
            throw new CommandeException("Aucune commande trouvée pour l'utilisateur avec id : " + utilisateurId);
        }
        return commandes.stream()
                .map(CommandeDTO::new)
                .collect(Collectors.toList());
    }


    @Override
    public CommandeDTO commandePlacee(Long commandeId,String jwt) throws CommandeException {
        Commande commande = chercherCommandeParIdParticuliere(commandeId);
        commande.setStatutCommande("PLACÉE");
        //order.getPaymentDetails().setStatus("COMPLETED");
        CommandeDTO commandeDTO = new CommandeDTO();
        commandeDTO.setId(commande.getId());
        commandeDTO.setTotalHT(commande.getTotalHT());
        commandeDTO.setTotalTTC(commande.getTotalTTC());
        commandeDTO.setMontantReduit(commande.getMontantReduit());
        commandeDTO.setMontantBase(commande.getMontantBase());
        commandeDTO.setPrixTTCReduit(commande.getPrixTTCReduit());
        commandeDTO.setTva(commande.getTva());
        commandeDTO.setStatutCommande(commande.getStatutCommande());
        commandeDTO.setDateCommande(commande.getDateCommande());
        commandeDTO.setDateLivraison(commande.getDateLivraison());
        commandeDTO.setDateCreation(commande.getDateCreation());
        commandeDTO.setPourcentageReduction(commande.getPourcentageReduction());
        commandeDTO.setNumCommande(commande.getNumCommande());
        commandeDTO.setTotalElements(commande.getTotalElements());
        commandeDTO.setAdresseLivrasion(commande.getAdresseLivrasion());

        return commandeDTO;
    }

    @Override
    public CommandeDTO commandeConfirmee(Long commandeId,String jwt) throws CommandeException {
        Commande commande = chercherCommandeParIdParticuliere(commandeId);
        commande.setStatutCommande("CONFIRMÉE");

        commandeRepository.save(commande);

        CommandeDTO commandeDTO = new CommandeDTO();
        commandeDTO.setId(commande.getId());
        commandeDTO.setTotalHT(commande.getTotalHT());
        commandeDTO.setTotalTTC(commande.getTotalTTC());
        commandeDTO.setMontantReduit(commande.getMontantReduit());
        commandeDTO.setMontantBase(commande.getMontantBase());
        commandeDTO.setPrixTTCReduit(commande.getPrixTTCReduit());
        commandeDTO.setTva(commande.getTva());
        commandeDTO.setStatutCommande(commande.getStatutCommande());
        commandeDTO.setDateCommande(commande.getDateCommande());
        commandeDTO.setDateLivraison(commande.getDateLivraison());
        commandeDTO.setDateCreation(commande.getDateCreation());
        commandeDTO.setPourcentageReduction(commande.getPourcentageReduction());
        commandeDTO.setNumCommande(commande.getNumCommande());
        commandeDTO.setTotalElements(commande.getTotalElements());
        commandeDTO.setAdresseLivrasion(commande.getAdresseLivrasion());

        return commandeDTO;
    }


    @Override
    public CommandeDTO commandeExpediee(Long commandeId,String jwt) throws CommandeException {
        Commande commande = chercherCommandeParIdParticuliere(commandeId);
        commande.setStatutCommande("EXPÉDIÉE");

        for (LigneCommande ligneCommande : commande.getLigneCommande()) {
            Produit produit = ligneCommande.getProduit();
            String taille = ligneCommande.getTaille();
            int quantiteCommandee = ligneCommande.getQuantite();

            modifierTailleQuantite(produit, taille, quantiteCommandee);
        }

        commandeRepository.save(commande);

        CommandeDTO commandeDTO = new CommandeDTO();
        commandeDTO.setId(commande.getId());
        commandeDTO.setTotalHT(commande.getTotalHT());
        commandeDTO.setTotalTTC(commande.getTotalTTC());
        commandeDTO.setMontantReduit(commande.getMontantReduit());
        commandeDTO.setMontantBase(commande.getMontantBase());
        commandeDTO.setPrixTTCReduit(commande.getPrixTTCReduit());
        commandeDTO.setTva(commande.getTva());
        commandeDTO.setStatutCommande(commande.getStatutCommande());
        commandeDTO.setDateCommande(commande.getDateCommande());
        commandeDTO.setDateLivraison(commande.getDateLivraison());
        commandeDTO.setDateCreation(commande.getDateCreation());
        commandeDTO.setPourcentageReduction(commande.getPourcentageReduction());
        commandeDTO.setNumCommande(commande.getNumCommande());
        commandeDTO.setTotalElements(commande.getTotalElements());
        commandeDTO.setAdresseLivrasion(commande.getAdresseLivrasion());

        return commandeDTO;
    }

    private void modifierProduitTotalQuantite(Produit produit) {
        int quantiteTotal = 0;
        for (Taille taille : produit.getTailles()) {
            quantiteTotal += taille.getQuantiteEnStock();
        }
        produit.setQuantiteEnStock(quantiteTotal);
    }

    private void modifierTailleQuantite(Produit produit, String nomTaille, int quantiteCommandee) {
        Set<Taille> tailles = produit.getTailles();
        for (Taille taille : tailles) {
            if (taille.getNom().equals(nomTaille)) {
                taille.setQuantiteEnStock(taille.getQuantiteEnStock() - quantiteCommandee);
                break;
            }
        }
        modifierProduitTotalQuantite(produit);
        produitRepository.save(produit);
    }

    @Override
    public CommandeDTO commandeLivree(Long commandeId,String jwt) throws CommandeException {
        verifierAdmin(jwt);
        Commande commande = chercherCommandeParIdParticuliere(commandeId);
        commande.setStatutCommande("LIVRÉE");
        commandeRepository.save(commande);

        CommandeDTO commandeDTO = new CommandeDTO();
        commandeDTO.setId(commande.getId());
        commandeDTO.setTotalHT(commande.getTotalHT());
        commandeDTO.setTotalTTC(commande.getTotalTTC());
        commandeDTO.setMontantReduit(commande.getMontantReduit());
        commandeDTO.setMontantBase(commande.getMontantBase());
        commandeDTO.setPrixTTCReduit(commande.getPrixTTCReduit());
        commandeDTO.setTva(commande.getTva());
        commandeDTO.setStatutCommande(commande.getStatutCommande());
        commandeDTO.setDateCommande(commande.getDateCommande());
        commandeDTO.setDateLivraison(commande.getDateLivraison());
        commandeDTO.setDateCreation(commande.getDateCreation());
        commandeDTO.setPourcentageReduction(commande.getPourcentageReduction());
        commandeDTO.setNumCommande(commande.getNumCommande());
        commandeDTO.setTotalElements(commande.getTotalElements());
        commandeDTO.setAdresseLivrasion(commande.getAdresseLivrasion());

        return commandeDTO;
    }

    @Override
    public CommandeDTO commandeAnnulee(Long commandeId,String jwt) throws CommandeException {
        verifierAdmin(jwt);
        Commande commande = chercherCommandeParIdParticuliere(commandeId);
        commande.setStatutCommande("ANNULÉE");
        commandeRepository.save(commande);

        CommandeDTO commandeDTO = new CommandeDTO();
        commandeDTO.setId(commande.getId());
        commandeDTO.setTotalHT(commande.getTotalHT());
        commandeDTO.setTotalTTC(commande.getTotalTTC());
        commandeDTO.setMontantReduit(commande.getMontantReduit());
        commandeDTO.setMontantBase(commande.getMontantBase());
        commandeDTO.setPrixTTCReduit(commande.getPrixTTCReduit());
        commandeDTO.setTva(commande.getTva());
        commandeDTO.setStatutCommande(commande.getStatutCommande());
        commandeDTO.setDateCommande(commande.getDateCommande());
        commandeDTO.setDateLivraison(commande.getDateLivraison());
        commandeDTO.setDateCreation(commande.getDateCreation());
        commandeDTO.setPourcentageReduction(commande.getPourcentageReduction());
        commandeDTO.setNumCommande(commande.getNumCommande());
        commandeDTO.setTotalElements(commande.getTotalElements());
        commandeDTO.setAdresseLivrasion(commande.getAdresseLivrasion());

        return commandeDTO;
    }

    @Override
    public List<CommandeDTO> avoirToutesCommandes(String jwt) throws CommandeException {
        verifierAdmin(jwt);
        List<Commande> commandes = commandeRepository.findAll();
        return commandes.stream()
                .map(commande -> new CommandeDTO(commande))
                .collect(Collectors.toList());
    }


    @Override
    public void supprimerCommande(Long commandeId,String jwt) throws CommandeException {
        verifierAdmin(jwt);
        Commande commande = chercherCommandeParIdParticuliere(commandeId);
        commandeRepository.deleteById(commandeId);
    }

    private Utilisateur verifierAdmin(String jwt) throws CommandeException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(email);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            throw new CommandeException("Accès interdit : vous devez être un administrateur !");
        }
        return admin;
    }
}
