package com.styloChic.ecommerce.services;


import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Produit;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.models.Vote;
import com.styloChic.ecommerce.repositories.VoteRepository;
import com.styloChic.ecommerce.requests.VoteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteServiceImplementation implements VoteService{

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private UtilisateurService utilisateurService;


    @Override
    public Vote creerVote(VoteRequest requete, Utilisateur utilisateur) throws ProduitException {
        Produit produit = produitService.chercherProduit(requete.getIdProduit());
        Vote vote = new Vote();
        vote.setProduit(produit);
        vote.setUtilisateur(utilisateur);
        vote.setVote(requete.getVote());
        vote.setDateCreation(LocalDateTime.now());
        vote.setDateModification(LocalDateTime.now());

        return voteRepository.save(vote);
    }



    @Override
    public List<Vote> avoirVotesProduit(Long idProduit) throws ProduitException{
        Produit produit = produitService.chercherProduit(idProduit);
        return voteRepository.findAllVotesByProduct(idProduit);
    }

    @Override
    public Vote avoirVoteUtilisateurSurProduit(Long idProduit, String jwt) throws ProduitException, UtilisateurException {
        Produit produit = produitService.chercherProduit(idProduit);
        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);
        return voteRepository.findByProduitAndUtilisateur(produit, utilisateur)
                .orElseThrow(() -> new ProduitException("Aucun vote pour cet utilisateur sur ce prouduit"));
    }

    @Override
    public long totalVotesSurProduit(Long idProduit) throws ProduitException{
        Produit produit = produitService.chercherProduit(idProduit);
        return voteRepository.countVotesForProduct(idProduit);
    }

    @Override
    public Vote modifierVote(Long idVote, VoteRequest requete, String jwt) throws ProduitException, UtilisateurException {
        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);

        Vote vote = voteRepository.findById(idVote)
                .orElseThrow(() -> new ProduitException("Vote introuvable avec l'id : " + idVote));

        if (!vote.getUtilisateur().getId().equals(utilisateur.getId())) {
            throw new UtilisateurException("Vous n'êtes pas autorisé à modifier ce vote.");
        }

        vote.setVote(requete.getVote());
        vote.setDateModification(LocalDateTime.now());

        return voteRepository.save(vote);
    }


    @Override
    public Double calculerMoyenneVote(Long idProduit) throws ProduitException {
        Double somme = voteRepository.sumVotesForProduct(idProduit);
        Long total = voteRepository.countVotesForProduct(idProduit);

        if (somme == null) {
            somme = 0.0;
        }

        if (total == 0) {
            return 0.0;
        }

        return somme / total;
    }


    @Override
    public void supprimerVote(Long idVote) throws ProduitException {
        Vote vote = voteRepository.findById(idVote)
                .orElseThrow(() -> new ProduitException("Vote non trouvée avec l'id : " + idVote));
        voteRepository.deleteById(idVote);
    }

}
