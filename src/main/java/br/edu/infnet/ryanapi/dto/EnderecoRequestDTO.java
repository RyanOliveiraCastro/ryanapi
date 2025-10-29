package br.edu.infnet.ryanapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EnderecoRequestDTO(
        @NotNull
        @Size(max = 50)
        String logradouro,
        @NotNull
        String numero,
        @NotNull
        @Size(max = 50)
        String complemento,
        @NotNull
        @Size(max = 30)
        String bairro,
        @NotNull
        @Size(max = 30)
        String localidade,
        @NotNull
        @Size(max = 2, min = 2)
        String uf,
        @NotNull
        @Size(max = 30)
        String estado,
        @NotNull
        @Size(min = 8, max = 8)
        String cep) {
}
