package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.dtos.CommandeDTO;
import com.styloChic.ecommerce.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande,Long> {
    @Query("SELECT c FROM Commande c WHERE c.utilisateur.id = :utilisateurId AND (c.statutCommande = 'PLACÉE' OR c.statutCommande = 'CONFIRMÉE' OR c.statutCommande = 'EXPÉDIÉE' OR c.statutCommande = 'LIVRÉE')")
    public List<Commande> avoirCommandesUtilisateur(@Param("utilisateurId") Long utilisateurId);
}
