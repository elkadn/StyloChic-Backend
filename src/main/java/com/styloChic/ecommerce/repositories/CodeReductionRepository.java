package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.exceptions.CodeReductionException;
import com.styloChic.ecommerce.models.CodeReduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CodeReductionRepository extends JpaRepository<CodeReduction,Long> {

    CodeReduction findByCode(String code);

    @Query("SELECT count(*) FROM CodeReduction c")
    long compterCodesPromos(String jwt) throws CodeReductionException;
}
