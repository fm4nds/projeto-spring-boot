package com.example.projetosbamanda.dtos.matricula;

import com.example.projetosbamanda.models.Curso;
import com.example.projetosbamanda.models.Estudante;

import java.util.UUID;

public record CadastrarOuEditarMatriculaDTO(Curso idCurso, Estudante idEstudante) {
}
