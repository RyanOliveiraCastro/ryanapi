package br.edu.infnet.ryanapi.model.domain;

import br.edu.infnet.ryanapi.exceptions.QuantidadeZeroNegativoException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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

    @Min(1)
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    public AgendamentoProduto(Agendamento agendamento, Produto produto, int quantidade) {
        this.agendamento = agendamento;
        this.produto = produto;
        if (quantidade <= 0) {
            throw new QuantidadeZeroNegativoException("A quantidade não pode ser zero ou negativo");
        }
        this.quantidade = quantidade;
    }

    public void adicionarAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
        if (agendamento != null) {
            this.id.setAgendamentoId(this.agendamento.getId());
            this.id.setProdutoId(this.produto.getId());
        }
    }
}
