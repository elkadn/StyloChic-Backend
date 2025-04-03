package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.repositories.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurServiceParticulierImplementation implements UserDetailsService {

    private UtilisateurRepository utilisateurRepository;
    public UtilisateurServiceParticulierImplementation(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Utilisateur utilisateur = utilisateurRepository.chercherParEmail(email);
        if(utilisateur ==null){
            throw new UsernameNotFoundException("Aucun utilisateur trouvé avec l'identifiant : "+ email+ " !");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(utilisateur.getEmail(),utilisateur.getMotDePasse(),authorities);
    }
}
