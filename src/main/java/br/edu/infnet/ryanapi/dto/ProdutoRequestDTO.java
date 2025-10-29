package br.edu.infnet.ryanapi.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record ProdutoRequestDTO(
        @NotNull
        @Size(max = 30)
        String nome,
        @NotNull
        @Size(max = 20)
        String categoria,
        @NotNull
        @Size(max = 30)
        String marca,
        @NotNull
        @Digits(integer = 5, fraction = 2)
        BigDecimal preco,
        @NotNull
        List<ProdutoAtributoRequestDTO> atributos
) {
}
