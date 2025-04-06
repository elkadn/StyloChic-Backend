package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.CodeReduction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeReductionRepository extends JpaRepository<CodeReduction,Long> {

    CodeReduction findByCode(String code);
}
