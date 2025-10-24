package br.edu.infnet.ryanapi.model.domain.service;


import br.edu.infnet.ryanapi.dto.EnderecoRequestDTO;
import br.edu.infnet.ryanapi.exceptions.EnderecoNaoEncontradoException;
import br.edu.infnet.ryanapi.model.domain.Endereco;
import br.edu.infnet.ryanapi.model.domain.repository.EnderecoRepository;
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
        return this.enderecoRepository.findById(id)
                .orElseThrow(() -> new EnderecoNaoEncontradoException("Endereço não encontrado."));
    }

    public Endereco alterar(Long id, EnderecoRequestDTO enderecoRequestDTO) {
        Endereco endereco = this.obterPorId(id);
        endereco.alterar(enderecoRequestDTO);
        this.enderecoRepository.save(endereco);
        return endereco;
    }

    public void excluir(Long id) {
        Endereco endereco = this.obterPorId(id);
        this.enderecoRepository.delete(endereco);
    }
}
