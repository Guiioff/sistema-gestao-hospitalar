package com.dev.gabriel.autenticacao.exception.handler;

import com.dev.gabriel.autenticacao.dto.response.ErroResponse;
import com.dev.gabriel.autenticacao.exception.exceptions.GenericaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErroResponse> handleValidationException(
      MethodArgumentNotValidException ex) {
    List<String> mensagensErro =
        ex.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .toList();

    ErroResponse response =
        ErroResponse.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .erro("Dados de entrada inválidos")
            .detalhe(mensagensErro)
            .timestamp(Instant.now())
            .build();

    log.error("Dados de entrada inválidos. Detalhes: {}", response.detalhe());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

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

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErroResponse> handleUnhandledException(Exception ex) {
    GenericaException genericaException = new GenericaException(ex.getMessage());

    ErroResponse response =
        ErroResponse.builder()
            .status(genericaException.getStatus().value())
            .erro(genericaException.getErro())
            .detalhe(List.of(genericaException.getDetalhe()))
            .timestamp(Instant.now())
            .build();

    log.error("Erro interno do sistema. Detalhes: {}", response.detalhe());
    return ResponseEntity.status(genericaException.getStatus()).body(response);
  }
}
