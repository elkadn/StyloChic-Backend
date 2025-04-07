package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.Categorie;
import com.styloChic.ecommerce.models.NavBarElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NavBarElementRepository extends JpaRepository<NavBarElement,Long> {

    NavBarElement findByCategorie(Categorie categorie);

    @Query("SELECT e FROM NavBarElement e WHERE e.nom = :nom AND e.navBarSection.id = :categorieParenteId")
    public NavBarElement findByNomAndParent(@Param("nom") String nom, @Param("categorieParenteId") long categorieParenteId);
}
