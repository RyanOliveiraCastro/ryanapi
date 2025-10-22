package br.edu.infnet.ryanapi.controllers;

import br.edu.infnet.ryanapi.dto.ProdutoRequestDTO;
import br.edu.infnet.ryanapi.dto.ProdutoResponseDTO;
import br.edu.infnet.ryanapi.model.domain.Produto;
import br.edu.infnet.ryanapi.model.domain.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ProdutoResponseDTO incluir(@RequestBody ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = produtoService.incluir(produtoRequestDTO);
        return ProdutoResponseDTO.produtoToProdutoResponseDTO(produto);
    }

    @GetMapping
    public List<ProdutoResponseDTO> obterLista() {
        List<Produto> produtos = produtoService.obterLista();
        return produtos.stream()
                .map(ProdutoResponseDTO::produtoToProdutoResponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO obterPorId(@PathVariable Long id) {
        Produto produto = produtoService.obterPorId(id);
        return ProdutoResponseDTO.produtoToProdutoResponseDTO(produto);
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO alterar(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        System.out.println("Recebido DTO: " + produtoRequestDTO);
        Produto produto = produtoService.alterar(id, produtoRequestDTO);
        return ProdutoResponseDTO.produtoToProdutoResponseDTO(produto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        produtoService.excluir(id);
    }
}
