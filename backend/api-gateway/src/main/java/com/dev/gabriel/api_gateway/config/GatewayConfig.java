package com.dev.gabriel.api_gateway.config;

import com.dev.gabriel.api_gateway.security.filter.JwtFiltro;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {
  private final JwtFiltro jwtFiltro;

  private RouteLocatorBuilder.Builder configurarRota(
      RouteLocatorBuilder.Builder builder, String path, String roleExigida, String uri) {
    return builder.route(
        routeSpec ->
            routeSpec
                .path(path)
                .filters(f -> f.filter(this.jwtFiltro.apply(new JwtFiltro.Config(roleExigida))))
                .uri(uri));
  }
}
