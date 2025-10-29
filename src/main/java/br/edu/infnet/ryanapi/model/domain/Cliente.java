package br.edu.infnet.ryanapi.model.domain;

import br.edu.infnet.ryanapi.dto.ClienteRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 100)
    @Column(name = "nome", nullable = false)
    private String nome;

    @Size(min = 11, max = 14)
    @Column(name = "cpfCnpj", nullable = false, length = 14)
    private String cpfCnpj;

    @Size(min = 10, max = 11)
    @Column(name = "telefone", nullable = false, length = 11)
    private String telefone;

    @Email
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
