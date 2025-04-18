package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.models.Contact;
import com.styloChic.ecommerce.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactClientController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestParam String message) {
        Contact newContact = contactService.createContact(message);
        return ResponseEntity.ok(newContact);
    }

}