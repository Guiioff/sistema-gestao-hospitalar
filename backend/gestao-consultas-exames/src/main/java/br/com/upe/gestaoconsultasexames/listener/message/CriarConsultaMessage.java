package br.com.upe.gestaoconsultasexames.listener.message;

import java.time.LocalDateTime;

public record CriarConsultaMessage(Long pacienteId, Long medicoId, LocalDateTime dataConsulta) {
}
