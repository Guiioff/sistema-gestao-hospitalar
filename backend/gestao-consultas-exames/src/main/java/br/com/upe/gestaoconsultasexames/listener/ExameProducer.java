package br.com.upe.gestaoconsultasexames.listener;

import br.com.upe.gestaoconsultasexames.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExameProducer {

    public final RabbitTemplate rabbitTemplate;

    public void enviarDadosParaFila(Map<String, Object> dadosExame) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXAME_DADOS_QUEUE, dadosExame);
        System.out.println("Dados enviados para a fila: " + RabbitMQConfig.EXAME_QUEUE);
    }
}
