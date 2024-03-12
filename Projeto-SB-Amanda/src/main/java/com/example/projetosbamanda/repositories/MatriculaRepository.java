package com.example.projetosbamanda.repositories;

import com.example.projetosbamanda.models.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, UUID> {
}
