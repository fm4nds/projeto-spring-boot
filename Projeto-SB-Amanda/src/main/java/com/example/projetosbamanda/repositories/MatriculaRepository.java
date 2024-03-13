package com.example.projetosbamanda.repositories;

import com.example.projetosbamanda.models.Curso;
import com.example.projetosbamanda.models.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, UUID> {

    @Query("SELECT m FROM Matricula m WHERE m.idCurso.id = :cursoId")
   Optional<List<Matricula>> findByIdCurso_Id(UUID cursoId);

    @Query("SELECT m FROM Matricula m WHERE m.idEstudante.id = :estudanteId")
    Optional<List<Matricula>> findByIdEstudante_Id(UUID estudanteId);


}
