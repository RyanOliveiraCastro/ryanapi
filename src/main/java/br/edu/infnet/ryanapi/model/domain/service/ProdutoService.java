package br.edu.infnet.ryanapi.model.domain.service;


import br.edu.infnet.ryanapi.dto.ProdutoRequestDTO;
import br.edu.infnet.ryanapi.exceptions.ProdutoNaoEncontradoException;
import br.edu.infnet.ryanapi.model.domain.Produto;
import br.edu.infnet.ryanapi.model.domain.repository.ProdutoAtributoRepository;
import br.edu.infnet.ryanapi.model.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    ProdutoRepository produtoRepository;
    ProdutoAtributoRepository produtoAtributoRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          ProdutoAtributoRepository produtoAtributoRepository) {
        this.produtoRepository = produtoRepository;
        this.produtoAtributoRepository = produtoAtributoRepository;
    }

    public Produto incluir(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = new Produto(produtoRequestDTO);
        this.produtoRepository.save(produto);
        return produto;
    }

    public Produto incluirLoader(Produto produto) {
        this.produtoRepository.save(produto);
        return produto;
    }

    public List<Produto> obterLista() {
        return this.produtoRepository.findAll();
    }

    public List<Produto> obterPorCategoria(String categoria) {
        return this.produtoRepository.findByCategoria(categoria);
    }

    public List<Produto> obterPorPrecoMaior(BigDecimal preco) {
        return this.produtoRepository.findByPrecoGreaterThanEqualOrderByPrecoDesc(preco);
    }
    public List<Produto> obterPorPrecoMenor(BigDecimal preco) {
        return this.produtoRepository.findByPrecoLessThanEqualOrderByPrecoDesc(preco);
    }

    public Produto obterPorId(Long id) {
        return this.produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado."));
    }

    public Produto alterar(Long id, ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = this.obterPorId(id);
        produto.alterar(produtoRequestDTO);
        produtoRepository.save(produto);
        return produto;
    }

    public void excluir(Long id) {
        Produto produto = this.obterPorId(id);
        this.produtoRepository.delete(produto);
    }
}
