package com.integracaodesistemas.gerenciadeusuario.producer;

import com.integracaodesistemas.gerenciadeusuario.producer.dto.NovoUsuarioCadastradoMensagem;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovoUsuarioProducer {
  private final RabbitTemplate rabbitTemplate;

  @Value("${rabbitmq.exchange.novo-usuario}")
  private String exchange;

  @Value("${rabbitmq.queue.novo-usuario}")
  private String routingKey;

  public void enviarMensagemNovoUsuario(NovoUsuarioCadastradoMensagem mensagem) {
    this.rabbitTemplate.convertAndSend(exchange, routingKey, mensagem);
  }
}
