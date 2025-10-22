package br.edu.infnet.ryanapi.dto;

public record EnderecoRequestDTO(
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep) {
}
