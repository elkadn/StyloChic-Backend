package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.Categorie;
import com.styloChic.ecommerce.models.NavBarCategorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NavBarCategorieRepository extends JpaRepository<NavBarCategorie,Long> {

    Optional<NavBarCategorie> findByNom(String nom);

    NavBarCategorie findByCategorie(Categorie categorie);

}
