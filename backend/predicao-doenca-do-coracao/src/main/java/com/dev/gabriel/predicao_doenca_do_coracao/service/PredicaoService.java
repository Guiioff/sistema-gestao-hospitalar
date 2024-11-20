package com.dev.gabriel.predicao_doenca_do_coracao.service;

import lombok.RequiredArgsConstructor;
import org.jpmml.evaluator.Evaluator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PredicaoService {
  private final Evaluator evaluator;
}
