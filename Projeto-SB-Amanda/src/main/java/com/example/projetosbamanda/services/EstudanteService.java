package com.example.projetosbamanda.services;

import com.example.projetosbamanda.dtos.estudante.CadastrarOuEditarEstudanteDTO;
import com.example.projetosbamanda.models.Estudante;
import com.example.projetosbamanda.repositories.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
        LocalDateTime dataAtual = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        estudante.setDataMatricula(dataAtual);
        return estudanteRepository.save(estudante);
    }
    @Override
    public Estudante atualizarEstudante(UUID id, CadastrarOuEditarEstudanteDTO atualizarEstudante) {
        Optional<Estudante> estudanteData = buscarEstudantePorId(id);
        if(estudanteData.isPresent()) {
            Estudante estudante = new Estudante();
            estudante.setId(id);
            estudante.setDataMatricula(estudanteData.get().getDataMatricula());
            estudante.setNome(atualizarEstudante.nome());
            return estudanteRepository.save(estudante);
        }
        throw  new RuntimeException("O estudante não foi encontrado");
    }
    @Override
    public void deletarEstudante(UUID id) {
        estudanteRepository.deleteById(id);
    }
}
