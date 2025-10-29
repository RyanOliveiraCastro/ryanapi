package br.edu.infnet.ryanapi.model.domain;

import br.edu.infnet.ryanapi.dto.EnderecoRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 8, max = 8)
    @Column(name = "cep", nullable = false)
    private String cep;

    @Size(max = 50)
    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Size(max = 30)
    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Size(max = 30)
    @Column(name = "localidade", nullable = false)
    private String localidade;

    @Size(max = 2, min = 2)
    @Column(name = "uf", nullable = false)
    private String uf;

    @Size(max = 30)
    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Size(max = 50)
    @Column(name = "complemento")
    private String complemento;

    public Endereco(EnderecoRequestDTO endereco) {
        this.logradouro = endereco.logradouro();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.bairro = endereco.bairro();
        this.localidade = endereco.localidade();
        this.uf = endereco.uf();
        this.estado = endereco.estado();
        this.cep = endereco.cep();
    }

    public void alterar(EnderecoRequestDTO endereco) {
        this.logradouro = endereco.logradouro();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.bairro = endereco.bairro();
        this.localidade = endereco.localidade();
        this.uf = endereco.uf();
        this.estado = endereco.estado();
        this.cep = endereco.cep();
    }
}

