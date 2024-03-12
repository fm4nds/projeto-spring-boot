package com.example.projetosbamanda.controllers;

import com.example.projetosbamanda.dtos.CadastrarOuEditarEstudanteDTO;
import com.example.projetosbamanda.dtos.EstudanteCadastradoOuEditadoDTO;
import com.example.projetosbamanda.models.Estudante;
import com.example.projetosbamanda.services.EstudanteService;
import com.example.projetosbamanda.services.EstudanteService;
import jakarta.transaction.Status;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class EstudanteController {
    final private EstudanteService estudanteService;
    @Autowired
    public EstudanteController (EstudanteService estudanteService) {
        this.estudanteService = estudanteService;
    }
    @GetMapping
    public ResponseEntity<List<Estudante>> listaDeEstudantes() {
        List<Estudante> todosOsEstudantes = estudanteService.listaDeEstudantes();
        if (todosOsEstudantes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todosOsEstudantes);
    }

    @GetMapping
    public ResponseEntity<Optional<Estudante>> buscarEstudantePorId(@PathVariable UUID idEstudante) {
        Optional<Estudante> estudanteEncontrado = estudanteService.buscarEstudantePorId(idEstudante);
        if (estudanteEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estudanteEncontrado);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<EstudanteCadastradoOuEditadoDTO> cadastrarEstudante(@RequestBody CadastrarOuEditarEstudanteDTO criarEstudanteDTO) {
        Estudante estudanteCadastrado = estudanteService.cadastrarEstudante(criarEstudanteDTO);
        if (estudanteCadastrado == null) {
            return ResponseEntity.internalServerError().build();
        }
        EstudanteCadastradoOuEditadoDTO estudanteCadastradoDTO = new EstudanteCadastradoOuEditadoDTO(estudanteCadastrado.getId(),
                estudanteCadastrado.getNome(), estudanteCadastrado.getDataMatricula());
        return ResponseEntity.ok(estudanteCadastradoDTO);
    }


    @Transactional
    @PutMapping
    public ResponseEntity<EstudanteCadastradoOuEditadoDTO> editarEstudante(@PathVariable UUID idEstudante, @RequestBody CadastrarOuEditarEstudanteDTO atualizarEstudanteDTO) {
        Optional<Estudante> estudanteEncontrado = estudanteService.buscarEstudantePorId(idEstudante);
        if (estudanteEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        estudanteService.atualizarEstudante(idEstudante, atualizarEstudanteDTO);
        EstudanteCadastradoOuEditadoDTO estudanteAtualizadoDTO = new EstudanteCadastradoOuEditadoDTO(idEstudante,
                atualizarEstudanteDTO.nome(), atualizarEstudanteDTO.dataMatricula());
        return ResponseEntity.ok(estudanteAtualizadoDTO);
    }

    @DeleteMapping
    public ResponseEntity<Status> deletarEstudante(@PathVariable UUID idEstudante) {
        Optional<Estudante> estudanteEncontrado = estudanteService.buscarEstudantePorId(idEstudante);
        if (estudanteEncontrado.isEmpty()) {

            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}

