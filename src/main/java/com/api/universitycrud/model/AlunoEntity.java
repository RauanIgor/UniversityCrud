package com.api.universitycrud.model;
import com.api.universitycrud.DTO.AlunoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="alunos")
@Data
@AllArgsConstructor
public class AlunoEntity {

    public AlunoEntity(){
        this.ativo = true;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cpf;
    private String nome;
    private Integer matricula;
    private String genero;
    private String curso;
    private LocalDate dataNascimento;
    private boolean ativo;

    public void update(AlunoDTO alunoDTO){
        if (alunoDTO.curso() != null){
            this.curso = alunoDTO.curso();
        }
        if (alunoDTO.nome() != null){
            this.nome = alunoDTO.nome();
        }
        if (alunoDTO.cpf() != null) {
            this.cpf = alunoDTO.cpf();
        }
    }

    public void activeAluno() { this.ativo = true; }

    public void deactivateAluno() {
        this.ativo = false;
    }
}
