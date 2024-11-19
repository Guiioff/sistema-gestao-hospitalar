package com.dev.gabriel.autenticacao.exception.exceptions;

import org.springframework.http.HttpStatus;

public class DadoInvalidoException extends GenericaException {
  private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
  private static final String ERRO = "Dado em formato inválido";

  public DadoInvalidoException(String detalhe) {
    super(STATUS, ERRO, detalhe);
  }
}
