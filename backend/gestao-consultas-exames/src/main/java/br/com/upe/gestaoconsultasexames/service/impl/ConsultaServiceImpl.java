package br.com.upe.gestaoconsultasexames.service.impl;

import br.com.upe.gestaoconsultasexames.model.Consulta;
import br.com.upe.gestaoconsultasexames.model.Exame;
import br.com.upe.gestaoconsultasexames.repository.ConsultaRepository;
import br.com.upe.gestaoconsultasexames.service.iConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements iConsultaService {

    private final ConsultaRepository Consultarepository;

    @Override
    @Transactional
    public Consulta criarConsulta(Long pacienteId, Long medicoId, LocalDateTime dataConsulta) {
        return null;
    }

    @Override
    public Exame agendarExameParaConsulta(Consulta consulta, String tipoExame) {
        return null;
    }
}
