package com.styloChic.ecommerce.repositories;


import com.styloChic.ecommerce.models.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {
}
