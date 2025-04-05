package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.ElementPanier;
import com.styloChic.ecommerce.models.Panier;
import com.styloChic.ecommerce.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ElementPanierRepository extends JpaRepository<ElementPanier,Long> {
    @Query("SELECT ep FROM ElementPanier ep WHERE ep.panier=:panier AND ep.produit=:produit AND ep.taille=:taille AND ep.utilisateurId=:utilisateurId")
    public ElementPanier elementPanierExiste(@Param("panier") Panier panier, @Param("produit") Produit produit, @Param("taille") String taille, @Param("utilisateurId") Long utilisateurId);
}
