package com.styloChic.ecommerce.services;

import com.styloChic.ecommerce.config.JwtProvider;
import com.styloChic.ecommerce.exceptions.ContactException;
import com.styloChic.ecommerce.exceptions.CouleurException;
import com.styloChic.ecommerce.models.Contact;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.repositories.ContactRepository;
import com.styloChic.ecommerce.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private ContactRepository contactRepository;

    public Contact createContact(String message) {
        Contact contact = new Contact(message);
        return contactRepository.save(contact);
    }

    public List<Contact> getAllContacts(String jwt) throws ContactException {
        verifierAdmin(jwt);
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id,String jwt) throws ContactException{
        verifierAdmin(jwt);
        return contactRepository.findById(id);
    }

    private Utilisateur verifierAdmin(String jwt) throws ContactException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Utilisateur admin = utilisateurRepository.chercherParEmail(email);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            throw new ContactException("Accès interdit : vous devez être un administrateur !");
        }
        return admin;
    }
}