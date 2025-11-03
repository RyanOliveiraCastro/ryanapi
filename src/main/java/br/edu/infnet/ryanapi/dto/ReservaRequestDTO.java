package br.edu.infnet.ryanapi.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record ReservaRequestDTO(
        @NotNull
        @Future
        LocalDate dataInicio,
        @NotNull
        @Future
        LocalDate dataFim,
        @NotNull
        Long codigoCliente,
        @NotNull
        Long codigoOperador,
        @NotNull
        List<ReservaProdutoRequestDTO> reservaProdutos
) {
}
