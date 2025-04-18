package com.styloChic.ecommerce.services;


import com.styloChic.ecommerce.models.Evenement;
import com.styloChic.ecommerce.repositories.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvenementService {


    @Autowired
    private EvenementRepository evenementRepository;



    public List<Evenement> getAllEvents() {
        return evenementRepository.findAll();
    }

    public void deleteEvent(Long id) {
        evenementRepository.deleteById(id);
    }

    public Evenement updateEvent(Long id, Evenement evenementDetails) {
        Evenement evenement = evenementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evenement non trouvé avec id: " + id));

        evenement.setTitre(evenementDetails.getTitre());
        evenement.setDebut(evenementDetails.getDebut());
        evenement.setFin(evenementDetails.getFin());
        evenement.setTousJours(evenementDetails.getTousJours());

        return evenementRepository.save(evenement);
    }

    public Evenement createEvent(Evenement event) {
        return evenementRepository.save(event);
    }
}