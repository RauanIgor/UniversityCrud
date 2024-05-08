package com.api.universitycrud.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record ProfessorDTO(
        @NotBlank(message = "Campo não pode ser vazio!")
        String nome,
        @NotBlank(message = "Campo não pode ser vazio!")
        @CPF (message = "CPF inválido")
        String cpf,
        @DateTimeFormat
        LocalDate dataNascimento,
        @NotBlank(message = "Campo não pode ser vazio!")
        String genero,
        @NotNull
        Integer matricula,
        @NotBlank(message = "Campo não pode ser vazio!")
        String departamento,
        @NotBlank(message = "Campo não pode ser vazio!")
        String disciplinaAss
) { }
