package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.exceptions.CategorieException;
import com.styloChic.ecommerce.models.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    int countByCategorieParente(Categorie categorieParente);
    Categorie findByNom(String nom);

    List<Categorie> findByNiveau(int niveau);

    @Query("SELECT c FROM Categorie c WHERE c.nom = :nom AND c.categorieParente.nom = :nomCategorieParente")
    public Categorie chercherParNomEtParente(@Param("nom") String nom,@Param("nomCategorieParente") String nomCategorieParente);

    @Query("SELECT c.nom FROM Categorie c WHERE c.niveau = 3")
    List<String> findNomsByNiveau3();

    @Query("SELECT count(*) FROM Categorie c WHERE c.niveau = 3")
    long compterCategories() throws CategorieException;

    @Query("SELECT c.nom, COALESCE(SUM(lc.quantite * lc.prixTTCReduit), 0) " +
            "FROM Categorie c " +
            "LEFT JOIN Produit p ON p.categorie.id = c.id OR p.categorie.categorieParente.id = c.id OR p.categorie.categorieParente.categorieParente.id = c.id " +
            "LEFT JOIN LigneCommande lc ON lc.produit.id = p.id " +
            "LEFT JOIN Commande cmd ON lc.commande.id = cmd.id " +
            "WHERE c.niveau = 1 " +
            "AND cmd.statutCommande = 'Livrée' " +
            "GROUP BY c.nom")
    List<Object[]> avoirVentePaCategorie();

}
