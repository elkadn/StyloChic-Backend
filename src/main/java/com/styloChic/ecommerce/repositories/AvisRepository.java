package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvisRepository extends JpaRepository<Avis,Long> {

    @Query("SELECT a FROM Avis a WHERE a.produit.id=:idProduit")
    public List<Avis> avoirTousProduitsAvis(@Param("idProduit") Long idProduit);

    @Query("SELECT COUNT(a) FROM Avis a WHERE a.produit.id = :idProduit")
    Long compterAvisAvecIdProduit(@Param("idProduit") Long idProduit);

    @Query("SELECT a FROM Avis a WHERE a.produit.id = :idProduit ORDER BY RAND()")
    List<Avis> trouverAvisAloitoireAvecPrduitId(@Param("idProduit") Long idProduit);
}
