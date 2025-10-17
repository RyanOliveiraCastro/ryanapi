package br.edu.infnet.ryanapi.model.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity(name = "agendamento_produto")
public class AgendamentoProduto {

    @EmbeddedId
    private AgendamentoProdutoPK id = new AgendamentoProdutoPK();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("agendamentoId")
    private Agendamento agendamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("produtoId")
    private Produto produto;

    private int quantidade;


    public AgendamentoProduto(Agendamento agendamento, Produto produto, int quantidade) {
        this.agendamento = agendamento;
        this.produto = produto;
        this.quantidade = quantidade;
        this.id = new AgendamentoProdutoPK(agendamento.getId(), produto.getId());
    }
}
