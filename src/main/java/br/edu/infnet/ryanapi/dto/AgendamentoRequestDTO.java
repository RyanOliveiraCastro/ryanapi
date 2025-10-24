package br.edu.infnet.ryanapi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record AgendamentoRequestDTO(
         LocalDate dataInicio,
         LocalDate dataFim,
         LocalDateTime dataHoraDevolucao,
         Integer tipoEntrega,
         Integer tipoDevolucao,
         Long codigoCliente,
         List<AgendamentoProdutoRequestDTO> agendamentoProdutos
) {
}
