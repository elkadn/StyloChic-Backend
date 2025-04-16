package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.LigneRetour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneRetourRepository extends JpaRepository<LigneRetour,Long> {
    List<LigneRetour> findByRetourId(Long retourId);

}
