package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.Categorie;
import com.styloChic.ecommerce.models.NavBarSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface NavBarSectionRepository extends JpaRepository<NavBarSection,Long> {

    Optional<NavBarSection> findByNom(String nom);
    NavBarSection findByCategorie(Categorie categorie);

    @Query("SELECT s FROM NavBarSection s WHERE s.nom = :nom AND s.navBarCategorie.nom = :nomCategorieParente")
    public NavBarSection findByNomAndParent(@Param("nom") String name, @Param("nomCategorieParente") String nomCategorieParente);
}
