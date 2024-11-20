package com.dev.gabriel.autenticacao.exception.exceptions;

import org.springframework.http.HttpStatus;

public class NaoEncontradoException extends GenericaException {
  private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
  private static final String ERRO = "Recurso não encontrado";

  public NaoEncontradoException(String detalhe) {
    super(STATUS, ERRO, detalhe);
  }
}
