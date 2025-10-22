package br.edu.infnet.ryanapi.dto;

public record ClienteRequestDTO(
        String nome,
        String cpfCnpj,
        String telefone,
        String email,
        EnderecoRequestDTO endereco
) {
}
