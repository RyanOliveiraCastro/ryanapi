package br.edu.infnet.ryanapi.model.domain;

import br.edu.infnet.ryanapi.dto.AgendamentoRequestDTO;
import br.edu.infnet.ryanapi.exceptions.AgendamentoSemProdutoException;
import br.edu.infnet.ryanapi.model.domain.enumerado.StatusAgendamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Future
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Future
    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @PastOrPresent
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

    @ManyToOne
    @JoinColumn(name = "operador_id")
    private Operador operador;

    @OneToMany(mappedBy = "agendamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AgendamentoProduto> produtos = new ArrayList<>();

    public Agendamento(AgendamentoRequestDTO agendamentoRequestDTO, Cliente cliente, Operador operador) {
        this.dataInicio = agendamentoRequestDTO.dataInicio();
        this.dataFim = agendamentoRequestDTO.dataFim();
        this.tipoEntrega = agendamentoRequestDTO.tipoEntrega();
        this.tipoDevolucao = agendamentoRequestDTO.tipoDevolucao();
        this.cliente = cliente;
        this.operador = operador;
        this.status = StatusAgendamento.AGENDADO.getId();
    }

    public Agendamento(LocalDate dataInicio, LocalDate dataFim, LocalDateTime dataHoraDevolucao,
                       Integer tipoEntrega, Integer tipoDevolucao, Integer status, Cliente cliente, Operador operador) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataHoraDevolucao = dataHoraDevolucao;
        this.tipoEntrega = tipoEntrega;
        this.tipoDevolucao = tipoDevolucao;
        this.status = status;
        this.cliente = cliente;
        this.operador = operador;
    }

    public void adicionarAgendamentoProdutos(List<AgendamentoProduto> agendamentoProdutos) {
        if (agendamentoProdutos.isEmpty()) {
            throw new AgendamentoSemProdutoException("Agendamento deve ter ao menos um produto");
        }
        agendamentoProdutos.forEach(agendamentoProduto -> agendamentoProduto.adicionarAgendamento(this));
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

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return String.format(
                "Agendamento [ID: %d | Início: %s | Fim: %s | Devolução: %s | TipoEntrega: %d | TipoDevolução: %d | Status: %d | Cliente: %s | Operador: %s | Produtos: %d]",
                id,
                dataInicio != null ? dataInicio.format(dateFormatter) : "N/A",
                dataFim != null ? dataFim.format(dateFormatter) : "N/A",
                dataHoraDevolucao != null ? dataHoraDevolucao.format(dateTimeFormatter) : "N/A",
                tipoEntrega,
                tipoDevolucao,
                status,
                cliente != null ? cliente.getNome() : "N/A",
                operador != null ? operador.getNome() : "N/A",
                produtos != null ? produtos.size() : 0
        );
    }

}
