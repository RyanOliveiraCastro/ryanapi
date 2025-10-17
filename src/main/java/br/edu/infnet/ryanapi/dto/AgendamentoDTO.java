package br.edu.infnet.ryanapi.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record AgendamentoDTO(
         LocalDate dataInicio,
         LocalDate dataFim,
         LocalTime horaDevolucao,
         Integer tipoEntrega,
         Integer tipoDevolucao,
         ClienteDTO cliente,
         List<ProdutoDTO> produtos
) {
}
