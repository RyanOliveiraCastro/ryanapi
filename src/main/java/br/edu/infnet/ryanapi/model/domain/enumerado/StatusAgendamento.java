package br.edu.infnet.ryanapi.model.domain.enumerado;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusAgendamento {

    AGENDADO(1, "Agendamento Realizado"),
    ENTREGUE(2, "Produto entregue ou retirado"),
    DEVOLVIDO(3, "Produto devolvido");

    Integer id;
    String descrição;
}
