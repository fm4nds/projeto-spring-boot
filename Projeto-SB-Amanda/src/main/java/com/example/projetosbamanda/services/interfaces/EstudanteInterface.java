package com.example.projetosbamanda.services.interfaces;

import com.example.projetosbamanda.dtos.CadastrarOuEditarEstudanteDTO;
import com.example.projetosbamanda.models.Estudante;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EstudanteInterface {
    List<Estudante> listaDeEstudantes();

    Optional<Estudante> buscarEstudantePorId(UUID id);

    Estudante cadastrarEstudante(CadastrarOuEditarEstudanteDTO cadastrarEstudanteDTO);

    Estudante atualizarEstudante(UUID id, CadastrarOuEditarEstudanteDTO atualizarEstudanteDTO);

    void deletarEstudante(Estudante estudante);
}
