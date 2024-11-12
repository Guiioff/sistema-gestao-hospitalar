package br.com.upe.gestaoconsultasexames.service.impl;

import br.com.upe.gestaoconsultasexames.model.Consulta;
import br.com.upe.gestaoconsultasexames.model.Exame;
import br.com.upe.gestaoconsultasexames.repository.ExameRepository;
import br.com.upe.gestaoconsultasexames.service.iExameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExameServiceImpl implements iExameService {

    private final ExameRepository exameRepository;

    @Override
    @Transactional
    public Exame criarExame(String tipoExame, Consulta consulta) {
        return null;
    }

    @Override
    public void realizarExame(Exame exame) {

    }

    @Override
    public void enviarParaModeloML(Map<String, Object> dados) {

    }
}
