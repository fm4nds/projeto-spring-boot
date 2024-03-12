package com.example.projetosbamanda.services;

import com.example.projetosbamanda.models.Curso;
import com.example.projetosbamanda.repositories.CursoRepository;
import com.example.projetosbamanda.services.interfaces.CursoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService implements CursoInterface {
    private CursoRepository cursoRepository;
    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }
    @Override
    public List<Curso> listaDeCursos() {
        return cursoRepository.findAll();
    }

}
