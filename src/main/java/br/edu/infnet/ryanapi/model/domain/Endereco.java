package br.edu.infnet.ryanapi.model.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Embeddable
public class Endereco {

    private String rua;
    private String numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;

}

