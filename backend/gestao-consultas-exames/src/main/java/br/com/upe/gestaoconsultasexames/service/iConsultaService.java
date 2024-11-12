package br.com.upe.gestaoconsultasexames.service;

import br.com.upe.gestaoconsultasexames.model.Consulta;
import br.com.upe.gestaoconsultasexames.model.Exame;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

public interface iConsultaService {

    Consulta criarConsulta(Long pacienteId, Long medicoId, LocalDateTime dataConsulta);
    Exame agendarExameParaConsulta(Consulta consulta, String tipoExame);
}
