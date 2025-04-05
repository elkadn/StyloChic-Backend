package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.models.Vote;
import com.styloChic.ecommerce.requests.VoteRequest;

import java.util.List;

public interface VoteService {

    public Vote creerVote(VoteRequest requete, Utilisateur utilisateur) throws ProduitException;

    Vote modifierVote(Long ratingId, VoteRequest requete,String jwt) throws ProduitException,UtilisateurException;

    public List<Vote> avoirVotesProduit(Long idProduit) throws ProduitException;

    public Vote avoirVoteUtilisateurSurProduit(Long idProduit, String jwt) throws ProduitException, UtilisateurException;

    public long totalVotesSurProduit(Long idProduit) throws ProduitException;

    Double calculerMoyenneVote(Long idProduit) throws ProduitException;


    public void supprimerVote(Long voteId) throws ProduitException;
}
