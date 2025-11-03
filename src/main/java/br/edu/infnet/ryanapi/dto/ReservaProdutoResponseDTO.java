package br.edu.infnet.ryanapi.dto;

import br.edu.infnet.ryanapi.model.domain.ReservaProduto;

import java.util.List;

public record ReservaProdutoResponseDTO(
        ProdutoResponseDTO produto,
        Integer quantidade
) {

    public static List<ReservaProdutoResponseDTO> reservaProdutosToAgendamentoProdutoResponseDTO(List<ReservaProduto> reservaProdutos) {

        return reservaProdutos.stream()
                .map(reservaProduto -> {
                    ProdutoResponseDTO produtoResponseDTO = ProdutoResponseDTO.produtoToProdutoResponseDTO(reservaProduto.getProduto());
                    return new ReservaProdutoResponseDTO(produtoResponseDTO, reservaProduto.getQuantidade());
                })
                .toList();
    }
}
