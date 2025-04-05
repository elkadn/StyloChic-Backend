package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.Produit;
import com.styloChic.ecommerce.models.Utilisateur;
import com.styloChic.ecommerce.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT v FROM Vote v WHERE v.produit.id = :idProduit")
    List<Vote> findAllVotesByProduct(@Param("idProduit") Long idProduit);

    @Query("SELECT SUM(v.vote) FROM Vote v WHERE v.produit.id = :idProduit")
    Double sumVotesForProduct(@Param("idProduit") Long idProduit);

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.produit.id = :idProduit")
    Long countVotesForProduct(@Param("idProduit") Long idProduit);

    Optional<Vote> findByProduitAndUtilisateur(Produit produit, Utilisateur utilisateur);

    long countByProduitId(Long idProduit);
}
