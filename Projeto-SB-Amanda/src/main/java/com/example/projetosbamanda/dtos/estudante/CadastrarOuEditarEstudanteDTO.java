package com.example.projetosbamanda.dtos.estudante;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public record CadastrarOuEditarEstudanteDTO(String nome) {
    public CadastrarOuEditarEstudanteDTO {
        if(nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome é obrigatório");
        }
    }
}

