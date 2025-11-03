package br.edu.infnet.ryanapi.dto;

import br.edu.infnet.ryanapi.model.domain.Reserva;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ReservaResponseDTO(
        Long id,
        LocalDate dataInicio,
        LocalDate dataFim,
        BigDecimal valorTotal,
        ClienteResponseDTO cliente,
        OperadorResponseDTO operador,
        List<ReservaProdutoResponseDTO> produtos
) {

    public static ReservaResponseDTO reservaToReservaReponseDTO(Reserva reserva) {
        ClienteResponseDTO clienteResponseDTO = ClienteResponseDTO.clienteToClienteReponseDTO(reserva.getCliente());
        OperadorResponseDTO operadorResponseDTO = OperadorResponseDTO.operadorToOperadorReponseDTO(reserva.getOperador());
        List<ReservaProdutoResponseDTO> agendamentoProdutoResponseDTOS
                = ReservaProdutoResponseDTO.reservaProdutosToAgendamentoProdutoResponseDTO(reserva.getReservaProdutos());
        return new ReservaResponseDTO(reserva.getId(), reserva.getDataInicio(), reserva.getDataFim(),
                reserva.getValorTotal(), clienteResponseDTO, operadorResponseDTO, agendamentoProdutoResponseDTOS);
    }

}
