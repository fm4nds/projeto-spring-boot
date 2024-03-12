package com.example.projetosbamanda.services;

import com.example.projetosbamanda.dtos.CadastrarOuEditarCursoDTO;
import com.example.projetosbamanda.models.Curso;
import com.example.projetosbamanda.repositories.CursoRepository;
import com.example.projetosbamanda.services.interfaces.CursoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    @Override
    public Optional<Curso> buscarCursoPorId(UUID id) {
        return cursoRepository.findById(id);
    }
    @Override
    public Curso cadastrarCurso(CadastrarOuEditarCursoDTO cadastrarCursoDTO) {
        Curso curso = new Curso();
        curso.setTitulo(cadastrarCursoDTO.titulo());
        curso.setCreditos(cadastrarCursoDTO.creditos());
        return cursoRepository.save(curso);
    }
    @Override
    public Curso atualizarCurso(UUID id, CadastrarOuEditarCursoDTO atualizarCursoDTO) {
        Curso curso = new Curso();
        curso.setId(id);
        curso.setTitulo(atualizarCursoDTO.titulo());
        curso.setCreditos(atualizarCursoDTO.creditos());
        return cursoRepository.save(curso);
    }
    @Override
    public void deletarCurso(Curso curso) {
        cursoRepository.delete(curso);
    }
}
