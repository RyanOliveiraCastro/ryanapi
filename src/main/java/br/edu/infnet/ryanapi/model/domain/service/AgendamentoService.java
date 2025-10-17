package br.edu.infnet.ryanapi.model.domain.service;


import br.edu.infnet.ryanapi.dto.AgendamentoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {


    public AgendamentoDTO incluir(AgendamentoDTO agendamento) {
        return null;
    }

    public List<AgendamentoDTO> obterLista() {
        return List.of();
    }

    public AgendamentoDTO obterPorId(Long id) {
        return null;
    }

    public AgendamentoDTO alterar(Long id) {
        return null;
    }

    public void inativar(Long id) {

    }
}
