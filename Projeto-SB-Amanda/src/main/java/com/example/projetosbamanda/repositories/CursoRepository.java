package com.example.projetosbamanda.repositories;

import com.example.projetosbamanda.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CursoRepository extends JpaRepository<Curso, UUID> {

}
