package br.edu.infnet.ryanapi.dto;

import br.edu.infnet.ryanapi.model.domain.AgendamentoProduto;

import java.util.List;

public record AgendamentoProdutoResponseDTO(
        ProdutoResponseDTO produto,
        Integer quantidade
) {

    public static List<AgendamentoProdutoResponseDTO> agendamentoProdutosToAgendamentoProdutoResponseDTO(List<AgendamentoProduto> agendamentoProdutos) {

        return agendamentoProdutos.stream()
                .map(agendamentoProduto -> {
                    ProdutoResponseDTO produtoResponseDTO = ProdutoResponseDTO.produtoToProdutoResponseDTO(agendamentoProduto.getProduto());
                    return new AgendamentoProdutoResponseDTO(produtoResponseDTO, agendamentoProduto.getQuantidade());
                })
                .toList();
    }
}
