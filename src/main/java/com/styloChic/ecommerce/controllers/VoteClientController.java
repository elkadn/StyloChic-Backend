package com.styloChic.ecommerce.controllers;


import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.models.Vote;
import com.styloChic.ecommerce.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteClientController {


    @Autowired
    private VoteService voteService;

    @GetMapping("produit/{idProduit}")
    public ResponseEntity<List<Vote>> avoirVotesProduit(@PathVariable Long idProduit) throws ProduitException{
        List<Vote> votes = voteService.avoirVotesProduit(idProduit);

        return new ResponseEntity<>(votes, HttpStatus.CREATED);
    }

    @GetMapping("/produit/totalVotes/{idProduit}")
    public ResponseEntity<Long> avoirTotalVotesProduit(@PathVariable Long idProduit)
            throws ProduitException {

        long total = voteService.totalVotesSurProduit(idProduit);

        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    @GetMapping("/produit/moyenneVotes/{idProduit}")
    public ResponseEntity<Double> avoirMoyenneDesVotesSurProduit(@PathVariable Long idProduit)
            throws ProduitException {
        Double moyenneVotes = voteService.calculerMoyenneVote(idProduit);
        return new ResponseEntity<>(Math.round(moyenneVotes * 10.0) / 10.0, HttpStatus.OK);
    }
}
