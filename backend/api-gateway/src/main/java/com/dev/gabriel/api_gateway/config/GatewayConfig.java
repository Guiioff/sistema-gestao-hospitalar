package com.dev.gabriel.api_gateway.config;

import com.dev.gabriel.api_gateway.security.filter.JwtFiltro;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {
    private final JwtFiltro jwtFiltro;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(
                        "fazer-agendamento-route",
                        r ->
                                r.path("/agendamentos")
                                        .and().method(HttpMethod.POST)
                                        .filters(
                                                f ->
                                                        f.filter(this.jwtFiltro.apply(new JwtFiltro.Config(List.of("MEDICO")))))
                                        .uri("lb://GESTAO-AGENDAMENTOS"))
                .build();
    }
}
