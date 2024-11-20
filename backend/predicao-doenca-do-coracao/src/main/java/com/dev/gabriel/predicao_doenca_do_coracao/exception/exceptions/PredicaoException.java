package com.dev.gabriel.predicao_doenca_do_coracao.exception.exceptions;

import org.springframework.http.HttpStatus;

public class PredicaoException extends GenericaException {
  private static final HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
  private static final String ERRO = "Erro no processo de predição";

  public PredicaoException(String detalhe) {
    super(STATUS, ERRO, detalhe);
  }
}
