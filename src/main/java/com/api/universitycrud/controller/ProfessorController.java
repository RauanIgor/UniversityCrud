package com.api.universitycrud.controller;

import com.api.universitycrud.DTO.ProfessorDTO;
import com.api.universitycrud.model.ProfessorEntity;

import com.api.universitycrud.repository.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("professores")
public class ProfessorController {


    @Autowired
    ProfessorRepository repository;

    @PostMapping
    public ResponseEntity<ProfessorEntity> create(@Valid @RequestBody ProfessorDTO professorDTO){
        ProfessorEntity professorEntity = new ProfessorEntity();
        BeanUtils.copyProperties(professorDTO, professorEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(professorEntity));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorEntity>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByAtivoTrue());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") Long id){
        Optional<ProfessorEntity> professor = repository.findById(id);
        if(professor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(professor);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody ProfessorDTO professorDTO){
        Optional<ProfessorEntity> professor = repository.findById(id);
        if(professor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("professor not found");
        }

        ProfessorEntity al = professor.get();

        al.update(professorDTO);

        return ResponseEntity.status(HttpStatus.OK).body(repository.save(al));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteOne(@PathVariable(value = "id") Long id){
        Optional<ProfessorEntity> professor = repository.findById(id);

        if(professor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor not found");
        }
        ProfessorEntity al = professor.get();
        repository.delete(al);


        return ResponseEntity.status(HttpStatus.OK).body("Professor deleted sucessifully!");
    }

    @DeleteMapping("deactive/{id}")
    public ResponseEntity<Object> deleteLogic(@PathVariable(value = "id") Long id){
        Optional<ProfessorEntity> professor = repository.findById(id);

        if(professor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("professor not found");
        }
        ProfessorEntity al = professor.get();

        al.deactivateProfessor();

        repository.save(al);
        return ResponseEntity.status(HttpStatus.OK).body("Professor deleted sucessifully!");
    }


    @PatchMapping("{id}")
    public ResponseEntity<Object> patchOne(@PathVariable(value = "id") Long id,
                                           @RequestBody ProfessorDTO professorDTO){
        Optional<ProfessorEntity> professor = repository.findById(id);
        if(professor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor not found");
        }

        ProfessorEntity al = professor.get();

        BeanUtils.copyProperties(professorDTO, al);

        return ResponseEntity.status(HttpStatus.OK).body(repository.save(al));
    }

}
