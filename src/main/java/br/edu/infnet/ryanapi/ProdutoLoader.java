package br.edu.infnet.ryanapi;


import br.edu.infnet.ryanapi.model.domain.Produto;
import br.edu.infnet.ryanapi.model.domain.ProdutoAtributo;
import br.edu.infnet.ryanapi.model.domain.service.ProdutoService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

@Order(1)
@Component
public class ProdutoLoader implements ApplicationRunner {

    private final ProdutoService produtoService;

    public ProdutoLoader(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        FileReader arquivo = new FileReader("produtos-listagem.csv");
        BufferedReader leitura = new BufferedReader(arquivo);

        String linha = leitura.readLine();
        String[] campos = null;

        while (linha != null) {

            campos = linha.split(";");

            String nome = campos[0];
            String categoria = campos[1];
            String marca = campos[2];
            BigDecimal preco = new BigDecimal(campos[3]);
            Produto produto = new Produto(nome, categoria, marca, preco);


            String atributosStr = campos[4].replace("[", "").replace("]", "");
            String[] pares = atributosStr.split(",");

            Arrays.stream(pares)
                    .forEach(par -> {
                        String[] chaveValor = par.split(":");
                        if (chaveValor.length == 2) {
                            produto.adicionarAtributo(new ProdutoAtributo(chaveValor[0].trim(), chaveValor[1].trim()));
                        }
                    });

            produtoService.incluirLoader(produto);
            linha = leitura.readLine();
        }

        Collection<Produto> produtos = produtoService.obterLista();
        produtos.forEach(System.out::println);
        leitura.close();
    }

}

