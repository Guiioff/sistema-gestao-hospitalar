package br.com.upe.gestaoconsultasexames.service;

import br.com.upe.gestaoconsultasexames.model.Consulta;
import br.com.upe.gestaoconsultasexames.model.Exame;

import java.time.LocalDateTime;

public interface iConsultaService {

    Long criarConsulta(Long pacienteId, Long medicoId, LocalDateTime dataConsulta);
    Exame adicionarExameParaConsulta(Consulta consulta, String tipoExame);
    Consulta buscarConsultaPorId(Long consultaId);
}
