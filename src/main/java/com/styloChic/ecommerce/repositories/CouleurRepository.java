package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.Couleur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CouleurRepository extends JpaRepository<Couleur,Long> {
        Optional<Couleur> findByNom(String nom);

        Optional<Couleur> findByNomIgnoreCase(String nom);

        @Query("SELECT c.nom FROM Couleur c")
        List<String> findAllNoms();


}
