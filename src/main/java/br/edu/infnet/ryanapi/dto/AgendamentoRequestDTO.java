package br.edu.infnet.ryanapi.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record AgendamentoRequestDTO(
        @NotNull
        @Future
        LocalDate dataInicio,
        @NotNull
        @Future
        LocalDate dataFim,
        @NotNull
        @PastOrPresent
        LocalDateTime dataHoraDevolucao,
        @NotNull
        Integer tipoEntrega,
        @NotNull
        Integer tipoDevolucao,
        @NotNull
        Long codigoCliente,
        @NotNull
        List<AgendamentoProdutoRequestDTO> agendamentoProdutos
) {
}
