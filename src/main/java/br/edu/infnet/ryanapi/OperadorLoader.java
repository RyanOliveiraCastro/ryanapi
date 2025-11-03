package br.edu.infnet.ryanapi;


import br.edu.infnet.ryanapi.model.domain.Operador;
import br.edu.infnet.ryanapi.model.domain.service.OperadorService;
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
public class OperadorLoader implements ApplicationRunner {

    private final OperadorService operadorService;

    public OperadorLoader(OperadorService operadorService) {
        this.operadorService = operadorService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        FileReader arquivo = new FileReader("operadores-listagem.csv");
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
            String matricula = campos[5];
            LocalDate dataContratacao = LocalDate.parse(campos[6]);

            Operador operador = new Operador(nome, cpfCnpj, telefone, email, dataNascimento, matricula, dataContratacao);

            operadorService.incluirLoader(operador);
            linha = leitura.readLine();
        }

        Collection<Operador> operadors = operadorService.obterLista();
        operadors.forEach(System.out::println);
        leitura.close();
    }

}

