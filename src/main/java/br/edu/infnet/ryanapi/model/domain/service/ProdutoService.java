package br.edu.infnet.ryanapi.model.domain.service;


import br.edu.infnet.ryanapi.dto.ProdutoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {


    public ProdutoDTO incluir(ProdutoDTO agendamento) {
        return null;
    }

    public List<ProdutoDTO> obterLista() {
        return List.of();
    }

    public ProdutoDTO obterPorId(Long id) {
        return null;
    }

    public ProdutoDTO alterar(Long id) {
        return null;
    }

    public void inativar(Long id) {

    }
}
