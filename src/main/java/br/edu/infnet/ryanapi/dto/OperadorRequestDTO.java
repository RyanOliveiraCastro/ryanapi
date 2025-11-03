package br.edu.infnet.ryanapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record OperadorRequestDTO(
        @NotNull
        @Size(min = 3, max = 100)
        String nome,
        @NotNull
        @Size(min = 11, max = 14)
        String cpfCnpj,
        @NotNull
        @Size(min = 10, max = 11)
        String telefone,
        @NotNull
        @Email
        String email,
        @NotNull
        LocalDate dataNascimento,
        @NotNull
        String matricula,
        @NotNull
        LocalDate dataContratacao,
        @NotNull
        EnderecoRequestDTO endereco
) {
}
