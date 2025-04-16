package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.dtos.CommandeDTO;
import com.styloChic.ecommerce.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande,Long> {
    @Query("SELECT c FROM Commande c WHERE c.utilisateur.id = :utilisateurId AND (c.statutCommande = 'En cours de préparation' OR c.statutCommande = 'Confirmée' OR c.statutCommande = 'Expédiée' OR c.statutCommande = 'Livrée' OR c.statutCommande = 'Annulée')")
    public List<Commande> avoirCommandesUtilisateur(@Param("utilisateurId") Long utilisateurId);
}
