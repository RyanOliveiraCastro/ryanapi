package br.edu.infnet.ryanapi.dto;

import br.edu.infnet.ryanapi.model.domain.Produto;

import java.math.BigDecimal;
import java.util.List;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String categoria,
        String marca,
        BigDecimal preco,
        List<ProdutoAtributoResponseDTO> atributos
) {
    public static ProdutoResponseDTO produtoToProdutoResponseDTO(Produto produto) {
        List<ProdutoAtributoResponseDTO> atributosResponse = produto.getAtributos().stream()
                .map(ProdutoAtributoResponseDTO::produtoAtributoToProdutoAtributoResponseDTO)
                .toList();
        return new ProdutoResponseDTO(produto.getId(), produto.getNome(),
                produto.getCategoria(), produto.getMarca(), produto.getPreco(), atributosResponse);
    }
}
