package com.matt.gestao_agendamentos.producer.message;

import java.time.LocalDateTime;

public record CriarAgendamentoMessage(Long pacienteId, Long medicoId, LocalDateTime dataConsulta) {
}
