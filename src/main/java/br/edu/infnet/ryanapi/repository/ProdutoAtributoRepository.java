package br.edu.infnet.ryanapi.repository;

import br.edu.infnet.ryanapi.model.domain.ProdutoAtributo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoAtributoRepository extends JpaRepository<ProdutoAtributo, Long> {
}