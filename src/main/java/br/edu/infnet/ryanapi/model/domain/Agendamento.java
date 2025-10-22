package br.edu.infnet.ryanapi.model.domain;

import br.edu.infnet.ryanapi.model.domain.enumerado.TipoDevolucao;
import br.edu.infnet.ryanapi.model.domain.enumerado.TipoEntrega;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Entity(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;
    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;
    @Column(name = "hora_devolucao")
    private LocalTime horaDevolucao;
    @Column(name = "tipo_entrega", nullable = false)
    private TipoEntrega tipoEntrega;
    @Column(name = "tipo_devolucao", nullable = false)
    private TipoDevolucao tipoDevolucao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "agendamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AgendamentoProduto> produtos = new ArrayList<>();

}
