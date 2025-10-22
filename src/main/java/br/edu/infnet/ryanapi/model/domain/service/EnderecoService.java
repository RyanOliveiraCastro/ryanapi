package br.edu.infnet.ryanapi.model.domain.service;


import br.edu.infnet.ryanapi.dto.EnderecoRequestDTO;
import br.edu.infnet.ryanapi.model.domain.Endereco;
import br.edu.infnet.ryanapi.model.domain.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco incluir(EnderecoRequestDTO enderecoRequestDTO) {
        Endereco endereco = new Endereco(enderecoRequestDTO);
        this.enderecoRepository.save(endereco);
        return endereco;
    }

    public List<Endereco> obterLista() {
        return this.enderecoRepository.findAll();
    }

    public Endereco obterPorId(Long id) {
        Endereco endereco = this.enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(""));
        return endereco;
    }

    public Endereco alterar(Long id, EnderecoRequestDTO enderecoRequestDTO) {
        Endereco endereco = this.enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado."));
        endereco.alterar(enderecoRequestDTO);
        this.enderecoRepository.save(endereco);
        return endereco;
    }

    public void excluir(Long id) {
        this.enderecoRepository.deleteById(id);
    }
}
