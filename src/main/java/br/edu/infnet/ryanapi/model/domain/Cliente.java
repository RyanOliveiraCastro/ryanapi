package br.edu.infnet.ryanapi.model.domain;

import br.edu.infnet.ryanapi.dto.ClienteRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "cliente")
public class Cliente extends Pessoa{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public Cliente(ClienteRequestDTO cliente, Endereco endereco) {
        this.setNome(cliente.nome());
        this.setCpfCnpj( cliente.cpfCnpj());
        this.setTelefone(cliente.telefone());
        this.setEmail(cliente.email());
        this.endereco = endereco;
        this.setAtivo(true);
        this.setDataCriacao(LocalDateTime.now());
    }

    public void alterar(ClienteRequestDTO cliente, Endereco endereco) {
        this.setNome(cliente.nome());
        this.setCpfCnpj( cliente.cpfCnpj());
        this.setTelefone(cliente.telefone());
        this.setEmail(cliente.email());
        this.endereco = endereco;
    }

    public Cliente(String nome, String cpfCnpj, String telefone, String email, LocalDate dataNascimento, Endereco endereco) {
        super(email, dataNascimento, telefone, cpfCnpj, nome);
        this.endereco = endereco;
    }
}
