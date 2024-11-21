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
      String token = extrairToken(exchange);
      if (!validarRole(token, config.getRoleExigida())) {
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        return exchange.getResponse().setComplete();
      }
      return chain.filter(exchange);
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

  private boolean validarRole(String token, String roleExigida) {
    try {
      Jwt jwt = this.jwtDecoder.decode(token);
      String role = jwt.getClaim("role");
      return roleExigida.equals(role);
    } catch (JwtException ex) {
      throw new TokenException("Erro ao validar a role do usuário");
    }
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class Config {
    private String roleExigida;
  }
}
