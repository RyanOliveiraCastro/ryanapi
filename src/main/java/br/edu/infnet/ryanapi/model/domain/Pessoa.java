package br.edu.infnet.ryanapi.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Email
    @Column(name = "email", nullable = false)
    private String email;

}
