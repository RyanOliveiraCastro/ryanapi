package br.edu.infnet.ryanapi.model.domain.repository;

import br.edu.infnet.ryanapi.model.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoria(String categoria);

    List<Produto> findByPrecoLessThanEqualOrderByPrecoDesc(BigDecimal valor);

    List<Produto> findByPrecoGreaterThanEqualOrderByPrecoDesc(BigDecimal valor);
}