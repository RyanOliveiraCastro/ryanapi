package br.edu.infnet.ryanapi.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@RequiredArgsConstructor
@Embeddable
public class AgendamentoProdutoPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "agendamento_id")
    private Long agendamentoId;

    @Column(name = "produto_id")
    private Long produtoId;

    public AgendamentoProdutoPK(Long agendamentoId, Long produtoId) {
        this.agendamentoId = agendamentoId;
        this.produtoId = produtoId;
    }

    @Override
    public String toString() {
        return String.format(
                "AgendamentoProdutoPK [AgendamentoID: %d | ProdutoID: %d]",
                agendamentoId, produtoId
        );
    }
}