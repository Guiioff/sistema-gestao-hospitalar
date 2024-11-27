package com.dev.gabriel.api_gateway.security.filter;

import com.dev.gabriel.api_gateway.exception.exceptions.TokenException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.Instant;
import java.util.List;

@Component
public class JwtFiltro extends AbstractGatewayFilterFactory<JwtFiltro.Config> {
  private final JwtDecoder jwtDecoder;

  public JwtFiltro(JwtDecoder jwtDecoder) {
    super(Config.class);
    this.jwtDecoder = jwtDecoder;
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      try {
        String token = extrairToken(exchange);
        validarToken(token, config.rolesExigidas);
        return chain.filter(exchange);
      } catch (TokenException ex) {
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        return exchange.getResponse().setComplete();
      }
    };
  }

  private String extrairToken(ServerWebExchange exchange) {
    String cabecalhoAuth = exchange.getRequest().getHeaders().getFirst("Authorization");
    if (cabecalhoAuth != null && cabecalhoAuth.startsWith("Bearer ")) {
      return cabecalhoAuth.substring(7);
    } else {
      throw new TokenException("Token de autenticação inválido ou inexistente");
    }
  }

  private void validarToken(String token, List<String> rolesExigidas) {
    try {
      Jwt jwt = this.jwtDecoder.decode(token);
      verificarExpiracao(jwt);
      validarRole(jwt, rolesExigidas);
    } catch (JwtException e) {
      throw new TokenException("Erro ao validar o token de autenticação: " + e.getMessage());
    }
  }

  private void verificarExpiracao(Jwt jwt) {
    if (jwt.getExpiresAt() == null || Instant.now().isAfter(jwt.getExpiresAt())) {
      throw new TokenException("O token de autenticação está expirado");
    }
  }

  private void validarRole(Jwt jwt, List<String> rolesExigidas) {
    String rolesUsuario = jwt.getClaim("role");
    if (rolesUsuario == null || rolesUsuario.isEmpty()) {
      throw new TokenException("Role do usuário não foi encontrada no token");
    }
    if (rolesExigidas.contains(rolesUsuario)) {
      throw new TokenException("Usuário não possui a role exigida para acessar este recurso");
    }
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class Config {
    private List<String> rolesExigidas;
  }
}
