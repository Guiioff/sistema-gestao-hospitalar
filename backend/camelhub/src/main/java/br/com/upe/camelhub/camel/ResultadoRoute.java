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


	                .end();
	    }
}
