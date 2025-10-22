package br.edu.infnet.ryanapi.model.domain.repository;

import br.edu.infnet.ryanapi.model.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}