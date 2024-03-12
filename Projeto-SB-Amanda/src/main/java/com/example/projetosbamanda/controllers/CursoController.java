package com.example.projetosbamanda.controllers;

import com.example.projetosbamanda.models.Curso;
import com.example.projetosbamanda.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CursoController {
    private CursoService cursoService;
    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }
    @GetMapping
    public Optional<ResponseEntity<List<Curso>>> listaDeCursos() {
        List<Curso> = cursoService.
    }

}
