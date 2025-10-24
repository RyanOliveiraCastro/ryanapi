package br.edu.infnet.ryanapi.controllers;

import br.edu.infnet.ryanapi.dto.EnderecoRequestDTO;
import br.edu.infnet.ryanapi.dto.EnderecoResponseDTO;
import br.edu.infnet.ryanapi.model.domain.Endereco;
import br.edu.infnet.ryanapi.model.domain.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> incluir(@RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        Endereco endereco = enderecoService.incluir(enderecoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EnderecoResponseDTO.enderecoToEnderecoReponseDTO(endereco));
    }

    @GetMapping
    public ResponseEntity<List<EnderecoResponseDTO>> obterLista() {
        List<Endereco> enderecos = enderecoService.obterLista();
        if (enderecos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(enderecos.stream()
                .map(EnderecoResponseDTO::enderecoToEnderecoReponseDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> obterPorId(@PathVariable Long id) {
        Endereco endereco = enderecoService.obterPorId(id);
        return ResponseEntity.ok(EnderecoResponseDTO.enderecoToEnderecoReponseDTO(endereco));

    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> alterar(@PathVariable Long id, EnderecoRequestDTO enderecoRequestDTO) {
        Endereco endereco = enderecoService.alterar(id, enderecoRequestDTO);
        return ResponseEntity.ok(EnderecoResponseDTO.enderecoToEnderecoReponseDTO(endereco));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        enderecoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
