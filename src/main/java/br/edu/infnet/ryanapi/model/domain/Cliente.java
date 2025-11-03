package br.edu.infnet.ryanapi.model.domain;

import br.edu.infnet.ryanapi.dto.ClienteRequestDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
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

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return String.format(
                "Cliente [ID: %d | Nome: %s | CPF/CNPJ: %s | Telefone: %s | Email: %s | Ativo: %s | Criado em: %s | Endereço: %s]",
                getId(),
                getNome(),
                getCpfCnpj(),
                getTelefone(),
                getEmail(),
                isAtivo() ? "Sim" : "Não",
                getDataCriacao() != null ? getDataCriacao().format(dateFormatter) : "N/A",
                endereco != null ? endereco.getLogradouro() + ", " + endereco.getNumero() : "N/A"
        );
    }
}
