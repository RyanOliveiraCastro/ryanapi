package br.edu.infnet.ryanapi;


import br.edu.infnet.ryanapi.model.domain.*;
import br.edu.infnet.ryanapi.model.domain.service.ClienteService;
import br.edu.infnet.ryanapi.model.domain.service.OperadorService;
import br.edu.infnet.ryanapi.model.domain.service.ProdutoService;
import br.edu.infnet.ryanapi.model.domain.service.ReservaService;
import jakarta.transaction.Transactional;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Order(4)
@Component
public class ReservaLoader implements ApplicationRunner {

    private ReservaService reservaService;
    private ClienteService clienteService;
    private OperadorService operadorService;
    private ProdutoService produtoService;

    public ReservaLoader(ReservaService reservaService,
                         ClienteService clienteService,
                         OperadorService operadorService,
                         ProdutoService produtoService) {
        this.reservaService = reservaService;
        this.clienteService = clienteService;
        this.operadorService = operadorService;
        this.produtoService = produtoService;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        FileReader arquivo = new FileReader("reservas-listagem.csv");
        BufferedReader leitura = new BufferedReader(arquivo);

        String linha = leitura.readLine();
        String[] campos = null;

        while (linha != null) {
            campos = linha.split(";");
            LocalDate dataInicio = LocalDate.parse(campos[0]);
            LocalDate dataFim = LocalDate.parse(campos[1]);
            Long codigoCliente = Long.valueOf(campos[2]);
            Long codigoOperador = Long.valueOf(campos[3]);
            Cliente cliente = clienteService.obterPorId(codigoCliente);
            Operador operador = operadorService.obterPorId(codigoOperador);

            Reserva reserva
                    = new Reserva(dataInicio, dataFim, cliente, operador);

            String produtosStr = campos[4];
            String[] pares = produtosStr.split(",");
            List<ReservaProduto> reservaProdutos = Arrays.stream(pares)
                    .map(par -> {
                        String[] pv = par.split(":");
                        if (pv.length == 2) {
                            Long codigoProduto = Long.valueOf(pv[0].trim());
                            int quantidade = Integer.parseInt(pv[1].trim());
                            Produto produto = produtoService.obterPorId(codigoProduto);
                            return new ReservaProduto(reserva, produto, quantidade);
                        }
                        return null;
                    }).toList();
            reserva.adicionarReservaProdutos(reservaProdutos);
            reserva.calcularValorTotal();
            reservaService.incluirLoader(reserva);
            linha = leitura.readLine();
        }

        Collection<Reserva> reserva = reservaService.obterLista();
        reserva.forEach(System.out::println);
        leitura.close();
    }

}

