package br.edu.infnet.ryanapi.controllers;

import br.edu.infnet.ryanapi.dto.AgendamentoDTO;
import br.edu.infnet.ryanapi.model.domain.service.AgendamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    public AgendamentoDTO incluir(@RequestBody AgendamentoDTO agendamento) {
        return agendamentoService.incluir(agendamento);
    }

    @GetMapping
    public List<AgendamentoDTO> obterLista() {
        return agendamentoService.obterLista();
    }

    @GetMapping("/{id}")
    public AgendamentoDTO obterPorId(@PathVariable Long id) {
        return agendamentoService.obterPorId(id);
    }

    @PutMapping("/{id}")
    public AgendamentoDTO alterar(@PathVariable Long id){
        return agendamentoService.alterar(id);
    }

    @DeleteMapping("/{id}")
    public void inativar(@PathVariable Long id) {
        agendamentoService.inativar(id);
    }

}
