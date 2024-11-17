package br.com.upe.gestaoconsultasexames.listener;

import br.com.upe.gestaoconsultasexames.config.RabbitMQConfig;
import br.com.upe.gestaoconsultasexames.listener.message.AgendarExameMessage;
import br.com.upe.gestaoconsultasexames.model.Consulta;
import br.com.upe.gestaoconsultasexames.model.Exame;
import br.com.upe.gestaoconsultasexames.service.iConsultaService;
import br.com.upe.gestaoconsultasexames.service.iExameService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExameListener {

    private final iExameService exameService;
    private final iConsultaService consultaService;

    @RabbitListener(queues = RabbitMQConfig.EXAME_QUEUE)
    public void receberMensagem(AgendarExameMessage message) {
        try {
            Consulta consulta = consultaService.buscarConsultaPorId(message.consultaId());
            if (consulta != null) {
                Exame exame = exameService.criarExame(message.tipoExame(), consulta);
                System.out.println("Exame criado com sucesso! Exame ID: " + exame.getId());
            } else {
                System.err.println("Consulta não encontrada! ID da consulta: " + message.consultaId());
            }
        } catch (Exception e) {
            System.err.println("Erro ao processar o agendamento de exame: " + e.getMessage());
        }
    }
}
