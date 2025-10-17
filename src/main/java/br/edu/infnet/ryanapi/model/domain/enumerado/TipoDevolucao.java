package br.edu.infnet.ryanapi.model.domain.enumerado;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDevolucao {

    DEVOLUCAO_NO_LOCAL(1, "Cliente devolve no local combinado"),
    BUSCA_EM_CASA(2, "Empresa busca na casa do cliente");

    Integer id;
    String descrição;
}
