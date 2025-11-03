package br.edu.infnet.ryanapi.model.domain.repository;

import br.edu.infnet.ryanapi.model.domain.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query("select a from agendamento a where a.dataInicio between ?1 and ?2")
    List<Agendamento> obterAgendamentosDataInicialIntervalo(LocalDate inicio, LocalDate fim);

    @Query("select a from agendamento a where a.cliente.endereco.bairro = ?1")
    List<Agendamento> obterAgendamentosPorBairro(String bairro);

}