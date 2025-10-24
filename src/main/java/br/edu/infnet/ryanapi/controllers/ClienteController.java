package br.edu.infnet.ryanapi.controllers;

import br.edu.infnet.ryanapi.dto.ClienteRequestDTO;
import br.edu.infnet.ryanapi.dto.ClienteResponseDTO;
import br.edu.infnet.ryanapi.model.domain.Cliente;
import br.edu.infnet.ryanapi.model.domain.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ClienteResponseDTO> incluir(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = clienteService.incluir(clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ClienteResponseDTO.clienteToClienteReponseDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> obterLista() {
        List<Cliente> clientes = clienteService.obterLista();
        if (clientes.isEmpty()) {
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes.stream()
                .map(ClienteResponseDTO::clienteToClienteReponseDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> obterPorId(@PathVariable Long id) {
        Cliente cliente = clienteService.obterPorId(id);
        return ResponseEntity.ok(ClienteResponseDTO.clienteToClienteReponseDTO(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> alterar(@PathVariable Long id, ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = clienteService.alterar(id, clienteRequestDTO);
        return ResponseEntity.ok(ClienteResponseDTO.clienteToClienteReponseDTO(cliente));
    }
}
