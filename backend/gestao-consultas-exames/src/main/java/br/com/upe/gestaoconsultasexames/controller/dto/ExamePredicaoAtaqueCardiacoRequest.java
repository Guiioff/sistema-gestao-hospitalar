package br.com.upe.gestaoconsultasexames.controller.dto;

import java.util.HashMap;
import java.util.Map;

public record ExamePredicaoAtaqueCardiacoRequest(
        Integer idade,
        String sexo,
        Integer tipoDorToracica,
        Integer pressaoSanguineaRepouso,
        Integer colesterolSerico,
        Boolean glicemiaJejumMaiorQue120,
        Integer resultadosEletrocardiogramaRepouso,
        Integer frequenciaCardiacaMaxima,
        Boolean anginaInduzida,
        Float depressaoSTInduzida,
        Integer inclinacaoPicoST,
        Integer numeroVasosPrincipais,
        String avaliacaoTesteEsforco
) {
    public Map<String, Object> toMap() {
        Map<String, Object> dados = new HashMap<>();
        dados.put("tipoExame", "PredicaoAtaqueCardiaco");
        dados.put("idade", idade);
        dados.put("sexo", sexo);
        dados.put("tipoDorToracica", tipoDorToracica);
        dados.put("pressaoSanguineaRepouso", pressaoSanguineaRepouso);
        dados.put("colesterolSerico", colesterolSerico);
        dados.put("glicemiaJejumMaiorQue120", glicemiaJejumMaiorQue120);
        dados.put("resultadosEletrocardiogramaRepouso", resultadosEletrocardiogramaRepouso);
        dados.put("frequenciaCardiacaMaxima", frequenciaCardiacaMaxima);
        dados.put("anginaInduzida", anginaInduzida);
        dados.put("depressaoSTInduzida", depressaoSTInduzida);
        dados.put("inclinacaoPicoST", inclinacaoPicoST);
        dados.put("numeroVasosPrincipais", numeroVasosPrincipais);
        dados.put("avaliacaoTesteEsforco", avaliacaoTesteEsforco);
        return dados;
    }
}
