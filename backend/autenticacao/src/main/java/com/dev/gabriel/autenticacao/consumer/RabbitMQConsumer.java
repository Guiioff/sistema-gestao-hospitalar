package com.dev.gabriel.autenticacao.consumer;

import com.dev.gabriel.autenticacao.dto.request.RegistrarUsuarioMensagem;
import com.dev.gabriel.autenticacao.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQConsumer {
  private final UsuarioService usuarioService;

  @RabbitListener(queues = "${rabbitmq_novo_usuario_queue}")
  public void registrarUsuarioListener(RegistrarUsuarioMensagem mensagem) {
    this.usuarioService.registrarNovoUsuario(mensagem);
  }
}
