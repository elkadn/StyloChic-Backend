package com.styloChic.ecommerce.repositories;

import com.styloChic.ecommerce.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
