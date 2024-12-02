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
        dados.put("tipoExame", "PredicaoDiabetes");
        dados.put("age", idade);
        dados.put("location", localizacao);
        dados.put("race", raca);
        dados.put("bmi", imc);
        dados.put("hba1c", hba1c);
        dados.put("blood_glucose", glicoseSanguinea);
        dados.put("gender", genero);
        dados.put("smoking_history", historicoTabagismo);
        dados.put("hypertension", hipertensao);
        dados.put("heart_disease", doencaCardiaca);
        return dados;
    }
}