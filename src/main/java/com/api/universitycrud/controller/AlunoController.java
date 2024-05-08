package com.api.universitycrud.controller;

import com.api.universitycrud.DTO.AlunoDTO;
import com.api.universitycrud.model.AlunoEntity;

import com.api.universitycrud.repository.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("alunos")
public class AlunoController {


    @Autowired
    AlunoRepository repository;

    @PostMapping
    public ResponseEntity<AlunoEntity> create(@Valid @RequestBody AlunoDTO alunoDTO){
        AlunoEntity alunoEntity = new AlunoEntity();
        BeanUtils.copyProperties(alunoDTO, alunoEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(alunoEntity));
    }

    @GetMapping("/university")
    public String helloUniversity(){
        return "Hello university";
    }

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByAtivoTrue());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") Long id){
        Optional<AlunoEntity> aluno = repository.findById(id);
        if(aluno.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(aluno);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody AlunoDTO alunoDTO){
        Optional<AlunoEntity> aluno = repository.findById(id);
        if(aluno.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno not found");
        }

        AlunoEntity al = aluno.get();

        al.update(alunoDTO);

        return ResponseEntity.status(HttpStatus.OK).body(repository.save(al));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteOne(@PathVariable(value = "id") Long id){
        Optional<AlunoEntity> aluno = repository.findById(id);

        if(aluno.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno not found");
        }
        AlunoEntity al = aluno.get();
        repository.delete(al);


        return ResponseEntity.status(HttpStatus.OK).body("Aluno deleted sucessifully!");
    }

    @DeleteMapping("deactive/{id}")
    public ResponseEntity<Object> deleteLogic(@PathVariable(value = "id") Long id){
        Optional<AlunoEntity> aluno = repository.findById(id);

        if(aluno.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno not found");
        }
        AlunoEntity al = aluno.get();

        al.deactivateAluno();

        repository.save(al);
        return ResponseEntity.status(HttpStatus.OK).body("aluno deleted sucessifully!");
    }


    @PatchMapping("{id}")
    public ResponseEntity<Object> patchOne(@PathVariable(value = "id") Long id,
                                           @RequestBody AlunoDTO alunoDTO){
        Optional<AlunoEntity> aluno = repository.findById(id);
        if(aluno.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno not found");
        }

        AlunoEntity al = aluno.get();

        BeanUtils.copyProperties(alunoDTO, al);

        return ResponseEntity.status(HttpStatus.OK).body(repository.save(al));
    }

}
