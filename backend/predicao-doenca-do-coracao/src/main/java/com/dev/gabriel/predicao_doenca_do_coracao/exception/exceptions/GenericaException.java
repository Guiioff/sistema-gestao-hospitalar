package com.dev.gabriel.predicao_doenca_do_coracao.exception.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GenericaException extends RuntimeException {
  private final HttpStatus status;
  private final String erro;
  private final String detalhe;

  public GenericaException(HttpStatus status, String erro, String detalhe) {
    super(detalhe);
    this.status = status;
    this.erro = erro;
    this.detalhe = detalhe;
  }

  public GenericaException(String detalhe) {
    super(detalhe);
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    this.erro = "Erro interno no sistema";
    this.detalhe = detalhe;
  }
}
