package br.edu.infnet.ryanapi;


import br.edu.infnet.ryanapi.model.domain.Agendamento;
import br.edu.infnet.ryanapi.model.domain.AgendamentoProduto;
import br.edu.infnet.ryanapi.model.domain.Cliente;
import br.edu.infnet.ryanapi.model.domain.Produto;
import br.edu.infnet.ryanapi.model.domain.service.AgendamentoService;
import br.edu.infnet.ryanapi.model.domain.service.ClienteService;
import br.edu.infnet.ryanapi.model.domain.service.ProdutoService;
import jakarta.transaction.Transactional;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Order(3)
@Component
public class AgendamentoLoader implements ApplicationRunner {

    private AgendamentoService agendamentoService;
    private ClienteService clienteService;
    private ProdutoService produtoService;

    public AgendamentoLoader(AgendamentoService agendamentoService,
                             ClienteService clienteService,
                             ProdutoService produtoService) {
        this.agendamentoService = agendamentoService;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        FileReader arquivo = new FileReader("agendamentos-listagem.csv");
        BufferedReader leitura = new BufferedReader(arquivo);

        String linha = leitura.readLine();
        String[] campos = null;

        while (linha != null) {
            campos = linha.split(";");
            LocalDate dataInicio = LocalDate.parse(campos[0]);
            LocalDate dataFim = LocalDate.parse(campos[1]);
            LocalDateTime dataHoraDevolucao = null;
            if (!campos[2].isBlank()) {
                dataHoraDevolucao = LocalDateTime.parse(campos[2]);
            }
            Integer tipoEntrega = Integer.valueOf(campos[3]);
            Integer tipoDevolucao = Integer.valueOf(campos[4]);
            Integer status = Integer.valueOf(campos[5]);
            Long codigoCliente = Long.valueOf(campos[6]);
            Cliente cliente = clienteService.obterPorId(codigoCliente);

            Agendamento agendamento
                    = new Agendamento(dataInicio, dataFim, dataHoraDevolucao,
                    tipoEntrega, tipoDevolucao, status, cliente);

            String produtosStr = campos[7];
            String[] pares = produtosStr.split(",");
            List<AgendamentoProduto> agendamentoProdutos = Arrays.stream(pares)
                    .map(par -> {
                        String[] pv = par.split(":");
                        if (pv.length == 2) {
                            Long codigoProduto = Long.valueOf(pv[0].trim());
                            int quantidade = Integer.parseInt(pv[1].trim());
                            Produto produto = produtoService.obterPorId(codigoProduto);
                            return new AgendamentoProduto(agendamento, produto, quantidade);
                        }
                        return null;
                    }).toList();
            agendamento.adicionarAgendamentoProdutos(agendamentoProdutos);
            agendamentoService.incluirLoader(agendamento);
            linha = leitura.readLine();
        }

        Collection<Agendamento> agendamento = agendamentoService.obterLista();
        agendamento.forEach(System.out::println);
        leitura.close();
    }

}

