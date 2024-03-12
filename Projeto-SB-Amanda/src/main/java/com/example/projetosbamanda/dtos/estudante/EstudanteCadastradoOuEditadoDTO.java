package com.example.projetosbamanda.dtos.estudante;

import java.util.Date;
import java.util.UUID;

public record EstudanteCadastradoOuEditadoDTO(UUID id, String nome, Date dataMatricula) {
}
