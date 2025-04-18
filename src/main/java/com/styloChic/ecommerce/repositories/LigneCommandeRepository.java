package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande,Long> {

    @Query("SELECT lc.produit, SUM(lc.quantite) as totalVentes " +
            "FROM LigneCommande lc " +
            "GROUP BY lc.produit " +
            "ORDER BY totalVentes DESC " +
            "LIMIT 10")
    List<Object[]> findTop10ProductsBySales();
}
