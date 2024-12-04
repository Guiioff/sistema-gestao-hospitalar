package com.integracaodesistemas.gerenciadeusuario.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
  @Value("${rabbitmq.queue.novo-usuario}")
  private String novoUsuarioNomeQueue;

  @Value("${rabbitmq.queue.novo-usuario-dlq}")
  private String novoUsuarioNomeDLQ;

  @Value("${rabbitmq.exchange.novo-usuario}")
  private String nomeExchange;

  @Bean
  public Queue novoUsuarioQueue() {
    return QueueBuilder.durable(novoUsuarioNomeQueue)
        .withArgument("x-dead-letter-exchange", nomeExchange)
        .withArgument("x-dead-letter-routing-key", novoUsuarioNomeDLQ)
        .build();
  }

  @Bean
  public Queue deadLetterQueue() {
    return new Queue(novoUsuarioNomeDLQ, true);
  }

  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange(nomeExchange);
  }

  @Bean
  public Binding novoUsuarioBinding() {
    return BindingBuilder.bind(novoUsuarioQueue()).to(directExchange()).with(novoUsuarioNomeQueue);
  }

  @Bean
  public Binding dlqBinding() {
    return BindingBuilder.bind(deadLetterQueue()).to(directExchange()).with(novoUsuarioNomeDLQ);
  }

  @Bean
  public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
