package br.edu.infnet.ryanapi.model.domain.service;


import br.edu.infnet.ryanapi.dto.AgendamentoProdutoRequestDTO;
import br.edu.infnet.ryanapi.dto.AgendamentoRequestDTO;
import br.edu.infnet.ryanapi.exceptions.AgendamentoNaoEncontradoException;
import br.edu.infnet.ryanapi.model.domain.Agendamento;
import br.edu.infnet.ryanapi.model.domain.AgendamentoProduto;
import br.edu.infnet.ryanapi.model.domain.Cliente;
import br.edu.infnet.ryanapi.model.domain.Produto;
import br.edu.infnet.ryanapi.model.domain.repository.AgendamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import jdk.jshell.Snippet;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    ProdutoService produtoService;
    ClienteService clienteService;
    AgendamentoRepository agendamentoRepository;

    public AgendamentoService(ProdutoService produtoService,
                              ClienteService clienteService,
                              AgendamentoRepository agendamentoRepository) {
        this.produtoService = produtoService;
        this.clienteService = clienteService;
        this.agendamentoRepository = agendamentoRepository;
    }

    public Agendamento incluir(AgendamentoRequestDTO agendamentoRequestDTO) {
        Cliente cliente = clienteService.obterPorId(agendamentoRequestDTO.codigoCliente());
        Agendamento agendamento = new Agendamento(agendamentoRequestDTO, cliente);
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

    public Agendamento alterarStatus(Long id, Integer statusAgendamento){
        Agendamento agendamento = this.obterPorId(id);
        agendamento.alterarStatus(statusAgendamento);
        agendamentoRepository.save(agendamento);
        return agendamento;
    }

    public Agendamento finalizarAgendamento(Long id){
        Agendamento agendamento = this.obterPorId(id);
        agendamento.finalizarAgendamento(LocalDateTime.now());
        agendamentoRepository.save(agendamento);
        return agendamento;
    }
}
