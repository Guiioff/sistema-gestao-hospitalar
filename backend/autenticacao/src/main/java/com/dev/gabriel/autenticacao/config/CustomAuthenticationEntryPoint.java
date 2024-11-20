package com.dev.gabriel.autenticacao.config;

import com.dev.gabriel.autenticacao.dto.response.ErroResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException {

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    ErroResponse erroResponse =
        ErroResponse.builder()
            .status(HttpStatus.UNAUTHORIZED.value())
            .erro("Erro de autenticação")
            .detalhe(List.of(authException.getMessage()))
            .timestamp(Instant.now())
            .build();

    String responseBody = new ObjectMapper().writeValueAsString(erroResponse);
    response.getWriter().write(responseBody);
  }
}
