package com.styloChic.ecommerce.repositories;


import com.styloChic.ecommerce.exceptions.PubiliciteException;
import com.styloChic.ecommerce.models.Publicite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PubliciteRepository extends JpaRepository<Publicite,Long> {

    @Query("SELECT count(*) FROM Publicite p")
    long compterPublicites(String jwt) throws PubiliciteException;

}
