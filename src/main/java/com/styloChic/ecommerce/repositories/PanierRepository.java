package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PanierRepository extends JpaRepository<Panier,Long> {
    @Query("SELECT p FROM Panier p WHERE p.utilisateur.id=:idUtilisateur")
    public Panier chercherPanierParUtilisateurId(@Param("idUtilisateur") Long idUtilisateur);
}
