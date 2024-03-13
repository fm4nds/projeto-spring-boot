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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
}
