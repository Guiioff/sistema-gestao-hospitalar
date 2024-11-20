package com.dev.gabriel.predicao_doenca_do_coracao.config;

import com.dev.gabriel.predicao_doenca_do_coracao.exception.exceptions.PredicaoException;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.LoadingModelEvaluatorBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class LoaderConfig {
  @Value("${modelo.caminho-modelo}")
  private String caminhoModelo;

  @Bean
  public Evaluator evaluator() {
    try {
      return new LoadingModelEvaluatorBuilder().load(new File(caminhoModelo)).build();
    } catch (Exception e) {
      throw new PredicaoException("Falha ao carregar o arquivo do modelo de predição");
    }
  }
}
