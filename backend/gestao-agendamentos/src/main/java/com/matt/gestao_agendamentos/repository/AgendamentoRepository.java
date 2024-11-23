package com.matt.gestao_agendamentos.repository;

import com.matt.gestao_agendamentos.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
