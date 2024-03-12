package com.example.projetosbamanda.services;

import com.example.projetosbamanda.dtos.CadastrarOuEditarEstudanteDTO;
import com.example.projetosbamanda.models.Estudante;
import com.example.projetosbamanda.repositories.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EstudanteService implements com.example.projetosbamanda.services.interfaces.EstudanteInterface {
    private EstudanteRepository estudanteRepository;
    @Autowired
    public EstudanteService(EstudanteRepository estudanteRepository) {
        this.estudanteRepository = estudanteRepository;
    }
    @Override
    public List<Estudante> listaDeEstudantes() {
        return estudanteRepository.findAll();
    }
    @Override
    public Optional<Estudante> buscarEstudantePorId(UUID id) {
        return estudanteRepository.findById(id);
    }
    @Override
    public Estudante cadastrarEstudante(CadastrarOuEditarEstudanteDTO cadastrarEstudanteDTO) {
        Estudante estudante = new Estudante();
        estudante.setNome(cadastrarEstudanteDTO.nome());
        estudante.setDataMatricula(cadastrarEstudanteDTO.dataMatricula());
        return estudanteRepository.save(estudante);
    }
    @Override
    public Estudante atualizarEstudante(UUID id, CadastrarOuEditarEstudanteDTO atualizarEstudanteDTO) {
        Estudante estudante = new Estudante();
        estudante.setId(id);
        estudante.setNome(atualizarEstudanteDTO.nome());
        estudante.setDataMatricula(atualizarEstudanteDTO.dataMatricula());
        return estudanteRepository.save(estudante);
    }
    @Override
    public void deletarEstudante(Estudante estudante) {
        estudanteRepository.delete(estudante);
    }
}
