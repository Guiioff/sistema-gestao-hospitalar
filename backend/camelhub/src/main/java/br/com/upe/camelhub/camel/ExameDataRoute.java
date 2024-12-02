package br.com.upe.camelhub.camel;

import br.com.upe.camelhub.camel.processor.RemoveTipoExameProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExameDataRoute extends RouteBuilder {

    @Value("${url.predicao.coracao}")
    private String urlCoracao;

    @Value("${url.predicao.diabetes}")
    private String urlDiabetes;

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
                        .process(new RemoveTipoExameProcessor())
                        .log("Novo JSON: ${body}")

                    .when(simple("${body[tipoExame]} == 'PredicaoDiabetes'"))
                        .log("Exame de Predição de Diabetes")
                        .process(new RemoveTipoExameProcessor())
                        .log("Novo JSON: ${body}")

                    .otherwise()
                        .log("Exame desconhecido")
                .end();
    }
}
