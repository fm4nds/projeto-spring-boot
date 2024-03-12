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

    @OneToOne
    @JoinColumn (name = "idcursos", referencedColumnName = "id")
    private Curso idcurso;

    @OneToOne
    @JoinColumn (name = "idEstudante", referencedColumnName = "id")
    private Estudante idEstudante;
}
