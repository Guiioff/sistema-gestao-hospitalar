package br.com.upe.gestaoconsultasexames.listener;

import br.com.upe.gestaoconsultasexames.config.RabbitMQConfig;
import br.com.upe.gestaoconsultasexames.listener.message.CriarConsultaMessage;
import br.com.upe.gestaoconsultasexames.service.iConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultaListener {

    private final iConsultaService consultaService;

    @RabbitListener(queues = RabbitMQConfig.CONSULTA_QUEUE)
    public void receberMensagem(CriarConsultaMessage message) {
        try {
            Long consultaID = consultaService.criarConsulta(
                    message.pacienteId(),
                    message.medicoId(),
                    message.dataConsulta()
            );
            System.out.println("Consulta criada com sucesso! - ID da Consulta: " + consultaID);
        } catch (Exception e) {
            System.err.println("Erro ao processar a mensagem: " + e.getMessage());
        }
    }


}
