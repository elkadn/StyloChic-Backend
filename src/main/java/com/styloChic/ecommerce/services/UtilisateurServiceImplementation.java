package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.config.JwtProvider;
import com.styloChic.ecommerce.dtos.UtilisateurDTO;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.repositories.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UtilisateurServiceImplementation implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;


    public UtilisateurServiceImplementation(UtilisateurRepository userRepository, JwtProvider jwtProvider,PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public Utilisateur chercherProfileUtilisateurParJwt(String jwt) throws UtilisateurException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Utilisateur utilisateur = utilisateurRepository.chercherParEmail(email);

        if(utilisateur == null){
            throw new UtilisateurException("Utilisateur non existant avec email "+email+" !");
        }
        return utilisateur;
    }

    @Override
    public Utilisateur chercherUtilisateurParId(Long utilisateurId, String jwt) throws UtilisateurException {
        String adminEmail = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(adminEmail);

        if (admin == null || !admin.getRole().equals("ADMIN")) {
            throw new UtilisateurException("Accès interdit : vous devez être un administrateur pour effectuer cette action !");
        }

        Optional<Utilisateur> utilisateurOptionel = utilisateurRepository.findById(utilisateurId);

        if (utilisateurOptionel.isEmpty()) {
            throw new UtilisateurException("Utilisateur non existant avec id "+utilisateurId+" !");
        }

        return utilisateurOptionel.get();
    }

    @Override
    public Utilisateur chercherUtilisateurParIdParticulier(Long utilisateurId) throws UtilisateurException {

        Optional<Utilisateur> utilisateurOptionel = utilisateurRepository.findById(utilisateurId);

        if (utilisateurOptionel.isEmpty()) {
            throw new UtilisateurException("Utilisateur non existant avec id "+utilisateurId+" !");
        }

        return utilisateurOptionel.get();
    }

    @Override
    public List<Utilisateur> chercherTousUtilisateurs(String jwt) throws UtilisateurException {

        String adminEmail = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(adminEmail);

        if (admin == null || !admin.getRole().equals("ADMIN")) {
            throw new UtilisateurException("Accès interdit : vous devez être un administrateur pour effectuer cette action !");
        }

        return utilisateurRepository.findAll();
    }


    @Override
    public UtilisateurDTO modifierRoleUtilisateur(Long id, String role, String jwt) throws UtilisateurException {
        Optional<Utilisateur> utilisateurOptionel = utilisateurRepository.findById(id);

        if (utilisateurOptionel.isEmpty()) {
            throw new UtilisateurException("Utilisateur non existant pour modifier son rôle !");
        }

        Utilisateur utilisateur = utilisateurOptionel.get();

        String adminEmail = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(adminEmail);

        if (admin == null || !admin.getRole().equals("ADMIN")) {
            throw new UtilisateurException("Accès interdit : vous devez être un administrateur pour effectuer cette action !");
        }

        utilisateur.setAdmin(admin);
        utilisateur.setDateModification(LocalDateTime.now());
        utilisateur.setRole(role);
        utilisateurRepository.save(utilisateur);
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO(
                utilisateur.getId(),
                utilisateur.getPrenom(),
                utilisateur.getNom(),
                utilisateur.getEmail(),
                utilisateur.getTelephone(),
                utilisateur.getRole()
        );

        return utilisateurDTO;
    }

    @Override
    public Utilisateur creerUtilisateur(Utilisateur utilisateur, String jwt) throws UtilisateurException {

        String adminEmail = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(adminEmail);

        if (admin == null || !admin.getRole().equals("ADMIN")) {
            throw new UtilisateurException("Accès interdit : vous devez être un administrateur pour effectuer cette action !");
        }

        Utilisateur emailExisteDeja = utilisateurRepository.chercherParEmail(utilisateur.getEmail());
        if (emailExisteDeja != null) {
            throw new UtilisateurException("L'email est déjà utilisé par un autre compte !");
        }

        utilisateur.setRole("CLIENT");
        utilisateur.setAdmin(admin);
        utilisateur.setDateCreation(LocalDateTime.now());
        utilisateur.setDateModification(LocalDateTime.now());
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public void supprimerUtilisateur(Long id, String jwt) throws UtilisateurException {
        Optional<Utilisateur> utilisateurOptionel = utilisateurRepository.findById(id);

        if (utilisateurOptionel.isEmpty()) {
            throw new UtilisateurException("Utilisateur non existant !");
        }

        String adminEmail = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(adminEmail);

        if (admin == null || !admin.getRole().equals("ADMIN")) {
            throw new UtilisateurException("Accès interdit : vous devez être un administrateur pour effectuer cette action !");
        }

        utilisateurRepository.delete(utilisateurOptionel.get());
    }

    @Override
    public UtilisateurDTO mettreAJourUtilisateur(Long id, Utilisateur utilisateur, String jwt) throws UtilisateurException {
        Optional<Utilisateur> utilisateurOptionel = utilisateurRepository.findById(id);

        if (utilisateurOptionel.isEmpty()) {
            throw new UtilisateurException("Utilisateur non existant !");
        }

        Utilisateur utilisateurExistants = utilisateurOptionel.get();

        String adminEmail = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(adminEmail);

        if (admin == null || !admin.getRole().equals("ADMIN")) {
            throw new UtilisateurException("Accès interdit : vous devez être un administrateur pour effectuer cette action !");
        }

        utilisateurExistants.setPrenom(utilisateur.getPrenom());
        utilisateurExistants.setNom(utilisateur.getNom());
        utilisateurExistants.setEmail(utilisateur.getEmail());
        if (utilisateur.getMotDePasse() != null && !utilisateur.getMotDePasse().trim().isEmpty()) {
            utilisateurExistants.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        }
        utilisateurExistants.setTelephone(utilisateur.getTelephone());
        utilisateurExistants.setDateModification(LocalDateTime.now());
        utilisateurExistants.setAdmin(admin);

        utilisateurExistants.setRole(utilisateurExistants.getRole());
        utilisateurRepository.save(utilisateurExistants);
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO(
                utilisateurExistants.getId(),
                utilisateurExistants.getPrenom(),
                utilisateurExistants.getNom(),
                utilisateurExistants.getEmail(),
                utilisateurExistants.getTelephone(),
                utilisateurExistants.getRole()
        );

        return utilisateurDTO;
    }

    @Override
    public void changerMotDePasse(Long utilisateurId, String nouveauMotDePasse, String jwt) throws UtilisateurException {
        if (nouveauMotDePasse == null || nouveauMotDePasse.trim().isEmpty()) {
            throw new UtilisateurException("Le nouveau mot de passe ne peut pas être vide !");
        }

        String emailDemandeur = jwtProvider.getEmailFromToken(jwt);
        Utilisateur demandeur = utilisateurRepository.chercherParEmail(emailDemandeur);

        if (demandeur == null) {
            throw new UtilisateurException("Utilisateur connecté introuvable !");
        }

        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(utilisateurId);
        if (utilisateurOpt.isEmpty()) {
            throw new UtilisateurException("Utilisateur avec ID " + utilisateurId + " non trouvé !");
        }

        Utilisateur cible = utilisateurOpt.get();

        if(!cible.getId().equals(demandeur.getId())) {
            throw new UtilisateurException("Vous n'êtes pas autorisé à modifier ce mot de passe !");
        }

        if (nouveauMotDePasse.length() < 8) {
            throw new UtilisateurException("Le mot de passe doit contenir au moins 8 caractères !");
        }

        if (passwordEncoder.matches(nouveauMotDePasse, cible.getMotDePasse())) {
            throw new UtilisateurException("Le nouveau mot de passe doit être différent de l'ancien !");
        }


        String motDePasseEncode = passwordEncoder.encode(nouveauMotDePasse);
        cible.setMotDePasse(motDePasseEncode);
        cible.setDateModification(LocalDateTime.now());

        utilisateurRepository.save(cible);
    }

    @Override
    public long compterUtilisateurs(String jwt) throws UtilisateurException {
        String adminEmail = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(adminEmail);

        if (admin == null || !admin.getRole().equals("ADMIN")) {
            throw new UtilisateurException("Accès interdit : vous devez être un administrateur pour effectuer cette action !");
        }

        return utilisateurRepository.count();
    }


}

