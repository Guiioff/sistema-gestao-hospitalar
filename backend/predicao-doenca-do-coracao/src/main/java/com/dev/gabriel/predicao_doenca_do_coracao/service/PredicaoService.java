package com.dev.gabriel.predicao_doenca_do_coracao.service;

import com.dev.gabriel.predicao_doenca_do_coracao.dto.request.PredicaoRequest;
import com.dev.gabriel.predicao_doenca_do_coracao.exception.exceptions.PredicaoException;
import com.dev.gabriel.predicao_doenca_do_coracao.utils.PredicaoUtils;
import lombok.RequiredArgsConstructor;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.EvaluatorUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PredicaoService {
  private final Evaluator evaluator;

  public String predict(PredicaoRequest request) {
    try {
      Map<String, ?> requestMap = PredicaoUtils.converterRequisicaoParaMapa(request);
      Map<String, ?> resultado = this.evaluator.evaluate(requestMap);
      Map<String, ?> decoded = EvaluatorUtil.decodeAll(resultado);
      Map<String, Object> resultadoDecoded = new HashMap<>(decoded);
      return (String) resultadoDecoded.get("resultado");
    } catch (Exception ex) {
      throw new PredicaoException("Erro durante a realização da previsão: " + ex.getMessage());
    }
  }
}
