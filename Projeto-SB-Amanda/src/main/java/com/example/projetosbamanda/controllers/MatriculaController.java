package com.example.projetosbamanda.controllers;

import com.example.projetosbamanda.dtos.matricula.CadastrarOuEditarMatriculaDTO;
import com.example.projetosbamanda.dtos.matricula.MatriculaCadastradaOuEditadaDTO;
import com.example.projetosbamanda.models.Matricula;
import com.example.projetosbamanda.services.MatriculaService;
import jakarta.transaction.Status;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class MatriculaController { 
    final private MatriculaService matriculaService;
    @Autowired
    public MatriculaController (MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }
    @GetMapping
    public ResponseEntity<List<Matricula>> listaDeMatriculas() {
        List<Matricula> todasAsMatriculas = matriculaService.listaDeMatriculas();
        if (todasAsMatriculas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todasAsMatriculas);
    }

    @GetMapping
    public ResponseEntity<Optional<Matricula>> buscarMatriculaPorId(@PathVariable UUID idMatricula) {
        Optional<Matricula> matriculaEncontrada = matriculaService.buscarMatriculaPorId(idMatricula);
        if (matriculaEncontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(matriculaEncontrada);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<MatriculaCadastradaOuEditadaDTO> cadastrarMatricula(@RequestBody CadastrarOuEditarMatriculaDTO criarMatriculaDTO) {
        Matricula matriculaCadastrada = matriculaService.cadastrarMatricula(criarMatriculaDTO);
        if (matriculaCadastrada == null) {
            return ResponseEntity.internalServerError().build();
        }
        MatriculaCadastradaOuEditadaDTO matriculaCadastradaDTO = new MatriculaCadastradaOuEditadaDTO(matriculaCadastrada.getId(),
                matriculaCadastrada.getIdcurso().getId(), matriculaCadastrada.getIdEstudante().getId());
        return ResponseEntity.ok(matriculaCadastradaDTO);
    }


    @Transactional
    @PutMapping
    public ResponseEntity<MatriculaCadastradaOuEditadaDTO> editarMatricula(@PathVariable UUID idMatricula, @RequestBody CadastrarOuEditarMatriculaDTO atualizarMatriculaDTO) {
        Optional<Matricula> matriculaEncontrada = matriculaService.buscarMatriculaPorId(idMatricula);
        if (matriculaEncontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        matriculaService.atualizarMatricula(idMatricula, atualizarMatriculaDTO);
        MatriculaCadastradaOuEditadaDTO matriculaAtualizadaDTO = new MatriculaCadastradaOuEditadaDTO(idMatricula,
                atualizarMatriculaDTO.idCurso(), atualizarMatriculaDTO.idEstudante());
        return ResponseEntity.ok(matriculaAtualizadaDTO);
    }

    @DeleteMapping
    public ResponseEntity<Status> deletarMatricula(@PathVariable UUID idMatricula) {
        Optional<Matricula> matriculaEncontrada = matriculaService.buscarMatriculaPorId(idMatricula);
        if (matriculaEncontrada.isEmpty()) {

            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
