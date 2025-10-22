package br.edu.infnet.ryanapi.model.domain.repository;

import br.edu.infnet.ryanapi.model.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}