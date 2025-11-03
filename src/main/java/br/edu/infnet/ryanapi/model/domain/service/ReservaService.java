package br.edu.infnet.ryanapi.model.domain.service;


import br.edu.infnet.ryanapi.dto.ReservaRequestDTO;
import br.edu.infnet.ryanapi.exceptions.ReservaNaoEncontradoException;
import br.edu.infnet.ryanapi.model.domain.*;
import br.edu.infnet.ryanapi.model.domain.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    ProdutoService produtoService;
    ClienteService clienteService;
    OperadorService operadorService;
    ReservaRepository reservaRepository;

    public ReservaService(ProdutoService produtoService,
                          ClienteService clienteService,
                          OperadorService operadorService,
                          ReservaRepository reservaRepository) {
        this.produtoService = produtoService;
        this.clienteService = clienteService;
        this.operadorService = operadorService;
        this.reservaRepository = reservaRepository;
    }

    public Reserva incluir(ReservaRequestDTO reservaRequestDTO) {
        Cliente cliente = clienteService.obterPorId(reservaRequestDTO.codigoCliente());
        Operador operador = operadorService.obterPorId(reservaRequestDTO.codigoOperador());

        Reserva reserva = new Reserva(reservaRequestDTO, cliente, operador);

        List<ReservaProduto> reservaProdutos = reservaRequestDTO.reservaProdutos()
                .stream()
                .map(reservaProdutoRequestDTO -> {
                    Produto produto = produtoService.obterPorId(reservaProdutoRequestDTO.codigoProduto());
                    return new ReservaProduto(reserva, produto, reservaProdutoRequestDTO.quantidade());

                }).toList();
        reserva.adicionarReservaProdutos(reservaProdutos);
        reserva.calcularValorTotal();
        return reservaRepository.save(reserva);
    }

    public Reserva incluirLoader(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public List<Reserva> obterLista() {
        return reservaRepository.findAll();
    }

    public Reserva obterPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNaoEncontradoException("Reserva não encontrado."));
    }

    public Reserva alterarReserva(Long id, ReservaRequestDTO reservaRequestDTO) {
        Reserva reserva = this.obterPorId(id);
        reserva.alterar(reservaRequestDTO);
        reservaRepository.save(reserva);
        return reserva;
    }


}
