package com.styloChic.ecommerce.controllers;

import com.styloChic.ecommerce.exceptions.ContactException;
import com.styloChic.ecommerce.models.Contact;
import com.styloChic.ecommerce.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/styloChic/contacts/admin")
public class ContactAdminController {

    @Autowired
    private ContactService contactService;



    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts(@RequestHeader("Authorization") String jwt) throws ContactException {
        List<Contact> contacts = contactService.getAllContacts(jwt);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws ContactException{
        return contactService.getContactById(id,jwt)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}