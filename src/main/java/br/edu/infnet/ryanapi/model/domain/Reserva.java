package br.edu.infnet.ryanapi.model.domain;


import br.edu.infnet.ryanapi.dto.ReservaRequestDTO;
import br.edu.infnet.ryanapi.exceptions.ReservaSemProdutoException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
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

    public Reserva(LocalDate dataInicio, LocalDate dataFim, Cliente cliente, Operador operador) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
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

    public void calcularValorTotal() {
        this.valorTotal = reservaProdutos.stream()
                .map(reservaProduto -> reservaProduto.getProduto().getPreco().multiply(BigDecimal.valueOf(reservaProduto.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void alterar(ReservaRequestDTO reservaRequestDTO) {
        this.dataInicio = reservaRequestDTO.dataInicio();
        this.dataFim = reservaRequestDTO.dataFim();
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return String.format(
                "Reserva [ID: %d | Início: %s | Fim: %s | Valor Total: R$ %.2f | Cliente: %s | Operador: %s | Produtos: %d]",
                id,
                dataInicio != null ? dataInicio.format(dateFormatter) : "N/A",
                dataFim != null ? dataFim.format(dateFormatter) : "N/A",
                valorTotal != null ? valorTotal : BigDecimal.ZERO,
                cliente != null ? cliente.getNome() : "N/A",
                operador != null ? operador.getNome() : "N/A",
                reservaProdutos != null ? reservaProdutos.size() : 0
        );
    }


}
