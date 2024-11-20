package com.dev.gabriel.predicao_doenca_do_coracao.utils;

import com.dev.gabriel.predicao_doenca_do_coracao.dto.request.PredicaoRequest;

import java.util.HashMap;
import java.util.Map;

public class PredicaoUtils {
  private PredicaoUtils() {}

  public static Map<String, Object> converterRequisicaoParaMapa(PredicaoRequest request) {
    Map<String, Object> result = new HashMap<>();

    result.put("idade", request.idade());
    result.put("sexo", request.sexo().substring(0, 1).toUpperCase() + request.sexo().substring(1));
    result.put("tipoDorToracica", request.tipoDorToracica());
    result.put("pressaoSanguineaRepouso", request.pressaoSanguineaRepouso());
    result.put("colesterolSerico", request.colesterolSerico());
    result.put(
        "glicemiaJejum",
        Boolean.TRUE.equals(request.glicemiaJejumMaiorQue120())
            ? "Maior que 120 mg/dl"
            : "Menor que 120 mg/dl");
    result.put("resultadosEletrocardiogramaRepouso", request.resultadosEletrocardiogramaRepouso());
    result.put("frequenciaCardiacaMaxima", request.frequenciaCardiacaMaxima());
    result.put("anginaInduzida", Boolean.TRUE.equals(request.anginaInduzida()) ? "Sim" : "Não");
    result.put("depressaoSTInduzida", request.depressaoSTInduzida());
    result.put("inclinacaoPicoST", request.inclinacaoPicoST());
    result.put("numeroVasosPrincipais", request.numeroVasosPrincipais());
    result.put(
        "avaliacaoTesteEsforco", tratarAvaliacaoTesteEsforco(request.avaliacaoTesteEsforco()));

    return result;
  }

  private static String tratarAvaliacaoTesteEsforco(String requestValue) {
    if (requestValue.equals("normal")) {
      return "Normal";
    } else if (requestValue.contains("irr")) {
      return "Problema irreversível";
    } else {
      return "Problema reversível";
    }
  }
}
