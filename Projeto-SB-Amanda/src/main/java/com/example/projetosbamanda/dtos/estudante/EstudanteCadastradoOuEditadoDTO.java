package com.example.projetosbamanda.dtos.estudante;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public record EstudanteCadastradoOuEditadoDTO(UUID id, String nome, LocalDateTime dataMatricula) {
}
