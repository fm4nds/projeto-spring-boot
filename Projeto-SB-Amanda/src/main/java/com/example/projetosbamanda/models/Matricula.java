package com.example.projetosbamanda.models;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "Matricula")
public class Matricula {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Curso getIdcurso() {
        return idCurso;
    }

    public void setIdcurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    public Estudante getIdEstudante() {
        return idEstudante;
    }

    public void setIdEstudante(Estudante idEstudante) {
        this.idEstudante = idEstudante;
    }

    @OneToOne
    @JoinColumn (name = "idCursos", referencedColumnName = "id")
    private Curso idCurso;

    @OneToOne
    @JoinColumn (name = "idEstudante", referencedColumnName = "id")
    private Estudante idEstudante;
}
