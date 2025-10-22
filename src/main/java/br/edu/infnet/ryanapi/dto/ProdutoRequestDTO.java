package br.edu.infnet.ryanapi.dto;

import java.util.List;

public record ProdutoRequestDTO(
        String nome,
        String categoria,
        String marca,
        List<ProdutoAtributoRequestDTO> atributos
) {
}
