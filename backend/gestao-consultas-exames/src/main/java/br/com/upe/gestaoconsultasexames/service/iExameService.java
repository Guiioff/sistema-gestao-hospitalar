package br.com.upe.gestaoconsultasexames.service;

import br.com.upe.gestaoconsultasexames.model.Consulta;
import br.com.upe.gestaoconsultasexames.model.Exame;
import br.com.upe.gestaoconsultasexames.model.enums.TipoExameEnum;

import java.util.Map;

public interface iExameService {

    Exame criarExame(String tipoExame, Consulta consulta);
    void realizarExame(Long exameId, Map<String, Object> dados, TipoExameEnum tipoExame);
    void enviarParaModeloML(Map<String, Object> dados, TipoExameEnum tipoExame);
}
