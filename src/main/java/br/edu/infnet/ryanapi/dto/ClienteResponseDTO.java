package br.edu.infnet.ryanapi.dto;

import br.edu.infnet.ryanapi.model.domain.Cliente;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String cpfCnpj,
        String telefone,
        String email,
        EnderecoResponseDTO endereco
) {
    public static ClienteResponseDTO clienteToClienteReponseDTO(Cliente cliente) {
        EnderecoResponseDTO endereco = EnderecoResponseDTO.enderecoToEnderecoReponseDTO(cliente.getEndereco());
        return new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getCpfCnpj(),
                cliente.getTelefone(), cliente.getEmail(), endereco);
    }
}
