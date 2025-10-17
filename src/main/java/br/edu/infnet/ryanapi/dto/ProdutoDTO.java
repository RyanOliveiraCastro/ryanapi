package br.edu.infnet.ryanapi.dto;

import java.util.List;

public record ProdutoDTO(
        String nome,
        String categoria,
        String marca,
        List<ProdutoAtributoDTO> atributos
) {
}
