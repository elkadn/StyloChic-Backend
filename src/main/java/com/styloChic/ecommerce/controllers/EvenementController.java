package com.styloChic.ecommerce.controllers;


import com.styloChic.ecommerce.models.Evenement;
import com.styloChic.ecommerce.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/styloChic/evenements")
public class EvenementController {

    @Autowired
    private EvenementService evenementService;

    @GetMapping
    public ResponseEntity<List<Evenement>> getAllEvents() {
        List<Evenement> events = evenementService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        evenementService.deleteEvent(id);
        return ResponseEntity.ok().body("Event deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evenement> updateEvent(@PathVariable Long id, @RequestBody Evenement eventDetails) {
        Evenement updatedEvent = evenementService.updateEvent(id, eventDetails);
        return ResponseEntity.ok(updatedEvent);
    }

    @PostMapping
    public ResponseEntity<Evenement> createEvent(@RequestBody Evenement event) {
        Evenement createdEvent = evenementService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }
}