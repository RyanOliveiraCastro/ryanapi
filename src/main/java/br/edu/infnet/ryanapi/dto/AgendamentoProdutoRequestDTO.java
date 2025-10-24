package br.edu.infnet.ryanapi.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record AgendamentoProdutoRequestDTO(
         Long codigoProduto,
         Integer quantidade
) {
}
