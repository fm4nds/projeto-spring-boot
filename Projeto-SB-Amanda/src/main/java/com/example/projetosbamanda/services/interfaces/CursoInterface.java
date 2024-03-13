package com.example.projetosbamanda.services.interfaces;

import com.example.projetosbamanda.dtos.curso.CadastrarOuEditarCursoDTO;
import com.example.projetosbamanda.models.Curso;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CursoInterface {
    List<Curso> listaDeCursos();

    Optional<Curso> buscarCursoPorId(UUID id);

    Curso cadastrarCurso(CadastrarOuEditarCursoDTO cadastrarCursoDTO);

    Curso atualizarCurso(UUID id, CadastrarOuEditarCursoDTO atualizarCursoDTO);

    void deletarCurso(UUID idCurso);
}
