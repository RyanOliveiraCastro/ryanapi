package br.edu.infnet.ryanapi.model.domain;


import br.edu.infnet.ryanapi.dto.AgendamentoRequestDTO;
import br.edu.infnet.ryanapi.dto.ReservaRequestDTO;
import br.edu.infnet.ryanapi.exceptions.AgendamentoSemProdutoException;
import br.edu.infnet.ryanapi.exceptions.ReservaSemProdutoException;
import br.edu.infnet.ryanapi.model.domain.enumerado.StatusAgendamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Future
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Future
    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Digits(integer = 5, fraction = 2)
    @Column(name = "valor_total", nullable = false, precision = 5, scale = 2)
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "operador_id")
    private Operador operador;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservaProduto> reservaProdutos = new ArrayList<>();

    public Reserva(ReservaRequestDTO reservaRequestDTO, Cliente cliente, Operador operador) {
        this.dataInicio = reservaRequestDTO.dataInicio();
        this.dataFim = reservaRequestDTO.dataFim();
        this.cliente = cliente;
        this.operador = operador;
    }

    public void adicionarReservaProdutos(List<ReservaProduto> reservaProdutos) {
        if (reservaProdutos.isEmpty()) {
            throw new ReservaSemProdutoException("Reserva deve ter ao menos um produto");
        }
        reservaProdutos.forEach(reservaProduto -> reservaProduto.adicionarReserva(this));
        this.reservaProdutos.clear();
        this.reservaProdutos.addAll(reservaProdutos);
    }

}
