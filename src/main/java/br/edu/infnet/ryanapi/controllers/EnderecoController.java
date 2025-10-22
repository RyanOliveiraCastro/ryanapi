package br.edu.infnet.ryanapi.controllers;

import br.edu.infnet.ryanapi.dto.EnderecoRequestDTO;
import br.edu.infnet.ryanapi.dto.EnderecoResponseDTO;
import br.edu.infnet.ryanapi.model.domain.Endereco;
import br.edu.infnet.ryanapi.model.domain.service.EnderecoService;
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
    public EnderecoResponseDTO incluir(@RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        Endereco endereco = enderecoService.incluir(enderecoRequestDTO);
        return EnderecoResponseDTO.enderecoToEnderecoReponseDTO(endereco);
    }

    @GetMapping
    public List<EnderecoResponseDTO> obterLista() {
        List<Endereco> enderecos = enderecoService.obterLista();
        return enderecos.stream()
                .map(EnderecoResponseDTO::enderecoToEnderecoReponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public EnderecoResponseDTO obterPorId(@PathVariable Long id) {
        Endereco endereco = enderecoService.obterPorId(id);
        return EnderecoResponseDTO.enderecoToEnderecoReponseDTO(endereco);

    }

    @PutMapping("/{id}")
    public EnderecoResponseDTO alterar(@PathVariable Long id, EnderecoRequestDTO enderecoRequestDTO) {
        Endereco endereco = enderecoService.alterar(id, enderecoRequestDTO);
        return EnderecoResponseDTO.enderecoToEnderecoReponseDTO(endereco);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        enderecoService.excluir(id);
    }
}
