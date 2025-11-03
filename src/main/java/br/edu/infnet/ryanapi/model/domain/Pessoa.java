package br.edu.infnet.ryanapi.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Pessoa {

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

    @Past
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @PastOrPresent
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    public Pessoa(String email, LocalDate dataNascimento, String telefone, String cpfCnpj, String nome) {
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.cpfCnpj = cpfCnpj;
        this.nome = nome;
        this.ativo = true;
        this.dataCriacao = LocalDateTime.now();
    }
}
