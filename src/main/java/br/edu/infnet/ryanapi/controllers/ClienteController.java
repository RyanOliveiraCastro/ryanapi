package br.edu.infnet.ryanapi.controllers;

import br.edu.infnet.ryanapi.dto.ClienteRequestDTO;
import br.edu.infnet.ryanapi.dto.ClienteResponseDTO;
import br.edu.infnet.ryanapi.model.domain.Cliente;
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
    public ClienteResponseDTO incluir(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = clienteService.incluir(clienteRequestDTO);
        return ClienteResponseDTO.clienteToClienteReponseDTO(cliente);
    }

    @GetMapping
    public List<ClienteResponseDTO> obterLista() {
        List<Cliente> clientes = clienteService.obterLista();
        return clientes.stream()
                .map(ClienteResponseDTO::clienteToClienteReponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO obterPorId(@PathVariable Long id) {
        Cliente cliente = clienteService.obterPorId(id);
        return ClienteResponseDTO.clienteToClienteReponseDTO(cliente);
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO alterar(@PathVariable Long id, ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = clienteService.alterar(id, clienteRequestDTO);
        return ClienteResponseDTO.clienteToClienteReponseDTO(cliente);
    }

    @DeleteMapping("/{id}")
    public void inativar(@PathVariable Long id) {
        clienteService.inativar(id);
    }
}
