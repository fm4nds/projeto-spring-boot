package com.example.projetosbamanda.controllers;

import com.example.projetosbamanda.dtos.CadastrarOuEditarCursoDTO;
import com.example.projetosbamanda.dtos.CursoCadastradoOuEditadoDTO;
import com.example.projetosbamanda.models.Curso;
import com.example.projetosbamanda.services.CursoService;
import jakarta.transaction.Status;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CursoController {
    private CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listaDeCursos() {
        List<Curso> todosOsCursos = cursoService.listaDeCursos();
        if (todosOsCursos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todosOsCursos);
    }

    @GetMapping
    public ResponseEntity<Optional<Curso>> buscarCursoPorId(@PathVariable UUID idCurso) {
        Optional<Curso> cursoEncontrado = cursoService.buscarCursoPorId(idCurso);
        if (cursoEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cursoEncontrado);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<CursoCadastradoOuEditadoDTO> cadastrarCurso(@RequestBody CadastrarOuEditarCursoDTO criarCursoDTO) {
        Curso cursoCadastrado = cursoService.cadastrarCurso(criarCursoDTO);
        if (cursoCadastrado == null) {
            return ResponseEntity.internalServerError().build();
        }
        CursoCadastradoOuEditadoDTO cursoCadastradoDTO = new CursoCadastradoOuEditadoDTO(cursoCadastrado.getId(),
                cursoCadastrado.getTitulo(), cursoCadastrado.getCreditos());
        return ResponseEntity.ok(cursoCadastradoDTO);
    }


    @Transactional
    @PutMapping
    public ResponseEntity<CursoCadastradoOuEditadoDTO> editarCurso(@PathVariable UUID idCurso, @RequestBody CadastrarOuEditarCursoDTO atualizarCursoDTO) {
        Optional<Curso> cursoEncontrado = cursoService.buscarCursoPorId(idCurso);
        if (cursoEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        cursoService.atualizarCurso(idCurso, atualizarCursoDTO);
        CursoCadastradoOuEditadoDTO cursoAtualizadoDTO = new CursoCadastradoOuEditadoDTO(idCurso,
                atualizarCursoDTO.titulo(), atualizarCursoDTO.creditos());
        return ResponseEntity.ok(cursoAtualizadoDTO);
    }

    @DeleteMapping
    public ResponseEntity<Status> deletarCurso(@PathVariable UUID idCurso) {
        Optional<Curso> cursoEncontrado = cursoService.buscarCursoPorId(idCurso);
        if (cursoEncontrado.isEmpty()) {

            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
