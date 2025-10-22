package br.edu.infnet.ryanapi.dto;

import br.edu.infnet.ryanapi.model.domain.Endereco;

public record EnderecoResponseDTO(
        Long id,
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep) {


    public static EnderecoResponseDTO enderecoToEnderecoReponseDTO(Endereco endereco) {
        return new EnderecoResponseDTO(endereco.getId(), endereco.getRua(), endereco.getNumero(), endereco.getComplemento(),
                endereco.getBairro(), endereco.getCidade(), endereco.getEstado(), endereco.getCep());
    }
}
