package com.matt.gestao_agendamentos.producer;

import com.matt.gestao_agendamentos.config.RabbitMQConfig;
import com.matt.gestao_agendamentos.producer.message.CriarAgendamentoMessage;
import com.matt.gestao_agendamentos.producer.message.CriarExameMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgendamentoProducer {

    private final RabbitTemplate template;

    public void enviarDadosParaFila(CriarAgendamentoMessage message) {
        template.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.CONSULTA_ROUTING_KEY,
                message
        );
    }

    public void enviarDadosParaFila(CriarExameMessage message) {
        template.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.EXAME_ROUTING_KEY,
                message
        );
    }
}
