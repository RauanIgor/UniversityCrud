package com.api.universitycrud.model;
import com.api.universitycrud.DTO.ProfessorDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="professores")
@Data
@AllArgsConstructor
public class ProfessorEntity {

    public ProfessorEntity(){
        this.ativo = true;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cpf;
    private String nome;
    private Integer matricula;
    private String genero;
    private String departamento;
    private String disciplinaAss;
    private LocalDate dataNascimento;
    private boolean ativo;

    public void update(ProfessorDTO professorDTO){
        if (professorDTO.departamento() != null){
            this.departamento = professorDTO.departamento();
        }
        if (professorDTO.disciplinaAss() != null){
            this.disciplinaAss = professorDTO.disciplinaAss();
        }
        if (professorDTO.nome() != null){
            this.nome = professorDTO.nome();
        }
        if (professorDTO.cpf() != null) {
            this.cpf = professorDTO.cpf();
        }
    }

    public void activeProfessor() { this.ativo = true; }

    public void deactivateProfessor() {
        this.ativo = false;
    }
}
