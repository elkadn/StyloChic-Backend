package com.styloChic.ecommerce.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Publicite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urlImage;
    private String structureHiérarchique;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Utilisateur admin;

    LocalDateTime dateAjout;
    LocalDateTime dateModification;



}
