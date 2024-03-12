package com.example.projetosbamanda.models;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table (name = "Estudante")
public class Estudante {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID id;
    @Column (nullable = false, unique = true)
    private String nome;
    @Column (nullable = false)
    private Date dataMatricula;
}
