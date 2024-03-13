package com.example.projetosbamanda.dtos.curso;

public record CadastrarOuEditarCursoDTO(String titulo, int creditos) {
    public CadastrarOuEditarCursoDTO {
        if(titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("O título é obrigatório");
        }
        if(creditos <= 0) {
            throw new IllegalArgumentException("Créditos deve ser maior que 0");
        }
    }
}
