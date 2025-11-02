package br.edu.infnet.ryanapi.model.domain;


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
    @Column(name = "valor_otal", nullable = false, precision = 5, scale =  2)
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservaProduto> reservaProdutos = new ArrayList<>();

}
