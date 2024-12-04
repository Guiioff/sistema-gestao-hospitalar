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

                .route(
                        "fazer-exame-ataque-cardiaco-route",
                        r ->
                                r.path("/api/exames/predicao-ataque-cardiaco")
                                .and().method(HttpMethod.POST)
                                        .filters(
                                                f -> f.filter(
                                                        (exchange, chain) -> {
                                                            System.out.println("Rota 'fazer-exame-ataque-cardiaco-route' foi acessada!");
                                                            return chain.filter(exchange);
                                                        }
                                                )
                                        )
                                .uri("lb://GESTAO-CONSULTAS-EXAMES")
                )

                .route(
                        "fazer-exame-diabetes-route",
                        r ->
                                r.path("/api/exames/predicao-diabetes")
                                .and().method(HttpMethod.POST)
                                .filters(
                                        f ->
                                                f.filter(this.jwtFiltro.apply(new JwtFiltro.Config(List.of("PACIENTE"))))
                                )
                                .uri("lb://GESTAO-CONSULTAS-EXAMES")
                )
                .build();
    }
}
