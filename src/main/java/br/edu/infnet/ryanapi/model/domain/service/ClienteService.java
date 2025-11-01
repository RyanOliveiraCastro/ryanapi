package br.edu.infnet.ryanapi.model.domain.service;


import br.edu.infnet.ryanapi.dto.ClienteRequestDTO;
import br.edu.infnet.ryanapi.exceptions.ClienteNaoEncontradoException;
import br.edu.infnet.ryanapi.model.domain.Cliente;
import br.edu.infnet.ryanapi.model.domain.Endereco;
import br.edu.infnet.ryanapi.model.domain.repository.ClienteRepository;
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

    public Cliente incluirLoader(Cliente cliente) {
        this.clienteRepository.save(cliente);
        return cliente;
    }

    public List<Cliente> obterLista() {
        return this.clienteRepository.findAll();
    }

    public Cliente obterPorId(Long id) {
        return this.clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado."));
    }

    public Cliente alterar(Long id, ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = this.obterPorId(id);
        Endereco endereco = this.enderecoService.alterar(cliente.getEndereco().getId(), clienteRequestDTO.endereco());
        cliente.alterar(clienteRequestDTO, endereco);
        this.clienteRepository.save(cliente);
        return cliente;
    }
}
