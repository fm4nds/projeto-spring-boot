package com.example.projetosbamanda.services;

import com.example.projetosbamanda.dtos.matricula.CadastrarOuEditarMatriculaDTO;
import com.example.projetosbamanda.models.Matricula;
import com.example.projetosbamanda.repositories.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MatriculaService implements com.example.projetosbamanda.services.interfaces.MatriculaInterface {
    private MatriculaRepository matriculaRepository;
    @Autowired
    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }
    @Override
    public List<Matricula> listaDeMatriculas() {
        return matriculaRepository.findAll();
    }
    @Override
    public Optional<Matricula> buscarMatriculaPorId(UUID id) {
        return matriculaRepository.findById(id);
    }
    @Override
    public Matricula cadastrarMatricula(CadastrarOuEditarMatriculaDTO cadastrarMatriculaDTO) {
        Matricula matricula = new Matricula();
        matricula.setIdcurso(cadastrarMatriculaDTO.idCurso());
        matricula.setIdEstudante(cadastrarMatriculaDTO.idEstudante());
        return matriculaRepository.save(matricula);
    }
    @Override
    public Matricula atualizarMatricula(UUID id, CadastrarOuEditarMatriculaDTO atualizarMatriculaDTO) {
        Matricula matricula = new Matricula();
        matricula.setId(id);
        matricula.setIdcurso(atualizarMatriculaDTO.idCurso());
        matricula.setIdEstudante(atualizarMatriculaDTO.idEstudante());
        return matriculaRepository.save(matricula);
    }
    @Override
    public void deletarMatricula(UUID idMatricula) {

        matriculaRepository.deleteById(idMatricula);
    }

    @Override
    public Optional<List<Matricula>> buscarMatriculaComIdDoCurso(UUID idCurso){
        Optional<List<Matricula>> matricula =  matriculaRepository.findByIdCurso_Id(idCurso);
        return matricula;
    }

    @Override
    public Optional<List<Matricula>> buscarMatriculaComIdDoEstudante(UUID idEstudante){
        Optional<List<Matricula>> matricula =  matriculaRepository.findByIdEstudante_Id(idEstudante);
        return matricula;
    }
}

