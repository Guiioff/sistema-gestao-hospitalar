package com.dev.gabriel.api_gateway.security.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class JwtFiltro extends AbstractGatewayFilterFactory<JwtFiltro.Config> {

  public JwtFiltro() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return null;
  }

  public static class Config {}
}
