package com.dev.gabriel.autenticacao.exception.exceptions;

import org.springframework.http.HttpStatus;

public class DadoJaRegistradoException extends GenericaException {
  private static final HttpStatus STATUS = HttpStatus.CONFLICT;
  private static final String ERRO = "Dado já registrado na base de dados";

  public DadoJaRegistradoException(String detalhe) {
    super(STATUS, ERRO, detalhe);
  }
}
