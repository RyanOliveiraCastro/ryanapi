package br.edu.infnet.ryanapi.repository;

import br.edu.infnet.ryanapi.model.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}