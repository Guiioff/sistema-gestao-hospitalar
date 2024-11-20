package br.com.upe.camelhub.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExameDataRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("spring-rabbitmq:gestao-consultas-exames-exchange"
                + "?queues=exame-dados-queue"
                + "&routingKey=exame.dados.routing.key"
                + "&exchangeType=direct"
                + "&autoDeclare=true"
                + "&arg.queue.durable=true")

                .routeId("rabbitmq-exame-dados-consumer-route")
                .unmarshal().json()

                .choice()
                    .when(simple("${body[tipoExame]} == 'PredicaoAtaqueCardiaco'"))
                        .log("Exame de Predição de Ataque Cardíaco")

                    .when(simple("${body[tipoExame]} == 'PredicaoDiabetes'"))
                        .log("Exame de Predição de Diabetes")

                    .otherwise()
                        .log("Exame desconhecido")
                .end();
    }
}
