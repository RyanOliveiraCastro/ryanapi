package br.edu.infnet.ryanapi.controllers;

import br.edu.infnet.ryanapi.dto.ProdutoDTO;
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
    public ProdutoDTO incluir(@RequestBody ProdutoDTO produto) {
        return produtoService.incluir(produto);
    }

    @GetMapping
    public List<ProdutoDTO> obterLista() {
        return produtoService.obterLista();
    }

    @GetMapping("/{id}")
    public ProdutoDTO obterPorId(@PathVariable Long id) {
        return produtoService.obterPorId(id);
    }

    @PutMapping("/{id}")
    public ProdutoDTO alterar(@PathVariable Long id){
        return produtoService.alterar(id);
    }

    @DeleteMapping("/{id}")
    public void inativar(@PathVariable Long id) {
        produtoService.inativar(id);
    }
}
