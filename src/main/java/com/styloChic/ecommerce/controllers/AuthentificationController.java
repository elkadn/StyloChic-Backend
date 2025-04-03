package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.config.JwtProvider;
import com.styloChic.ecommerce.exceptions.UtilisateurException;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.repositories.UtilisateurRepository;
import com.styloChic.ecommerce.requests.LoginRequest;
import com.styloChic.ecommerce.responses.AuthentificationResponse;
import com.styloChic.ecommerce.services.UtilisateurServiceParticulierImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/authentification")
public class AuthentificationController {

    private UtilisateurRepository userRepository;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;
    private UtilisateurServiceParticulierImplementation utilisateurServiceParticulier;
    //private CartService cartService;

    public AuthentificationController(UtilisateurRepository userRepository, UtilisateurServiceParticulierImplementation utilisateurServiceParticulier, PasswordEncoder passwordEncoder,JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.utilisateurServiceParticulier = utilisateurServiceParticulier;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        //this.cartService = cartService;
    }

    @PostMapping("/inscription")
    public ResponseEntity<AuthentificationResponse> gestionnaireCreationUtilisateur(@RequestBody Utilisateur utilisateur) throws UtilisateurException {
        String email = utilisateur.getEmail();
        String motDePasse = utilisateur.getMotDePasse();
        String prenom = utilisateur.getPrenom();
        String nom = utilisateur.getNom();
        String telephone = utilisateur.getTelephone();


        Utilisateur emailExisteDeja = userRepository.chercherParEmail(email);
        if (emailExisteDeja != null) {
            AuthentificationResponse response = new AuthentificationResponse();
            response.setMessage("L'email est déjà utilisé par un autre compte !");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }



        Utilisateur utilisateurCree = new Utilisateur();
        utilisateurCree.setEmail(email);
        utilisateurCree.setMotDePasse(passwordEncoder.encode(motDePasse));
        utilisateurCree.setPrenom(prenom);
        utilisateurCree.setNom(nom);
        utilisateurCree.setDateCreation(LocalDateTime.now());
        utilisateurCree.setDateModification(LocalDateTime.now());
        utilisateurCree.setTelephone(telephone);
        utilisateurCree.setRole("CLIENT");
        utilisateurCree.setAdmin(null);

        Utilisateur savedUser = userRepository.save(utilisateurCree);

        //Cart cart = cartService.createCart(savedUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getMotDePasse());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        AuthentificationResponse authentificationResponse = new AuthentificationResponse();
        authentificationResponse.setNom(utilisateurCree.getNom());
        authentificationResponse.setPrenom(utilisateurCree.getPrenom());
        authentificationResponse.setEmail(utilisateurCree.getEmail());
        authentificationResponse.setRole(utilisateurCree.getRole());
        authentificationResponse.setJwt(token);
        authentificationResponse.setMessage("Inscription réussie ! Bienvenue sur StyloChic");

        return new ResponseEntity<AuthentificationResponse>(authentificationResponse, HttpStatus.CREATED);
    }


    @PostMapping("/connexion")
    public ResponseEntity<AuthentificationResponse> gestionnaireConnexionUtilisateur(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String motDePasse = loginRequest.getMotDePasse();
        Authentication authentification = authentification(email, motDePasse);
        SecurityContextHolder.getContext().setAuthentication(authentification);

        String jeton = jwtProvider.generateToken(authentification);
        Utilisateur utilisateur = userRepository.chercherParEmail(email);


        AuthentificationResponse authentificationResponse = new AuthentificationResponse();
        authentificationResponse.setJwt(jeton);
        authentificationResponse.setNom(utilisateur.getNom());
        authentificationResponse.setPrenom(utilisateur.getPrenom());
        authentificationResponse.setEmail(utilisateur.getEmail());
        authentificationResponse.setRole(utilisateur.getRole());

        authentificationResponse.setMessage("Connexion réussie. Bienvenue à nouveau !");
        return new ResponseEntity<AuthentificationResponse>(authentificationResponse, HttpStatus.OK);
    }

    private Authentication authentification(String email, String motDePasse) {
        UserDetails userDetails = utilisateurServiceParticulier.loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException("Identifiants incorrects ! Veuillez vérifier votre email et mot de passe");
        }

        if (!email.equals(userDetails.getUsername())) {
            throw new BadCredentialsException("Identifiants incorrects ! Veuillez vérifier votre email et mot de passe");
        }

        if (!passwordEncoder.matches(motDePasse, userDetails.getPassword())) {
            throw new BadCredentialsException("Identifiants incorrects ! Veuillez vérifier votre email et mot de passe");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}

