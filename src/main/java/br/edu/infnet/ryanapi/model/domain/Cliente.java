package br.edu.infnet.ryanapi.model.domain;

import br.edu.infnet.ryanapi.dto.ClienteRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Entity(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "cpfCnpj", nullable = false)
    private String cpfCnpj;
    @Column(name = "telefone", nullable = false)
    private String telefone;
    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public Cliente(ClienteRequestDTO cliente, Endereco endereco) {
        this.nome = cliente.nome();
        this.cpfCnpj = cliente.cpfCnpj();
        this.telefone = cliente.telefone();
        this.email = cliente.email();
        this.endereco = endereco;
    }

    public void alterar(ClienteRequestDTO cliente, Endereco endereco) {
        this.nome = cliente.nome();
        this.cpfCnpj = cliente.cpfCnpj();
        this.telefone = cliente.telefone();
        this.email = cliente.email();
        this.endereco = endereco;
    }

}
