package com.example.projetosbamanda.models;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "Curso")
public class Curso {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID id;
    @Column (nullable = false)
    private String titulo;
    @Column (nullable = false)
    private int creditos;
}
