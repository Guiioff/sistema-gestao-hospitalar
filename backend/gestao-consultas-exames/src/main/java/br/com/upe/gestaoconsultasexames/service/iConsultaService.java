package br.com.upe.gestaoconsultasexames.service;

import br.com.upe.gestaoconsultasexames.model.Consulta;

import java.time.LocalDateTime;

public interface iConsultaService {

    Long criarConsulta(Long pacienteId, Long medicoId, LocalDateTime dataConsulta);
    Long agendarExameParaConsulta(Consulta consulta, String tipoExame);
    Consulta buscarConsultaPorId(Long consultaId);
}
