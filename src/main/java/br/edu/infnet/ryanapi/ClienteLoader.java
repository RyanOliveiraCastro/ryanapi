package br.edu.infnet.ryanapi;


import br.edu.infnet.ryanapi.clients.ViaCepFeignClient;
import br.edu.infnet.ryanapi.model.domain.Cliente;
import br.edu.infnet.ryanapi.model.domain.Endereco;
import br.edu.infnet.ryanapi.model.domain.service.ClienteService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Collection;

@Order(2)
@Component
public class ClienteLoader implements ApplicationRunner {

    private final ClienteService clienteService;
    private final ViaCepFeignClient cepFeignClient;

    public ClienteLoader(ClienteService clienteService, ViaCepFeignClient cepFeignClient) {
        this.clienteService = clienteService;
        this.cepFeignClient = cepFeignClient;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        FileReader arquivo = new FileReader("clientes-listagem.csv");
        BufferedReader leitura = new BufferedReader(arquivo);

        String linha = leitura.readLine();
        String[] campos = null;

        while (linha != null) {

            campos = linha.split(";");

            String nome = campos[0];
            String cpfCnpj = campos[1];
            String telefone = campos[2];
            String email = campos[3];
            LocalDate dataNascimento = LocalDate.parse(campos[4]);
            Endereco endereco = cepFeignClient.findByCep(campos[5]);

            Cliente cliente = new Cliente(nome, cpfCnpj, telefone, email, dataNascimento, endereco);

            clienteService.incluirLoader(cliente);
            linha = leitura.readLine();
        }

        Collection<Cliente> clientes = clienteService.obterLista();
        clientes.forEach(System.out::println);
        leitura.close();
    }

}

