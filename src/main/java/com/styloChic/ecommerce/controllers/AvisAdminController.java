package com.styloChic.ecommerce.controllers;


import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Avis;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.requests.AvisRequest;
import com.styloChic.ecommerce.services.AvisService;
import com.styloChic.ecommerce.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/styloChic/avis")
public class AvisAdminController {

    @Autowired
    private AvisService avisService;

    @Autowired
    private UtilisateurService utilisateurService;
    @PostMapping("/ajouter")
    public ResponseEntity<Avis> ajouterAvis(@RequestBody AvisRequest requete,
                                            @RequestHeader("Authorization") String jwt) throws UtilisateurException, ProduitException {
        Utilisateur utilisateur = utilisateurService.chercherProfileUtilisateurParJwt(jwt);
        Avis avis = avisService.creerAvis(requete,utilisateur);
        return new ResponseEntity<>(avis, HttpStatus.CREATED);
    }

    @DeleteMapping("/{avisId}/supprimer")
    public ResponseEntity<String> supprimerAvis(@PathVariable Long avisId) throws ProduitException{
        avisService.supprimerAvis(avisId);
        return new ResponseEntity<>("Avis suprimé avec succès",HttpStatus.OK);

    }

    @PutMapping("/admin/visibilite/{avisId}")
    public ResponseEntity<String> changerVisibiliteAvis(@PathVariable Long avisId,
                                                      @RequestHeader("Authorization") String jwt,
                                                      @RequestParam boolean visibilite) throws ProduitException {
        avisService.changerVisibiliteAvis(avisId, jwt, visibilite);
        return new ResponseEntity<>("Visibilté changé avec succès",HttpStatus.OK);

    }
}
