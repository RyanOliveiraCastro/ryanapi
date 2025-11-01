package br.edu.infnet.ryanapi.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "operador")
public class Operador extends Pessoa{

    @Size(min = 11, max = 14)
    @Column(name = "matricula", nullable = false, length = 14)
    private String matricula;
}
