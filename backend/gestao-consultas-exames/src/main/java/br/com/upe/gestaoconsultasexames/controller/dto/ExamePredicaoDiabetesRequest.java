package br.com.upe.gestaoconsultasexames.controller.dto;

import java.util.HashMap;
import java.util.Map;

public record ExamePredicaoDiabetesRequest(
        Float idade,
        String localizacao,
        String raca,
        Float imc,
        Float hba1c,
        Float glicoseSanguinea,
        String genero,
        String historicoTabagismo,
        Boolean hipertensao,
        Boolean doencaCardiaca
) {
    public Map<String, Object> toMap() {
        Map<String, Object> dados = new HashMap<>();
        dados.put("idade", idade);
        dados.put("localizacao", localizacao);
        dados.put("raca", raca);
        dados.put("imc", imc);
        dados.put("hba1c", hba1c);
        dados.put("glicoseSanguinea", glicoseSanguinea);
        dados.put("genero", genero);
        dados.put("historicoTabagismo", historicoTabagismo);
        dados.put("hipertensao", hipertensao);
        dados.put("doencaCardiaca", doencaCardiaca);
        return dados;
    }
}