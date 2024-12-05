package com.matt.gestao_agendamentos.repository;

import com.matt.gestao_agendamentos.model.Exame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExameRepository extends JpaRepository<Exame, Long> {
}
