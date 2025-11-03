package br.edu.infnet.ryanapi.model.domain;

import br.edu.infnet.ryanapi.exceptions.QuantidadeZeroNegativoException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
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

    @Override
    public String toString() {
        return String.format(
                "AgendamentoProduto [AgendamentoID: %s | Produto: %s | Quantidade: %d]",
                agendamento != null ? agendamento.getId() : "N/A",
                produto != null ? produto.getNome() : "N/A",
                quantidade
        );
    }
}
