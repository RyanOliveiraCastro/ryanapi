package br.edu.infnet.ryanapi.model.domain.service;


import br.edu.infnet.ryanapi.dto.ClienteRequestDTO;
import br.edu.infnet.ryanapi.dto.EnderecoResponseDTO;
import br.edu.infnet.ryanapi.model.domain.Cliente;
import br.edu.infnet.ryanapi.model.domain.Endereco;
import br.edu.infnet.ryanapi.model.domain.repository.ClienteRepository;
import br.edu.infnet.ryanapi.model.domain.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    EnderecoService enderecoService;

    ClienteRepository clienteRepository;

    public ClienteService(EnderecoService enderecoService,
                          ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoService = enderecoService;
    }


    public Cliente incluir(ClienteRequestDTO clienteRequestDTO) {
        Endereco endereco = enderecoService.incluir(clienteRequestDTO.endereco());
        Cliente cliente = new Cliente(clienteRequestDTO, endereco);
        this.clienteRepository.save(cliente);
        return cliente;
    }

    public List<Cliente> obterLista() {
        return this.clienteRepository.findAll();
    }

    public Cliente obterPorId(Long id) {
        Cliente cliente = this.clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(""));
        return cliente;
    }

    public Cliente alterar(Long id, ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = this.clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));
        Endereco endereco = this.enderecoService.alterar(cliente.getEndereco().getId(), clienteRequestDTO.endereco());
        cliente.alterar(clienteRequestDTO, endereco);
        this.clienteRepository.save(cliente);
        return cliente;
    }

    public void inativar(Long id) {

    }
}
