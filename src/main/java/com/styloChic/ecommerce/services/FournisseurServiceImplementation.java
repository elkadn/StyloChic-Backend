package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.dtos.FournisseurDTO;
import com.styloChic.ecommerce.exceptions.FournisseurException;
import com.styloChic.ecommerce.models.Fournisseur;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.repositories.FournisseurRepository;
import com.styloChic.ecommerce.repositories.UtilisateurRepository;
import com.styloChic.ecommerce.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FournisseurServiceImplementation implements FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public List<Fournisseur> chercherTousFournisseurs(String jwt) throws FournisseurException {
        verifierAdmin(jwt);
        return fournisseurRepository.findAll();
    }

    @Override
    public Fournisseur ajouterFournisseur(FournisseurDTO fournisseurDTO, String jwt) throws FournisseurException {
        Utilisateur admin = verifierAdmin(jwt);

        boolean fournisseurExistant = fournisseurRepository.existsByEmail(fournisseurDTO.getEmail());
        if (fournisseurExistant) {
            throw new FournisseurException("Un fournisseur avec cet email existe déjà !");
        }

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setPrenom(fournisseurDTO.getPrenom());
        fournisseur.setNom(fournisseurDTO.getNom());
        fournisseur.setAdresse(fournisseurDTO.getAdresse());
        fournisseur.setEmail(fournisseurDTO.getEmail());
        fournisseur.setTelephone(fournisseurDTO.getTelephone());
        fournisseur.setAdmin(admin);
        fournisseur.setDateAjout(LocalDateTime.now());
        fournisseur.setDateModification(LocalDateTime.now());

        return fournisseurRepository.save(fournisseur);
    }

    @Override
    public Fournisseur chercherFournisseurParId(Long id, String jwt) throws FournisseurException {
        verifierAdmin(jwt);
        return fournisseurRepository.findById(id)
                .orElseThrow(() -> new FournisseurException("Fournisseur non trouvé avec ID: " + id));
    }

    @Override
    public Fournisseur mettreAJourFournisseur(Long id, FournisseurDTO fournisseurDTO, String jwt) throws FournisseurException {
        Utilisateur admin = verifierAdmin(jwt);
        Fournisseur fournisseurExistants = fournisseurRepository.findById(id)
                .orElseThrow(() -> new FournisseurException("Fournisseur non trouvé avec l'ID: " + id));

        fournisseurExistants.setPrenom(fournisseurDTO.getPrenom());
        fournisseurExistants.setNom(fournisseurDTO.getNom());
        fournisseurExistants.setAdresse(fournisseurDTO.getAdresse());
        fournisseurExistants.setEmail(fournisseurDTO.getEmail());
        fournisseurExistants.setTelephone(fournisseurDTO.getTelephone());
        fournisseurExistants.setAdmin(admin);
        fournisseurExistants.setDateModification(LocalDateTime.now());


        return fournisseurRepository.save(fournisseurExistants);
    }

    @Override
    public void supprimerFournisseur(Long id, String jwt) throws FournisseurException {
        verifierAdmin(jwt);
        Fournisseur fournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new FournisseurException("Fournisseur non trouvé avec l'ID: " + id));

        if (fournisseurRepository.countByProduitsFournisseur(fournisseur) > 0) {
            throw new FournisseurException("Impossible de supprimer ce fournisseur !");
        }

        fournisseurRepository.delete(fournisseur);
    }

    private Utilisateur verifierAdmin(String jwt) throws FournisseurException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(email);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            throw new FournisseurException("Accès interdit : vous devez être un administrateur !");
        }
        return admin;
    }
}
