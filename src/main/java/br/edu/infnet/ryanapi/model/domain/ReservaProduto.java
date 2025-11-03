package br.edu.infnet.ryanapi.model.domain;

import br.edu.infnet.ryanapi.dto.ProdutoAtributoRequestDTO;
import br.edu.infnet.ryanapi.exceptions.AgendamentoSemProdutoException;
import br.edu.infnet.ryanapi.exceptions.QuantidadeZeroNegativoException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reserva_produto")
public class ReservaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reserva reserva;

    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    public ReservaProduto(Reserva reserva, Produto produto, int quantidade) {
        this.reserva = reserva;
        this.produto = produto;
        if (quantidade <= 0) {
            throw new QuantidadeZeroNegativoException("A quantidade não pode ser zero ou negativo");
        }
        this.quantidade = quantidade;
    }

    public void adicionarReserva(Reserva reserva) {
        this.reserva = reserva;
    }

}
