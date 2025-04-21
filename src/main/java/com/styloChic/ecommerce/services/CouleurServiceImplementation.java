package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.config.JwtProvider;
import com.styloChic.ecommerce.dtos.CouleurDTO;
import com.styloChic.ecommerce.exceptions.CouleurException;
import com.styloChic.ecommerce.models.Couleur;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.repositories.CouleurRepository;
import com.styloChic.ecommerce.repositories.ProduitRepository;
import com.styloChic.ecommerce.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CouleurServiceImplementation implements CouleurService {

    @Autowired
    private CouleurRepository couleurRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private ProduitRepository produitRepository;

    private Utilisateur verifierAdmin(String jwt) throws CouleurException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(email);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            throw new CouleurException("Accès interdit : vous devez être un administrateur !");
        }
        return admin;
    }


    @Override
    public List<Couleur> chercherToutesCouleurs(String jwt) throws CouleurException {
        verifierAdmin(jwt);
        return couleurRepository.findAll();
    }

    @Override
    public Couleur ajouterCouleur(CouleurDTO couleurDTO, String jwt) throws CouleurException {
        Utilisateur admin = verifierAdmin(jwt);



        if (couleurRepository.findByNom(couleurDTO.getNom()).isPresent()) {
            throw new CouleurException("La couleur '" + couleurDTO.getNom() + "' existe déjà !");
        }

        Couleur couleur = new Couleur();
        couleur.setNom(couleurDTO.getNom());
        couleur.setDateCreation(LocalDateTime.now());
        couleur.setDateModification(LocalDateTime.now());
        couleur.setHexaCode(couleurDTO.getHexaCode());
        couleur.setAdmin(admin);

        return couleurRepository.save(couleur);
    }

    @Override
    public Couleur chercherCouleurParId(Long id, String jwt) throws CouleurException {
        verifierAdmin(jwt);
        return couleurRepository.findById(id)
                .orElseThrow(() -> new CouleurException("Couleur introuvable avec l'ID : " + id));
    }

    @Override
    public Couleur mettreAJourCouleur(Long id, CouleurDTO couleurDTO, String jwt) throws CouleurException {
        Utilisateur admin = verifierAdmin(jwt);

        Couleur couleurExistante = couleurRepository.findById(id)
                .orElseThrow(() -> new CouleurException("Couleur non trouvée avec l'ID : " + id));

        Optional<Couleur> couleurAvecMemeNom = couleurRepository.findByNomIgnoreCase(couleurDTO.getNom());
        if (couleurAvecMemeNom.isPresent() && !couleurAvecMemeNom.get().getId().equals(id)) {
            throw new CouleurException("Une autre couleur avec le nom '" + couleurDTO.getNom() + "' existe déjà !");
        }

        couleurExistante.setNom(couleurDTO.getNom());
        couleurExistante.setHexaCode(couleurDTO.getHexaCode());
        couleurExistante.setDateModification(LocalDateTime.now());
        couleurExistante.setAdmin(admin);

        return couleurRepository.save(couleurExistante);
    }

    @Override
    public void supprimerCouleur(Long id, String jwt) throws CouleurException {
        verifierAdmin(jwt);

        Couleur couleur = couleurRepository.findById(id)
                .orElseThrow(() -> new CouleurException("Couleur introuvable avec l'ID : " + id));

        if (produitRepository.existsByCouleur(couleur)) {
            throw new CouleurException("Impossible de supprimer cette couleur car elle est liée à des produits !");
        }

        couleurRepository.delete(couleur);
    }


    @Override
    public long compterCouleurs(String jwt) throws CouleurException {
        String adminEmail = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(adminEmail);

        if (admin == null || !admin.getRole().equals("ADMIN")) {
            throw new CouleurException("Accès interdit : vous devez être un administrateur pour effectuer cette action !");
        }

        return couleurRepository.count();
    }
}
