package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.OffreSpeciale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OffreSpecialeRepository extends JpaRepository<OffreSpeciale,Long> {

    Optional<OffreSpeciale> findByNom(String nom);
}
