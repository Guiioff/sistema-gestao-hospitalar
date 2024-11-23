package com.matt.gestao_agendamentos.controller.dto;

import java.time.LocalDateTime;

public record AgendamentoDTO(Long pacienteId, Long medicoId, LocalDateTime dataConsulta) {
}
