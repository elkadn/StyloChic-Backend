package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande,Long> {
}
