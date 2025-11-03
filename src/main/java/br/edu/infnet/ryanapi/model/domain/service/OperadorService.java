package br.edu.infnet.ryanapi.model.domain.service;


import br.edu.infnet.ryanapi.dto.OperadorRequestDTO;
import br.edu.infnet.ryanapi.exceptions.OperadorNaoEncontradoException;
import br.edu.infnet.ryanapi.model.domain.Endereco;
import br.edu.infnet.ryanapi.model.domain.Operador;
import br.edu.infnet.ryanapi.model.domain.repository.OperadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperadorService {

    EnderecoService enderecoService;

    OperadorRepository operadorRepository;

    public OperadorService(EnderecoService enderecoService,
                           OperadorRepository operadorRepository) {
        this.operadorRepository = operadorRepository;
        this.enderecoService = enderecoService;
    }


    public Operador incluir(OperadorRequestDTO operadorRequestDTO) {
        Endereco endereco = enderecoService.incluir(operadorRequestDTO.endereco());
        Operador operador = new Operador(operadorRequestDTO, endereco);
        this.operadorRepository.save(operador);
        return operador;
    }

    public Operador incluirLoader(Operador operador) {
        this.operadorRepository.save(operador);
        return operador;
    }

    public List<Operador> obterLista() {
        return this.operadorRepository.findAll();
    }

    public Operador obterPorId(Long id) {
        return this.operadorRepository.findById(id)
                .orElseThrow(() -> new OperadorNaoEncontradoException("Operador não encontrado."));
    }

    public Operador alterar(Long id, OperadorRequestDTO operadorRequestDTO) {
        Operador operador = this.obterPorId(id);
        operador.alterar(operadorRequestDTO);
        this.operadorRepository.save(operador);
        return operador;
    }
}
