package br.com.upe.camelhub.camel;

import org.springframework.stereotype.Component;
import org.apache.camel.builder.RouteBuilder;

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
	            .unmarshal().json()
	            .log("Resultado recebido e processado: ${body}")
	            
	  
	            .setHeader("medicoId", simple("${body[medicoId]}"))  
	            .to("http://localhost:8080/medicos/${header.medicoId}")
	            .unmarshal().json()
	            .log("Nome e e-mail do médico recuperados: ${body[nome]}, ${body[email]}")
	            
	 
	            .setHeader("medicoNome", simple("${body[nome]}"))
	            .setHeader("medicoEmail", simple("${body[email]}"))
	            
	  
	            .setHeader("pacienteId", simple("${body[pacienteId]}"))
	            .to("http://localhost:8080/pacientes/${header.pacienteId}")  
	            .unmarshal().json() 
	            .log("Nome do paciente recuperado: ${body[nome]}")


	            .setHeader("pacienteNome", simple("${body[nome]}"))

	       
	            .setBody(simple("Olá, Dr. ${header.medicoNome}, o resultado do paciente ${header.pacienteNome} está disponível. "
	                            + "O tipo de exame ${body[tipoExame]} detectou que o resultado é ${body[resultado]}."))

	 
	            .to("http://localhost:8080/mensageria/enviarResultado?corpoMensagem=${body}&medicoEmail=${header.medicoEmail}");

	    }
}
