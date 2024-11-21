package com.dev.gabriel.api_gateway.exception.handlers;

import com.dev.gabriel.api_gateway.dto.response.ErroResponse;
import com.dev.gabriel.api_gateway.exception.exceptions.GenericaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
  @ExceptionHandler(GenericaException.class)
  public ResponseEntity<ErroResponse> handleGenericaException(GenericaException ex) {
    ErroResponse response =
        ErroResponse.builder()
            .status(ex.getStatus().value())
            .erro(ex.getErro())
            .detalhe(List.of(ex.getDetalhe()))
            .timestamp(Instant.now())
            .build();

    log.error(ex.getErro() + ". Detalhes: {}", response.detalhe());
    return ResponseEntity.status(ex.getStatus()).body(response);
  }
}
