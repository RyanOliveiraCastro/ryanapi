package br.edu.infnet.ryanapi.dto;

public record ClienteDTO(
        String nome,
        String cpfCnpj,
        String telefone,
        String email
) {
}
