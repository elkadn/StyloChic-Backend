package com.styloChic.ecommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/status")
    public String getStatus() {
        return "API is up and running!";
    }

    @GetMapping("/data")
    public TestData getData() {
        // Exemple de données statiques
        return new TestData("Hello", "World");
    }

    // Classe interne pour les données statiques
    public static class TestData {
        private String message;
        private String description;

        public TestData(String message, String description) {
            this.message = message;
            this.description = description;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
