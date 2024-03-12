package com.example.projetosbamanda.repositories;

import com.example.projetosbamanda.models.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, UUID> {

}