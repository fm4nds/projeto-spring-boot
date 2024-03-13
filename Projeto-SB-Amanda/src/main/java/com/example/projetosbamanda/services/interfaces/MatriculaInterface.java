package com.example.projetosbamanda.services.interfaces;

import com.example.projetosbamanda.dtos.matricula.CadastrarOuEditarMatriculaDTO;
import com.example.projetosbamanda.models.Matricula;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MatriculaInterface {
    List<Matricula> listaDeMatriculas();

    Optional<Matricula> buscarMatriculaPorId(UUID id);

    Matricula cadastrarMatricula(CadastrarOuEditarMatriculaDTO cadastrarMatriculaDTO);

    Matricula atualizarMatricula(UUID id, CadastrarOuEditarMatriculaDTO atualizarMatriculaDTO);


    void deletarMatricula(UUID id);

    Optional<List<Matricula>> buscarMatriculaComIdDoCurso(UUID idCurso);

    Optional<List<Matricula>> buscarMatriculaComIdDoEstudante(UUID idEstudante);
}
