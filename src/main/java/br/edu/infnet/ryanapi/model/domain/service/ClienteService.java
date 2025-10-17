package br.edu.infnet.ryanapi.model.domain.service;


import br.edu.infnet.ryanapi.dto.ClienteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {


    public ClienteDTO incluir(ClienteDTO cliente) {
        return null;
    }

    public List<ClienteDTO> obterLista() {
        return List.of();
    }

    public ClienteDTO obterPorId(Long id) {
        return null;
    }

    public ClienteDTO alterar(Long id){
        return null;
    }

    public void inativar(Long id) {

    }
}
