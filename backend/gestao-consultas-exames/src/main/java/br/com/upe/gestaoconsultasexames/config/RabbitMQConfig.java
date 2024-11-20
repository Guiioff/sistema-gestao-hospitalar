package br.com.upe.gestaoconsultasexames.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String CONSULTA_QUEUE = "consulta-queue";
    public static final String EXAME_QUEUE = "exame-queue";
    public static final String EXAME_DADOS_QUEUE = "exame-dados-queue";
    public static final String EXCHANGE_NAME = "gestao-consultas-exames-exchange";

    // Routing Keys
    public static final String CONSULTA_ROUTING_KEY = "consulta.routing.key";
    public static final String EXAME_ROUTING_KEY = "exame.routing.key";
    public static final String EXAME_DADOS_ROUTING_KEY = "exame.dados.routing.key";

    // Queues
    @Bean
    public Queue consultaQueue() {
        return new Queue(CONSULTA_QUEUE, true);
    }

    @Bean
    public Queue exameQueue() {
        return new Queue(EXAME_QUEUE, true);
    }

    @Bean
    public Queue exameDadosQueue() {
        return new Queue(EXAME_DADOS_QUEUE, true);
    }

    // Direct Exchange
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    // Bindings
    @Bean
    public Binding consultaBinding(Queue consultaQueue, DirectExchange exchange) {
        return BindingBuilder.bind(consultaQueue).to(exchange).with(CONSULTA_ROUTING_KEY);
    }

    @Bean
    public Binding exameBinding(Queue exameQueue, DirectExchange exchange) {
        return BindingBuilder.bind(exameQueue).to(exchange).with(EXAME_ROUTING_KEY);
    }

    @Bean
    public Binding exameDadosBinding(Queue exameDadosQueue, DirectExchange exchange) {
        return BindingBuilder.bind(exameDadosQueue).to(exchange).with(EXAME_DADOS_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
