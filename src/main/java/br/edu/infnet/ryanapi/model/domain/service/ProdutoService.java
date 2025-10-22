package br.edu.infnet.ryanapi.model.domain.service;


import br.edu.infnet.ryanapi.dto.ProdutoRequestDTO;
import br.edu.infnet.ryanapi.model.domain.Produto;
import br.edu.infnet.ryanapi.model.domain.repository.ProdutoAtributoRepository;
import br.edu.infnet.ryanapi.model.domain.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

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

    public List<Produto> obterLista() {
        return this.produtoRepository.findAll();
    }

    public Produto obterPorId(Long id) {
        return this.produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));
    }

    public Produto alterar(Long id, ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = this.produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));
        produto.alterar(produtoRequestDTO);
        produtoRepository.save(produto);
        return produto;
    }

    public void excluir(Long id) {
        Produto produto = this.produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));
        this.produtoRepository.delete(produto);
    }
}
