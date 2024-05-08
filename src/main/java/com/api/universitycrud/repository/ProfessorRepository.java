package com.api.universitycrud.repository;

import com.api.universitycrud.model.AlunoEntity;
import com.api.universitycrud.model.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long>{
    List<ProfessorEntity> findAllByAtivoTrue();
}
