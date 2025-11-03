package br.edu.infnet.ryanapi.model.domain.repository;

import br.edu.infnet.ryanapi.model.domain.Operador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperadorRepository extends JpaRepository<Operador, Long> {


    List<Operador> findByNomeContaining(String nome);

}