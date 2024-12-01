package com.dev.gabriel.api_gateway.security.filter;

import com.dev.gabriel.api_gateway.dto.response.ErroResponse;
import com.dev.gabriel.api_gateway.exception.exceptions.GenericaException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
@Order(SecurityProperties.BASIC_AUTH_ORDER - 1)
public class JwtFiltro extends AbstractGatewayFilterFactory<JwtFiltro.Config> {
  private final JwtDecoder jwtDecoder;
  private final ObjectMapper objectMapper;

  public JwtFiltro(JwtDecoder jwtDecoder, ObjectMapper objectMapper) {
    super(Config.class);
    this.jwtDecoder = jwtDecoder;
    this.objectMapper = objectMapper;
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      try {
        String token = extrairToken(exchange);
        validarToken(token, config.rolesExigidas);
        return chain.filter(exchange);
      } catch (AccessDeniedException ex) {
        return handleException(
            exchange, HttpStatus.FORBIDDEN, "Erro de autorização", ex.getMessage());
      } catch (InsufficientAuthenticationException | BadCredentialsException ex) {
        return handleException(
            exchange, HttpStatus.UNAUTHORIZED, "Erro de autenticação", ex.getMessage());
      } catch (Exception ex) {
        return handleException(
            exchange, HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno", ex.getMessage());
      }
    };
  }

  private String extrairToken(ServerWebExchange exchange) {
    return Optional.of(exchange.getRequest().getHeaders().getFirst("Authorization"))
        .filter(cabecalho -> cabecalho.startsWith("Bearer "))
        .map(cabecalho -> cabecalho.substring(7))
        .orElseThrow(
            () ->
                new InsufficientAuthenticationException(
                    "Token de autenticação inválido ou inexistente"));
  }

  private void validarToken(String token, List<String> rolesExigidas) {
    try {
      Jwt jwt = this.jwtDecoder.decode(token);
      verificarExpiracao(jwt);
      validarRole(jwt, rolesExigidas);
    } catch (JwtException e) {
      throw new BadCredentialsException(
          "Erro ao validar o token de autenticação: " + e.getMessage());
    }
  }

  private void verificarExpiracao(Jwt jwt) {
    if (jwt.getExpiresAt() == null || Instant.now().isAfter(jwt.getExpiresAt())) {
      throw new BadCredentialsException("O token de autenticação está expirado") {};
    }
  }

  private void validarRole(Jwt jwt, List<String> rolesExigidas) {
    String rolesUsuario = jwt.getClaim("role");
    if (rolesUsuario == null || rolesUsuario.isEmpty()) {
      throw new AccessDeniedException("Role do usuário não foi encontrada no token");
    }
    if (!rolesExigidas.contains(rolesUsuario)) {
      throw new AccessDeniedException(
          "Usuário não possui a role exigida para acessar este recurso");
    }
  }

  private Mono<Void> handleException(
      ServerWebExchange exchange, HttpStatus status, String erro, String detalhe) {
    ServerHttpResponse response = exchange.getResponse();
    response.setStatusCode(status);
    response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json");

    ErroResponse erroResponse =
        ErroResponse.builder()
            .status(status.value())
            .erro(erro)
            .detalhe(List.of(detalhe))
            .timestamp(Instant.now())
            .build();

    try {
      byte[] body = this.objectMapper.writeValueAsBytes(erroResponse);
      DataBuffer buffer = response.bufferFactory().wrap(body);
      return response.writeWith(Mono.just(buffer));
    } catch (Exception e) {
      return Mono.error(new GenericaException("Erro ao serializar a resposta de erro"));
    }
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class Config {
    private List<String> rolesExigidas;
  }
}
