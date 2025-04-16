package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.DemmandePersonnalisation;
import com.styloChic.ecommerce.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandePersonnalisationRepository extends JpaRepository<DemmandePersonnalisation,Long> {

    List<DemmandePersonnalisation> findByUtilisateur(Utilisateur utilisateur);

}
