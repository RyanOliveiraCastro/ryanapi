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

    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalTime horaDevolucao;
    private TipoEntrega tipoEntrega;
    private TipoDevolucao tipoDevolucao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "agendamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AgendamentoProduto> produtos = new ArrayList<>();

}
