package com.example.projetosbamanda.controllers;

import com.example.projetosbamanda.dtos.estudante.CadastrarOuEditarEstudanteDTO;
import com.example.projetosbamanda.dtos.estudante.EstudanteCadastradoOuEditadoDTO;
import com.example.projetosbamanda.models.Estudante;
import com.example.projetosbamanda.models.Matricula;
import com.example.projetosbamanda.services.EstudanteService;
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
@RequestMapping("/estudante")
public class EstudanteController {
    final private EstudanteService estudanteService;
    final private MatriculaService matriculaService;
    @Autowired
    public EstudanteController (EstudanteService estudanteService, MatriculaService matriculaService) {
        this.estudanteService = estudanteService;
        this.matriculaService = matriculaService;
    }
    @GetMapping
    public ResponseEntity<List<Estudante>> listaDeEstudantes() {
        List<Estudante> todosOsEstudantes = estudanteService.listaDeEstudantes();
        if (todosOsEstudantes.isEmpty()) {
            return ResponseEntity.notFound().build();
    }
        return ResponseEntity.ok(todosOsEstudantes);
    }

    @GetMapping("/{idEstudante}")
    public ResponseEntity<Optional<Estudante>> buscarEstudantePorId(@PathVariable UUID idEstudante) {
        Optional<Estudante> estudanteEncontrado = estudanteService.buscarEstudantePorId(idEstudante);
        if (estudanteEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        System.out.println(estudanteEncontrado.get());
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
    @PatchMapping("/{idEstudante}")
    public ResponseEntity<EstudanteCadastradoOuEditadoDTO> editarEstudante(@PathVariable UUID idEstudante, @RequestBody CadastrarOuEditarEstudanteDTO atualizarEstudanteDTO) {
        Optional<Estudante> estudanteEncontrado = estudanteService.buscarEstudantePorId(idEstudante);
        if (estudanteEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Estudante estudante = estudanteService.atualizarEstudante(idEstudante, atualizarEstudanteDTO);
        EstudanteCadastradoOuEditadoDTO estudanteAtualizadoDTO = new EstudanteCadastradoOuEditadoDTO(idEstudante,
                atualizarEstudanteDTO.nome(), estudante.getDataMatricula());
        return ResponseEntity.ok(estudanteAtualizadoDTO);
    }

    @DeleteMapping("/{idEstudante}")
    public ResponseEntity<Status> deletarEstudante(@PathVariable UUID idEstudante) {
        Optional<Estudante> estudanteEncontrado = estudanteService.buscarEstudantePorId(idEstudante);
        if (estudanteEncontrado.isEmpty()) {

            return ResponseEntity.notFound().build();
        }
        Optional<List<Matricula>> existeMatricula = matriculaService.buscarMatriculaComIdDoEstudante(idEstudante);

        if(existeMatricula.isPresent() && !existeMatricula.get().isEmpty()) {
            throw new IllegalArgumentException("O estudante não pode ser deletado pois há matriculas relacionadas ativas");
        }
        estudanteService.deletarEstudante(idEstudante);
        return ResponseEntity.noContent().build();
    }
}