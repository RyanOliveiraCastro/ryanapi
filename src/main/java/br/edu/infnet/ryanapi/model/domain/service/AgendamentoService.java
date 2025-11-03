package br.edu.infnet.ryanapi.model.domain.service;


import br.edu.infnet.ryanapi.dto.AgendamentoProdutoRequestDTO;
import br.edu.infnet.ryanapi.dto.AgendamentoRequestDTO;
import br.edu.infnet.ryanapi.exceptions.AgendamentoNaoEncontradoException;
import br.edu.infnet.ryanapi.model.domain.*;
import br.edu.infnet.ryanapi.model.domain.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    ProdutoService produtoService;
    ClienteService clienteService;
    OperadorService operadorService;
    AgendamentoRepository agendamentoRepository;

    public AgendamentoService(ProdutoService produtoService,
                              ClienteService clienteService,
                              OperadorService operadorService,
                              AgendamentoRepository agendamentoRepository) {
        this.produtoService = produtoService;
        this.clienteService = clienteService;
        this.operadorService = operadorService;
        this.agendamentoRepository = agendamentoRepository;
    }

    public Agendamento incluir(AgendamentoRequestDTO agendamentoRequestDTO) {
        Cliente cliente = clienteService.obterPorId(agendamentoRequestDTO.codigoCliente());
        Operador operador = operadorService.obterPorId(agendamentoRequestDTO.codigoOperador());
        Agendamento agendamento = new Agendamento(agendamentoRequestDTO, cliente, operador);
        List<AgendamentoProduto> agendamentoProdutos = agendamentoRequestDTO.agendamentoProdutos()
                .stream()
                .map(agendamentoProdutoRequestDTO -> {
                            Produto produto = produtoService.obterPorId(agendamentoProdutoRequestDTO.codigoProduto());
                            return new AgendamentoProduto(agendamento, produto, agendamentoProdutoRequestDTO.quantidade());
                        }
                ).toList();
        agendamento.adicionarAgendamentoProdutos(agendamentoProdutos);
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento incluirLoader(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> obterAgendamentosDataInicialIntervalo(LocalDate inicio, LocalDate fim) {
        return agendamentoRepository.obterAgendamentosDataInicialIntervalo(inicio, fim);
    }

    public List<Agendamento> obterAgendamentosPorBairro(String bairro) {
        return agendamentoRepository.obterAgendamentosPorBairro(bairro);
    }

    public List<Agendamento> obterLista() {
        return agendamentoRepository.findAll();
    }

    public Agendamento obterPorId(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new AgendamentoNaoEncontradoException("Agendamento não encontrado."));
    }

    public Agendamento alterarAgendamento(Long id, AgendamentoRequestDTO agendamentoRequestDTO) {
        Agendamento agendamento = this.obterPorId(id);
        agendamento.alterar(agendamentoRequestDTO);
        agendamentoRepository.save(agendamento);
        return agendamento;
    }

    public Agendamento alterarProdutos(Long id, List<AgendamentoProdutoRequestDTO> agendamentoRequestDTO) {
        Agendamento agendamento = this.obterPorId(id);
        List<AgendamentoProduto> agendamentoProdutos = agendamentoRequestDTO.stream()
                .map(agendamentoProdutoRequestDTO -> {
                            Produto produto = produtoService.obterPorId(agendamentoProdutoRequestDTO.codigoProduto());
                            return new AgendamentoProduto(agendamento, produto, agendamentoProdutoRequestDTO.quantidade());
                        }
                ).toList();
        agendamento.alterarProdutos(agendamentoProdutos);
        agendamentoRepository.save(agendamento);
        return agendamento;
    }

    public Agendamento alterarStatus(Long id, Integer statusAgendamento) {
        Agendamento agendamento = this.obterPorId(id);
        agendamento.alterarStatus(statusAgendamento);
        agendamentoRepository.save(agendamento);
        return agendamento;
    }

    public Agendamento finalizarAgendamento(Long id) {
        Agendamento agendamento = this.obterPorId(id);
        agendamento.finalizarAgendamento(LocalDateTime.now());
        agendamentoRepository.save(agendamento);
        return agendamento;
    }
}
