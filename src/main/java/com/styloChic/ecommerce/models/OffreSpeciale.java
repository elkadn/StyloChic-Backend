package com.styloChic.ecommerce.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class OffreSpeciale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String urlImage;
    private String descriptionImage;

    private boolean visibilite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Utilisateur admin;

    private LocalDate dateAjout;
    private LocalDate dateModification;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_id")
    private Produit produit;

}
