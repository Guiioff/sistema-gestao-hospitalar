package br.com.upe.resultadosModelos.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String RESULTADOS_QUEUE = "resultados-queue";
    public static final String EXCHANGE_NAME = "resultados-exchange";
    
    public static final String RESULTADOS_ROUTING_KEY = "resultados.routing.key";

    @Bean
    public Queue resultadosQueue() {
        return new Queue(RESULTADOS_QUEUE, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }
    
    @Bean
    public Binding resultadosBinding(Queue resultadosQueue, DirectExchange exchange) {
        return BindingBuilder.bind(resultadosQueue).to(exchange).with(RESULTADOS_ROUTING_KEY);
    }
    
   
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
