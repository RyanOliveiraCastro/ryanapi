package br.edu.infnet.ryanapi.controllers;

import br.edu.infnet.ryanapi.dto.OperadorRequestDTO;
import br.edu.infnet.ryanapi.dto.OperadorResponseDTO;
import br.edu.infnet.ryanapi.model.domain.Operador;
import br.edu.infnet.ryanapi.model.domain.service.OperadorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operador")
public class OperadorController {

    private final OperadorService operadorService;

    public OperadorController(OperadorService operadorService) {
        this.operadorService = operadorService;
    }

    @PostMapping
    public ResponseEntity<OperadorResponseDTO> incluir(@Valid @RequestBody OperadorRequestDTO operadorRequestDTO) {
        Operador operador = operadorService.incluir(operadorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(OperadorResponseDTO.operadorToOperadorReponseDTO(operador));
    }

    @GetMapping
    public ResponseEntity<List<OperadorResponseDTO>> obterLista() {
        List<Operador> operadors = operadorService.obterLista();
        if (operadors.isEmpty()) {
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(operadors.stream()
                .map(OperadorResponseDTO::operadorToOperadorReponseDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperadorResponseDTO> obterPorId(@PathVariable Long id) {
        Operador operador = operadorService.obterPorId(id);
        return ResponseEntity.ok(OperadorResponseDTO.operadorToOperadorReponseDTO(operador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperadorResponseDTO> alterar(@PathVariable Long id,
                                                      @Valid @RequestBody OperadorRequestDTO operadorRequestDTO) {
        Operador operador = operadorService.alterar(id, operadorRequestDTO);
        return ResponseEntity.ok(OperadorResponseDTO.operadorToOperadorReponseDTO(operador));
    }
}
