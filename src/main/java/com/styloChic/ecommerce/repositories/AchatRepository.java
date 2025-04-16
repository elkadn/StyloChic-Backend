package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.Achat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchatRepository extends JpaRepository<Achat, Long> {
    List<Achat> findByFournisseurId(Long fournisseurId);
}