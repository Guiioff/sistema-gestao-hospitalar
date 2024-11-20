package com.dev.gabriel.autenticacao.consumer;

import com.dev.gabriel.autenticacao.dto.request.RegistrarUsuarioMensagem;
import com.dev.gabriel.autenticacao.service.UsuarioService;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQConsumer {
  private final UsuarioService usuarioService;

  @RabbitListener(queues = "${rabbitmq_novo_usuario_queue}")
  public void registrarUsuarioListener(
      RegistrarUsuarioMensagem mensagem,
      Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
    try {
      this.usuarioService.registrarNovoUsuario(mensagem);
      channel.basicAck(deliveryTag, false);
    } catch (Exception e) {
      log.error(
          "Erro no processamento da mensagem para cadastro de novo usuário: {}", e.getMessage());

      try {
        channel.basicReject(deliveryTag, false);
      } catch (IOException ioException) {
        log.error("Erro ao processar a rejeição da mensagem: {}", e.getMessage());
      }
    }
  }
}
