package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.exceptions.ProduitException;
import com.styloChic.ecommerce.models.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
    @Query("SELECT p FROM Produit p " +
            "WHERE (p.categorie.nom = :categorie OR :categorie = '') " +
            "AND ((:prixMin IS NULL AND :prixMax IS NULL) OR (p.prixVenteTTC BETWEEN :prixMin AND :prixMax)) " +
            "ORDER BY " +
            "CASE WHEN :trie = 'prix_bas' THEN p.prixVenteTTC END ASC, " +
            "CASE WHEN :trie = 'prix_eleve' THEN p.prixVenteTTC END DESC"
    )
    List<Produit> filterProduit(@Param("categorie") String categorie,
                                @Param("prixMin") Integer prixMin,
                                @Param("prixMax") Integer prixMax,
                                @Param("trie") String trie);

    @Query("SELECT p FROM Produit p WHERE LOWER(p.titre) LIKE LOWER(CONCAT('%', :requete, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :requete, '%'))")
    Page<Produit> searchByTitreOrDescription(@Param("requete") String requete, Pageable pageable);



    @Query(value = "SELECT DISTINCT tp.nom FROM tailles_produit tp", nativeQuery = true)
    List<String> findDistinctTailleNomsNative();





}
