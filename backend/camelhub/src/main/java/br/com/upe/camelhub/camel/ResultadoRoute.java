package br.com.upe.camelhub.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ResultadoRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("spring-rabbitmq:resultados-exchange"
				+ "?queues=resultados-queue"
				+ "&routingKey=resultados.routing.key"
				+ "&exchangeType=direct"
				+ "&autoDeclare=true"
				+ "&arg.queue.durable=true")

				.routeId("rabbitmq-resultados-consumer-route")
				.log("Resultado recebido e processado: ${body}")

				.setHeader("medicoId", jsonpath("$.medicoId"))
				.setHeader("pacienteId", jsonpath("$.pacienteId"))
				.setHeader("tipoExame", jsonpath("$.tipoExame"))
				.setHeader("resultado", jsonpath("$.resultado"))

				.log("HEADER MEDICO: ${header.medicoId}")

				.setBody(constant(""))

				.toD("http://localhost:8084/medicos/crm/${header.medicoId}?httpMethod=GET")
				.unmarshal().json()
				.log("Nome e e-mail do médico recuperados: ${body[nome]}, ${body[email]}")

				.setHeader("medicoNome", simple("${body[nome]}"))
				.setHeader("medicoEmail", simple("${body[email]}"))

				.setBody(constant("")) // Limpa o body novamente

				.toD("http://localhost:8084/pacientes/cpf/${header.pacienteId}?httpMethod=GET")
				.unmarshal().json()
				.log("Nome do paciente recuperado: ${body[nome]}")

				.setHeader("pacienteNome", simple("${body[nome]}"))

				.setBody(simple("Olá, Dr. ${header.medicoNome}, o resultado do paciente ${header.pacienteNome} está disponível. "
						+ "O tipo de exame ${header.tipoExame} detectou que o resultado é ${header.resultado}."))

				.log("Novo JSON: ${body}")
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				.setBody(simple("{\"corpoMensagem\": \"${body}\", \"medicoEmail\": \"${header.medicoEmail}\"}"))
				.toD("http://localhost:8762/api/mensageria/enviarResultado?httpMethod=POST")

				.log("Mensagem enviada com sucesso para ${header.medicoEmail}");
	}
}
