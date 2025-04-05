package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    int countByCategorieParente(Categorie categorieParente);
    Categorie findByNom(String nom);

    @Query("SELECT c FROM Categorie c WHERE c.nom = :nom AND c.categorieParente.nom = :nomCategorieParente")
    public Categorie chercherParNomEtParente(@Param("nom") String nom,@Param("nomCategorieParente") String nomCategorieParente);


}
