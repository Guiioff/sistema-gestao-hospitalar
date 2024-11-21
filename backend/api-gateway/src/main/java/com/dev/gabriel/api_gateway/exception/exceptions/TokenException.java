package com.dev.gabriel.api_gateway.exception.exceptions;

import org.springframework.http.HttpStatus;

public class TokenException extends GenericaException {
  private static final HttpStatus STATUS = HttpStatus.UNAUTHORIZED;
  private static final String ERRO = "Erro no token de autenticação";

  public TokenException(String detalhe) {
    super(STATUS, ERRO, detalhe);
  }
}
