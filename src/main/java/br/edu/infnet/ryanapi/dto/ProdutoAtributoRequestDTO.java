package br.edu.infnet.ryanapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProdutoAtributoRequestDTO(
        @NotNull
        @Size(max = 30)
        String nome,
        @NotNull
        @Size(max = 30)
        String valor
) {
}
