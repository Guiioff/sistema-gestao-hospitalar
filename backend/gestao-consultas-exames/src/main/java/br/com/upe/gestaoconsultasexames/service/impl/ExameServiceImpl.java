package br.com.upe.gestaoconsultasexames.service.impl;

import br.com.upe.gestaoconsultasexames.listener.ExameProducer;
import br.com.upe.gestaoconsultasexames.model.Consulta;
import br.com.upe.gestaoconsultasexames.model.Exame;
import br.com.upe.gestaoconsultasexames.model.ExamePredicaoAtaqueCardiaco;
import br.com.upe.gestaoconsultasexames.model.ExamePredicaoDiabetes;
import br.com.upe.gestaoconsultasexames.repository.ExameRepository;
import br.com.upe.gestaoconsultasexames.service.iExameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExameServiceImpl implements iExameService {

    private final ExameRepository exameRepository;
    private final ExameProducer exameProducer;

    @Override
    @Transactional
    public Exame criarExame(String tipoExame, Consulta consulta) {
        Exame exame;
        if ("AtaqueCardiaco".equals(tipoExame)) {
            exame = new ExamePredicaoAtaqueCardiaco();
        } else if("Diabetes".equals(tipoExame)) {
            exame = new ExamePredicaoDiabetes();
        } else {
            throw new IllegalArgumentException("Tipo de exame inválido.");
        }
        exame.setConsulta(consulta);
        exame.setDataAgendamento(LocalDateTime.now());

        return exameRepository.save(exame);
    }

    @Override
    public void realizarExame(Long exameId, Map<String, Object> dados, String tipoExame) {
        Optional<Exame> exameOptional = exameRepository.findById(exameId);

        if (exameOptional.isEmpty()) throw new RuntimeException("EXAME NÃO ENCONTRADO");

        Exame exame = exameOptional.get();

        if (!exame.getTipoExameDiscriminador().equals(tipoExame)) {
            throw new RuntimeException("O EXAME SOLICITADO NÃO FOI ESSE");
        }
        enviarParaModeloML(dados, tipoExame);
    }

    @Override
    public void enviarParaModeloML(Map<String, Object> dados, String tipoExame) {
        exameProducer.enviarDadosParaFila(dados);
    }
}
