package br.com.upe.camelhub.camel;

import br.com.upe.camelhub.camel.processor.RemoveDadosProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

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

                .setHeader("pacienteId", simple("${body[pacienteId]}"))

                .choice()
                    .when(simple("${body[tipoExame]} == 'PredicaoAtaqueCardiaco'"))
                        .log("Exame de Predição de Ataque Cardíaco")
                        .process(new RemoveDadosProcessor())
                        .log("Novo JSON: ${body}")

                        .setHeader("Content-Type", constant("application/json"))
                        .toD(urlCoracao + "/api/coracao/predict")
                        .log("Resposta do serviço (Ataque Cardíaco): ${body}")

                        .unmarshal().json()
                        .setBody(simple("{\"resposta\": ${body[predicao]}, \"pacienteId\": \"${header.pacienteId}\"}"))
                        .log("Novo JSON com resposta e pacienteId: ${body}")

                    .when(simple("${body[tipoExame]} == 'PredicaoDiabetes'"))
                        .log("Exame de Predição de Diabetes")
                        .process(new RemoveDadosProcessor())
                        .log("Novo JSON: ${body}")

                        .setHeader("Content-Type", constant("application/json"))
                        .toD(urlDiabetes + "/predict")
                        .log("Resposta do serviço (Diabetes): ${body}")

                        .unmarshal().json()
                        .setBody(simple("{\"resposta\": ${body[prediction]}, \"pacienteId\": \"${header.pacienteId}\"}"))
                        .log("Novo JSON com resposta e pacienteId: ${body}")

                    .otherwise()
                        .log("Exame desconhecido")

                .end();
    }
}
