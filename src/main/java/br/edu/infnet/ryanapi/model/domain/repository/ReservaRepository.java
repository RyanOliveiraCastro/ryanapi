package br.edu.infnet.ryanapi.model.domain.repository;

import br.edu.infnet.ryanapi.model.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}