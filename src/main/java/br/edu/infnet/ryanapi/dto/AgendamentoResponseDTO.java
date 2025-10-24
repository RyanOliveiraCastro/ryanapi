package br.edu.infnet.ryanapi.dto;

import br.edu.infnet.ryanapi.model.domain.Agendamento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record AgendamentoResponseDTO(
        Long id,
        LocalDate dataInicio,
        LocalDate dataFim,
        LocalDateTime dataHoraDevolucao,
        Integer tipoEntrega,
        Integer tipoDevolucao,
        Integer status,
        ClienteResponseDTO cliente,
        List<AgendamentoProdutoResponseDTO> produtos
) {

    public static AgendamentoResponseDTO agendamentoToAgendamentoReponseDTO(Agendamento agendamento) {
        ClienteResponseDTO clienteResponseDTO = ClienteResponseDTO.clienteToClienteReponseDTO(agendamento.getCliente());
        List<AgendamentoProdutoResponseDTO> agendamentoProdutoResponseDTOS
                = AgendamentoProdutoResponseDTO.agendamentoProdutosToAgendamentoProdutoResponseDTO(agendamento.getProdutos());
        return new AgendamentoResponseDTO(agendamento.getId(), agendamento.getDataInicio(), agendamento.getDataFim(),
                agendamento.getDataHoraDevolucao(), agendamento.getTipoEntrega(), agendamento.getTipoDevolucao(), agendamento.getStatus(),
                clienteResponseDTO, agendamentoProdutoResponseDTOS);
    }

}
