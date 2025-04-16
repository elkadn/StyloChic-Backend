package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.RetourClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetourClientRepository extends JpaRepository<RetourClient,Long> {
    List<RetourClient> findByCommandeUtilisateurId(Long utilisateurId);
    List<RetourClient> findByCommandeId(Long commandeId);
}
