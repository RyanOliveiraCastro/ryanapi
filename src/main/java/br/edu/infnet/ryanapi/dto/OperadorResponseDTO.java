package br.edu.infnet.ryanapi.dto;

import br.edu.infnet.ryanapi.model.domain.Operador;

import java.time.LocalDate;

public record OperadorResponseDTO(
        Long id,
        String nome,
        String cpfCnpj,
        String telefone,
        String email,
        String matricula,
        LocalDate dataContratacao
) {
    public static OperadorResponseDTO operadorToOperadorReponseDTO(Operador operador) {
        return new OperadorResponseDTO(operador.getId(), operador.getNome(), operador.getCpfCnpj(),
                operador.getTelefone(), operador.getEmail(), operador.getMatricula(), operador.getDataContratacao());
    }
}
