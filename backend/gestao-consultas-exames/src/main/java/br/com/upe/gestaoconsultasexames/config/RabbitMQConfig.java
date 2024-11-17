package br.com.upe.gestaoconsultasexames.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String CONSULTA_QUEUE = "consulta-queue";
    public static final String EXAME_QUEUE = "exame-queue";

    @Bean
    public Queue consultaQueue() {
        return new Queue(CONSULTA_QUEUE, true);
    }

    @Bean
    public Queue exameQueue() {
        return new Queue(EXAME_QUEUE, true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
