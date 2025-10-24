package br.edu.infnet.ryanapi.model.domain;

import br.edu.infnet.ryanapi.dto.AgendamentoRequestDTO;
import br.edu.infnet.ryanapi.exceptions.AgendamentoNaoEncontradoException;
import br.edu.infnet.ryanapi.exceptions.AgendamentoSemProdutoException;
import br.edu.infnet.ryanapi.model.domain.enumerado.StatusAgendamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;
    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;
    @Column(name = "data_hora_devolucao")
    private LocalDateTime dataHoraDevolucao;
    @Column(name = "tipo_entrega", nullable = false)
    private Integer tipoEntrega;
    @Column(name = "tipo_devolucao", nullable = false)
    private Integer tipoDevolucao;
    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "agendamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AgendamentoProduto> produtos = new ArrayList<>();

    public Agendamento(AgendamentoRequestDTO agendamentoRequestDTO, Cliente cliente) {
        this.dataInicio = agendamentoRequestDTO.dataInicio();
        this.dataFim = agendamentoRequestDTO.dataFim();
        this.tipoEntrega = agendamentoRequestDTO.tipoEntrega();
        this.tipoDevolucao = agendamentoRequestDTO.tipoDevolucao();
        this.cliente = cliente;
        this.status = StatusAgendamento.AGENDADO.getId();
    }

    public void adicionarAgendamentoProdutos(List<AgendamentoProduto> agendamentoProdutos) {
        if (agendamentoProdutos.isEmpty()) {
            throw new AgendamentoSemProdutoException("Agendamento deve ter ao menos um produto");
        }
        agendamentoProdutos.forEach(produto -> produto.adicionarAgendamento(this));
        this.produtos.clear();
        this.produtos.addAll(agendamentoProdutos);
    }

    public void alterar(AgendamentoRequestDTO agendamentoRequestDTO) {
        this.dataInicio = agendamentoRequestDTO.dataInicio();
        this.dataFim = agendamentoRequestDTO.dataFim();
        this.tipoEntrega = agendamentoRequestDTO.tipoEntrega();
        this.tipoDevolucao = agendamentoRequestDTO.tipoDevolucao();
    }

    public void alterarProdutos(List<AgendamentoProduto> agendamentoProdutos) {
        this.produtos.clear();
        this.produtos.addAll(agendamentoProdutos);
    }

    public void alterarStatus(Integer statusAgendamento) {
        this.status = statusAgendamento;
    }

    public void finalizarAgendamento(LocalDateTime dataHoraDevolucao) {
        this.status = StatusAgendamento.DEVOLVIDO.getId();
        this.dataHoraDevolucao = dataHoraDevolucao;
    }

}
