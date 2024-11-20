package com.dev.gabriel.autenticacao.exception.handler;

import com.dev.gabriel.autenticacao.dto.response.ErroResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityExceptionHandler {

  @ExceptionHandler(LockedException.class)
  public ResponseEntity<ErroResponse> handleLockedException(LockedException ex) {
    ErroResponse response =
        ErroResponse.builder()
            .status(HttpStatus.UNAUTHORIZED.value())
            .erro("Usuário bloqueado")
            .detalhe(ex.getMessage())
            .build();

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
  }
}
