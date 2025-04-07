package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepository extends JpaRepository<Fournisseur,Long> {
    boolean existsByEmail(String email);

    long countByProduitsFournisseur(Fournisseur fournisseur);

}
