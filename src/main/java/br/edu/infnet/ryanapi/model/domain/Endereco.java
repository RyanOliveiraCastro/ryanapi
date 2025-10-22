package br.edu.infnet.ryanapi.model.domain;

import br.edu.infnet.ryanapi.dto.EnderecoRequestDTO;
import br.edu.infnet.ryanapi.dto.EnderecoResponseDTO;
import jakarta.persistence.*;
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
    @Column(name = "rua", nullable = false)
    private String rua;
    @Column(name = "numero", nullable = false)
    private String numero;
    @Column(name = "complemento")
    private String complemento;
    @Column(name = "bairro", nullable = false)
    private String bairro;
    @Column(name = "cidade", nullable = false)
    private String cidade;
    @Column(name = "estado", nullable = false)
    private String estado;
    @Column(name = "cep", nullable = false)
    private String cep;

    public Endereco(EnderecoRequestDTO endereco) {
        this.rua = endereco.rua();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.estado = endereco.estado();
        this.cep = endereco.cep();
    }

    public void alterar(EnderecoRequestDTO endereco){
        this.rua = endereco.rua();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.estado = endereco.estado();
        this.cep = endereco.cep();
    }
}

