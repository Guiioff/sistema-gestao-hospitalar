package com.dev.gabriel.autenticacao.consumer;

import com.dev.gabriel.autenticacao.dto.request.RegistrarUsuarioMensagem;
import com.dev.gabriel.autenticacao.service.UsuarioService;
import com.rabbitmq.client.Channel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQConsumer {
  private final UsuarioService usuarioService;
  private final Validator validator;

  @RabbitListener(queues = "${rabbitmq_novo_usuario_queue}")
  public void registrarUsuarioListener(
      RegistrarUsuarioMensagem mensagem,
      Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
    try {
      Set<ConstraintViolation<RegistrarUsuarioMensagem>> violations = validator.validate(mensagem);
      if (!violations.isEmpty()) {
        logarViolacoes(violations);
        rejeitarMensagem(channel, deliveryTag);
        return;
      }

      this.usuarioService.registrarNovoUsuario(mensagem);
      channel.basicAck(deliveryTag, false);
    } catch (Exception e) {
      log.error("Erro no processamento da mensagem: {}", e.getMessage());
      rejeitarMensagem(channel, deliveryTag);
    }
  }

  private void logarViolacoes(Set<ConstraintViolation<RegistrarUsuarioMensagem>> violations) {
    violations.forEach(
        violation ->
            log.error(
                "Erro de validação: {} - valor inválido: {}",
                violation.getMessage(),
                violation.getInvalidValue()));
  }

  private void rejeitarMensagem(Channel channel, Long deliveryTag) {
    try {
      channel.basicReject(deliveryTag, false);
    } catch (IOException ex) {
      log.error("Erro ao rejeitar a mensagem: {}", ex.getMessage());
    }
  }
}
