package br.edu.infnet.ryanapi.controllers;

import br.edu.infnet.ryanapi.dto.ProdutoRequestDTO;
import br.edu.infnet.ryanapi.dto.ProdutoResponseDTO;
import br.edu.infnet.ryanapi.model.domain.Produto;
import br.edu.infnet.ryanapi.model.domain.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProdutoResponseDTO> incluir(@RequestBody ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = produtoService.incluir(produtoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProdutoResponseDTO.produtoToProdutoResponseDTO(produto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> obterLista() {
        List<Produto> produtos = produtoService.obterLista();
        if (produtos.isEmpty()) {
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(produtos.stream()
                .map(ProdutoResponseDTO::produtoToProdutoResponseDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> obterPorId(@PathVariable Long id) {
        Produto produto = produtoService.obterPorId(id);
        return ResponseEntity.ok(ProdutoResponseDTO.produtoToProdutoResponseDTO(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> alterar(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        System.out.println("Recebido DTO: " + produtoRequestDTO);
        Produto produto = produtoService.alterar(id, produtoRequestDTO);
        return ResponseEntity.ok(ProdutoResponseDTO.produtoToProdutoResponseDTO(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
