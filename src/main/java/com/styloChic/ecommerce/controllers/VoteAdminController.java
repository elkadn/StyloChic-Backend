package com.styloChic.ecommerce.controllers;


import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.models.Vote;
import com.styloChic.ecommerce.requests.VoteRequest;
import com.styloChic.ecommerce.services.UtilisateurService;
import com.styloChic.ecommerce.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/styloChic/votes")
public class VoteAdminController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private VoteService voteService;

    @PostMapping("/ajouter")
    public ResponseEntity<Vote> ajouterVote(@RequestBody VoteRequest requete, @RequestHeader("Authorization") String jwt)
            throws UtilisateurException, ProduitException {
        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);
        Vote vote = voteService.creerVote(requete,utilisateur);

        return new ResponseEntity<>(vote, HttpStatus.CREATED);
    }



    @GetMapping("/produit/{idProduit}/utilisateur")
    public ResponseEntity<Vote> getUserRatingForProduct(@PathVariable Long idProduit,
                                                          @RequestHeader("Authorization") String jwt)
            throws UtilisateurException, ProduitException {

        Vote vote = voteService.avoirVoteUtilisateurSurProduit(idProduit, jwt);

        return new ResponseEntity<>(vote, HttpStatus.OK);
    }


    @PutMapping("/modifier/{idVote}")
    public ResponseEntity<Vote> modifierVote(@PathVariable Long idVote,
                                               @RequestBody VoteRequest requete,
                                               @RequestHeader("Authorization") String jwt)
            throws UtilisateurException, ProduitException {
        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);
        Vote vote = voteService.modifierVote(idVote, requete,jwt);

        return new ResponseEntity<>(vote, HttpStatus.OK);
    }


    @DeleteMapping("/{idVote}/supprimer")
    public ResponseEntity<String> supprimerVote(@PathVariable Long idVote) throws ProduitException {
        voteService.supprimerVote(idVote);
        return new ResponseEntity<>("Vote supprimé avec succès",HttpStatus.OK);
    }

}
