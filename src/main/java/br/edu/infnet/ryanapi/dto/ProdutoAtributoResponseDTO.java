package br.edu.infnet.ryanapi.dto;

import br.edu.infnet.ryanapi.model.domain.ProdutoAtributo;

public record ProdutoAtributoResponseDTO(
        Long id,
        String nome,
        String valor
) {
    public static ProdutoAtributoResponseDTO produtoAtributoToProdutoAtributoResponseDTO(ProdutoAtributo produtoAtributo) {
        return new ProdutoAtributoResponseDTO(produtoAtributo.getId(), produtoAtributo.getNome(), produtoAtributo.getValor());
    }
}
