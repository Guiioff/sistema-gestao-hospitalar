package com.dev.gabriel.autenticacao.exception.handler;

import com.dev.gabriel.autenticacao.dto.response.ErroResponse;
import com.dev.gabriel.autenticacao.exception.exceptions.GenericaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<ErroResponse> handleGenericaException(GenericaException ex) {
    ErroResponse response =
        ErroResponse.builder()
            .status(ex.getStatus().value())
            .erro(ex.getErro())
            .detalhe(ex.getDetalhe())
            .timestamp(Instant.now())
            .build();

    return ResponseEntity.status(ex.getStatus()).body(response);
  }
}
