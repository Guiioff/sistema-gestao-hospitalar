package br.com.upe.gestaoconsultasexames.service;

import br.com.upe.gestaoconsultasexames.model.Consulta;
import br.com.upe.gestaoconsultasexames.model.Exame;

import java.util.Map;

public interface iExameService {

    Exame criarExame(String tipoExame, Consulta consulta);
    void realizarExame(Exame exame);
    void enviarParaModeloML(Map<String, Object> dados);
}
