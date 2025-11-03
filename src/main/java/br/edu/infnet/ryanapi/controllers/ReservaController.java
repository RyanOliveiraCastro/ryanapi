package br.edu.infnet.ryanapi.controllers;

import br.edu.infnet.ryanapi.dto.ReservaResponseDTO;
import br.edu.infnet.ryanapi.dto.ReservaRequestDTO;
import br.edu.infnet.ryanapi.model.domain.Agendamento;
import br.edu.infnet.ryanapi.model.domain.Reserva;
import br.edu.infnet.ryanapi.model.domain.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> incluir(@RequestBody ReservaRequestDTO reservaRequestDTO) {
        Reserva reserva = reservaService.incluir(reservaRequestDTO);
        ReservaResponseDTO reservaResponseDTO
                = ReservaResponseDTO.reservaToReservaReponseDTO(reserva);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservaResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> obterLista() {
        List<Reserva> reservas = reservaService.obterLista();
        if (reservas.isEmpty()) {
            ResponseEntity.noContent().build();
        }
        List<ReservaResponseDTO> reservaResponseDTO = reservas.stream()
                .map(ReservaResponseDTO::reservaToReservaReponseDTO)
                .toList();
        return ResponseEntity.ok(reservaResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> obterPorId(@PathVariable Long id) {
        Reserva reserva = reservaService.obterPorId(id);
        return ResponseEntity.ok(ReservaResponseDTO.reservaToReservaReponseDTO(reserva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> alterar(@PathVariable Long id,
                                                          @Valid @RequestBody ReservaRequestDTO reservaRequestDTO) {
        Reserva reserva = reservaService.alterarReserva(id, reservaRequestDTO);
        return ResponseEntity.ok(ReservaResponseDTO.reservaToReservaReponseDTO(reserva));
    }

}
