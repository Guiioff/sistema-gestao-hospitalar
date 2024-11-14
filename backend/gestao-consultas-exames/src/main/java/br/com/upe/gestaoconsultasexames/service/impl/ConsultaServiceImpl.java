package br.com.upe.gestaoconsultasexames.service.impl;

import br.com.upe.gestaoconsultasexames.model.Consulta;
import br.com.upe.gestaoconsultasexames.model.Exame;
import br.com.upe.gestaoconsultasexames.repository.ConsultaRepository;
import br.com.upe.gestaoconsultasexames.service.iConsultaService;
import br.com.upe.gestaoconsultasexames.service.iExameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements iConsultaService {

    private final iExameService exameService;

    private final ConsultaRepository consultaRepository;

    @Override
    @Transactional
    public Consulta criarConsulta(Long pacienteId, Long medicoId, LocalDateTime dataConsulta) {
        Consulta consulta = new Consulta();
        consulta.setPacienteId(pacienteId);
        consulta.setMedicoId(medicoId);
        consulta.setDataConsulta(dataConsulta);
        return consultaRepository.save(consulta);
    }

    @Override
    public Exame agendarExameParaConsulta(Consulta consulta, String tipoExame) {
        Exame exame = exameService.criarExame(tipoExame, consulta);
        consulta.getExames().add(exame);
        return exame;
    }
}
