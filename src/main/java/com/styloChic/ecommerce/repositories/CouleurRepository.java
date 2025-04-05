package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.Couleur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouleurRepository extends JpaRepository<Couleur,Long> {
        Optional<Couleur> findByNom(String nom);

        Optional<Couleur> findByNomIgnoreCase(String nom);


}
