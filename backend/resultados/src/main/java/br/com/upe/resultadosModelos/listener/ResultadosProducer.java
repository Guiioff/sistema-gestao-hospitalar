package br.com.upe.resultadosModelos.listener;

import br.com.upe.resultadosModelos.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResultadosProducer {

    public final RabbitTemplate rabbitTemplate;

    public void enviarResultadoParaFila(Map<String, Object> resultado) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.RESULTADOS_QUEUE, resultado);
        System.out.println("Resultado enviado para a fila: " + RabbitMQConfig.RESULTADOS_QUEUE);
    }
}
