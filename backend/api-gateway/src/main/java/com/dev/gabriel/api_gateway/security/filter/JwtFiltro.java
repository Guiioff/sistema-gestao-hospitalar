package com.dev.gabriel.api_gateway.security.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class JwtFiltro extends AbstractGatewayFilterFactory<JwtFiltro.Config> {

  public JwtFiltro() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return null;
  }

  private String extrairToken(ServerWebExchange exchange) {
    String cabecalhoAuth = exchange.getRequest().getHeaders().getFirst("Authorization");
    if (cabecalhoAuth != null && cabecalhoAuth.startsWith("Bearer ")) {
      return cabecalhoAuth.substring(7);
    } else {
      throw new RuntimeException("Token de autenticação inválido ou inexistente");
    }
  }

  public static class Config {}
}
