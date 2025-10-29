package br.edu.infnet.ryanapi.controllers;

import br.edu.infnet.ryanapi.dto.AgendamentoProdutoRequestDTO;
import br.edu.infnet.ryanapi.dto.AgendamentoRequestDTO;
import br.edu.infnet.ryanapi.dto.AgendamentoResponseDTO;
import br.edu.infnet.ryanapi.model.domain.Agendamento;
import br.edu.infnet.ryanapi.model.domain.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> incluir(@RequestBody AgendamentoRequestDTO agendamentoRequestDTO) {
        Agendamento agendamento = agendamentoService.incluir(agendamentoRequestDTO);
        AgendamentoResponseDTO agendamentoResponseDTO
                = AgendamentoResponseDTO.agendamentoToAgendamentoReponseDTO(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(agendamentoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> obterLista() {
        List<Agendamento> agendamentos = agendamentoService.obterLista();
        if (agendamentos.isEmpty()) {
            ResponseEntity.noContent().build();
        }
        List<AgendamentoResponseDTO> agendamentoResponseDTOS = agendamentos.stream()
                .map(AgendamentoResponseDTO::agendamentoToAgendamentoReponseDTO)
                .toList();
        return ResponseEntity.ok(agendamentoResponseDTOS);
    }

    @GetMapping("/intervalo/data-inicial/{inicio}/{fim}")
    public ResponseEntity<List<AgendamentoResponseDTO>> obterAgendamentosDataInicialIntervalo(@PathVariable LocalDate inicio, @PathVariable LocalDate fim) {
        List<Agendamento> agendamentos = agendamentoService.obterAgendamentosDataInicialIntervalo(inicio, fim);
        if (agendamentos.isEmpty()) {
            ResponseEntity.noContent().build();
        }
        List<AgendamentoResponseDTO> agendamentoResponseDTOS = agendamentos.stream()
                .map(AgendamentoResponseDTO::agendamentoToAgendamentoReponseDTO)
                .toList();
        return ResponseEntity.ok(agendamentoResponseDTOS);
    }

    @GetMapping("/bairro")
    public ResponseEntity<List<AgendamentoResponseDTO>> obterAgendamentosPorBairro(@RequestParam String nome) {
        List<Agendamento> agendamentos = agendamentoService.obterAgendamentosPorBairro(nome);
        if (agendamentos.isEmpty()) {
            ResponseEntity.noContent().build();
        }
        List<AgendamentoResponseDTO> agendamentoResponseDTOS = agendamentos.stream()
                .map(AgendamentoResponseDTO::agendamentoToAgendamentoReponseDTO)
                .toList();
        return ResponseEntity.ok(agendamentoResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> obterPorId(@PathVariable Long id) {
        Agendamento agendamento = agendamentoService.obterPorId(id);
        return ResponseEntity.ok(AgendamentoResponseDTO.agendamentoToAgendamentoReponseDTO(agendamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> alterar(@PathVariable Long id,
                                                          @Valid @RequestBody AgendamentoRequestDTO agendamentoRequestDTO) {
        Agendamento agendamento = agendamentoService.alterarAgendamento(id, agendamentoRequestDTO);
        return ResponseEntity.ok(AgendamentoResponseDTO.agendamentoToAgendamentoReponseDTO(agendamento));
    }

    @PutMapping("/{id}/produtos")
    public ResponseEntity<AgendamentoResponseDTO> alterarProdutos(@PathVariable Long id,
                                                                  @Valid @RequestBody List<AgendamentoProdutoRequestDTO> agendamentoRequestDTO) {
        Agendamento agendamento = agendamentoService.alterarProdutos(id, agendamentoRequestDTO);
        return ResponseEntity.ok(AgendamentoResponseDTO.agendamentoToAgendamentoReponseDTO(agendamento));
    }

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<AgendamentoResponseDTO> alterarStatus(@PathVariable Long id,
                                                                @PathVariable Integer status) {
        Agendamento agendamento = agendamentoService.alterarStatus(id, status);
        return ResponseEntity.ok(AgendamentoResponseDTO.agendamentoToAgendamentoReponseDTO(agendamento));
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<AgendamentoResponseDTO> finalizarAgendamento(@PathVariable Long id) {
        Agendamento agendamento = agendamentoService.finalizarAgendamento(id);
        return ResponseEntity.ok(AgendamentoResponseDTO.agendamentoToAgendamentoReponseDTO(agendamento));
    }


}
