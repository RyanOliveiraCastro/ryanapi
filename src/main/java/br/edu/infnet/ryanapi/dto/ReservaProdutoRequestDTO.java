package br.edu.infnet.ryanapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ReservaProdutoRequestDTO(
        @NotNull
        Long codigoProduto,

        @NotNull
        @Min(1)
        Integer quantidade
) {
}
