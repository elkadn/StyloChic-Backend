package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.config.JwtProvider;
import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.models.Avis;
import com.styloChic.ecommerce.models.Produit;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.repositories.AvisRepository;
import com.styloChic.ecommerce.repositories.ProduitRepository;
import com.styloChic.ecommerce.repositories.UtilisateurRepository;
import com.styloChic.ecommerce.requests.AvisRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AvisServiceImplementation implements AvisService {

    @Autowired
    private AvisRepository avisRepository;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private JwtProvider jwtProvider;

    private Utilisateur verifierAdmin(String jwt) throws ProduitException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(email);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            throw new ProduitException("Accès interdit : vous devez être un administrateur !");
        }
        return admin;
    }

    @Override
    public Avis creerAvis(AvisRequest requete, Utilisateur utilisateur) throws ProduitException {
        Produit produit = produitService.chercherProduit(requete.getIdProduit());

        Avis avis = new Avis();
        avis.setUtilisateur(utilisateur);
        avis.setProduit(produit);
        avis.setAvis(requete.getAvis());
        avis.setDateCreation(LocalDateTime.now());
        avis.setVisibilite(false);

        return avisRepository.save(avis);
    }

    @Override
    public List<Avis> avoirTousAvis(Long idProduit) throws ProduitException{
        Produit produit = produitService.chercherProduit(idProduit);
        return avisRepository.avoirTousProduitsAvis(idProduit);
    }

    @Override
    public Long compterAvis(Long idProduit) throws ProduitException {
        Produit produit = produitService.chercherProduit(idProduit);
        return avisRepository.compterAvisAvecIdProduit(idProduit);
    }

    @Override
    public List<Avis> avoirAvisAleatoire(Long idProduit) throws ProduitException {
        Produit produit = produitService.chercherProduit(idProduit);
        List<Avis> avis = avisRepository.trouverAvisAloitoireAvecPrduitId(idProduit);
        return avis.size() > 4 ? avis.subList(0, 4) : avis;
    }

    @Override
    public void supprimerAvis(Long avisId) throws ProduitException{
        Avis avis = avisRepository.findById(avisId)
                .orElseThrow(() -> new ProduitException("Avis non trouvée avec l'id : " + avisId));
        avisRepository.deleteById(avisId);
    }

    public void changerVisibiliteAvis(Long avisId, String jwt, boolean visibilite) throws ProduitException {
        verifierAdmin(jwt);
        Avis avis = avisRepository.findById(avisId)
                .orElseThrow(() -> new ProduitException("Avis non trouvé avec l'id : " + avisId));

        avis.setVisibilite(visibilite);
        avisRepository.save(avis);
    }

}
