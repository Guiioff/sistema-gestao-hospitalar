package com.matt.gestao_agendamentos.producer.message;

public record CriarExameMessage(Long consultaId, String tipoExame) {
}
