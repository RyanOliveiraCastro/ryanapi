package br.edu.infnet.ryanapi.controllers;

import br.edu.infnet.ryanapi.dto.ClienteDTO;
import br.edu.infnet.ryanapi.model.domain.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ClienteDTO incluir(@RequestBody ClienteDTO cliente) {
        return clienteService.incluir(cliente);
    }

    @GetMapping
    public List<ClienteDTO> obterLista() {
        return clienteService.obterLista();
    }

    @GetMapping("/{id}")
    public ClienteDTO obterPorId(@PathVariable Long id) {
        return clienteService.obterPorId(id);
    }

    @PutMapping("/{id}")
    public ClienteDTO alterar(@PathVariable Long id){
        return clienteService.alterar(id);
    }

    @DeleteMapping("/{id}")
    public void inativar(@PathVariable Long id) {
        clienteService.inativar(id);
    }
}
