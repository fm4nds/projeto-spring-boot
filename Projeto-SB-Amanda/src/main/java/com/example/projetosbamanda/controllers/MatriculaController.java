package com.example.projetosbamanda.controllers;
import com.example.projetosbamanda.dtos.matricula.CadastrarOuEditarMatriculaDTO;
import com.example.projetosbamanda.dtos.matricula.MatriculaCadastradaOuEditadaDTO;
import com.example.projetosbamanda.models.Curso;
import com.example.projetosbamanda.models.Estudante;
import com.example.projetosbamanda.models.Matricula;
import com.example.projetosbamanda.services.CursoService;
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
@RequestMapping("/matricula")
public class MatriculaController { 
    final private MatriculaService matriculaService;
    final private EstudanteService estudanteService;
    final private CursoService cursoService;
    @Autowired
    public MatriculaController (CursoService cursoService, MatriculaService matriculaService, EstudanteService estudanteService) {
        this.estudanteService = estudanteService;
        this.matriculaService = matriculaService;
        this.cursoService = cursoService;
    }
    @GetMapping
    public ResponseEntity<List<Matricula>> listaDeMatriculas() {
        List<Matricula> todasAsMatriculas = matriculaService.listaDeMatriculas();
        if (todasAsMatriculas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todasAsMatriculas);
    }

    @GetMapping("/{idMatricula}")
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

        Optional<Estudante> estudanteEncontrado = estudanteService.buscarEstudantePorId(criarMatriculaDTO.idEstudante().getId());
        if(estudanteEncontrado.isEmpty()){
            throw new IllegalArgumentException("Estudante não encontrado");
        }
        Optional<Curso> cursoEncontrado = cursoService.buscarCursoPorId(criarMatriculaDTO.idCurso().getId());
        if(cursoEncontrado.isEmpty()){
            throw new IllegalArgumentException("Curso não encontrado");
        }


        Matricula matriculaCadastrada = matriculaService.cadastrarMatricula(criarMatriculaDTO);
        if (matriculaCadastrada == null) {
            return ResponseEntity.internalServerError().build();
        }
        MatriculaCadastradaOuEditadaDTO matriculaCadastradaDTO = new MatriculaCadastradaOuEditadaDTO(matriculaCadastrada.getId());
        return ResponseEntity.ok(matriculaCadastradaDTO);
    }


    @Transactional
    @PatchMapping("/{idMatricula}")
    public ResponseEntity<MatriculaCadastradaOuEditadaDTO> editarMatricula(@PathVariable UUID idMatricula, @RequestBody CadastrarOuEditarMatriculaDTO atualizarMatriculaDTO) {
        Optional<Matricula> matriculaEncontrada = matriculaService.buscarMatriculaPorId(idMatricula);
        if (matriculaEncontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Estudante> estudanteEncontrado = estudanteService.buscarEstudantePorId(atualizarMatriculaDTO.idEstudante().getId());
        if(estudanteEncontrado.isEmpty()){
            throw new IllegalArgumentException("Estudante não encontrado");
        }
        Optional<Curso> cursoEncontrado = cursoService.buscarCursoPorId(atualizarMatriculaDTO.idCurso().getId());
        if(cursoEncontrado.isEmpty()){
            throw new IllegalArgumentException("Curso não encontrado");
        }

        Matricula matriculaAtualizada = matriculaService.atualizarMatricula(idMatricula, atualizarMatriculaDTO);
        MatriculaCadastradaOuEditadaDTO matriculaAtualizadaDTO = new MatriculaCadastradaOuEditadaDTO(matriculaAtualizada.getId());
        return ResponseEntity.ok(matriculaAtualizadaDTO);
    }

    @DeleteMapping("/{idMatricula}")
    public ResponseEntity<Status> deletarMatricula(@PathVariable UUID idMatricula) {
        Optional<Matricula> matriculaEncontrada = matriculaService.buscarMatriculaPorId(idMatricula);
        if (matriculaEncontrada.isEmpty()) {

            return ResponseEntity.notFound().build();
        }
        matriculaService.deletarMatricula(idMatricula);
        return ResponseEntity.noContent().build();
    }
}
