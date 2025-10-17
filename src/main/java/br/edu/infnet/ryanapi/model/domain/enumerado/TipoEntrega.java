package br.edu.infnet.ryanapi.model.domain.enumerado;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum TipoEntrega {
    RETIRADA(1, "RETIRADA", BigDecimal.ZERO),
    ENTREGA_NORMAL(2, "ENTREGA NORMAL", BigDecimal.TEN),
    ENTREGA_EXPRESS(3, "ENTREGA EXPRESS", BigDecimal.valueOf(25));

    Integer id;
    String descrição;
    BigDecimal valor;
}
