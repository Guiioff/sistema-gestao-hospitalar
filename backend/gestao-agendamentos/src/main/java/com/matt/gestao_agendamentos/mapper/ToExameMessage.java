package com.matt.gestao_agendamentos.mapper;

import com.matt.gestao_agendamentos.model.Exame;
import com.matt.gestao_agendamentos.producer.message.CriarExameMessage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ToExameMessage {

    public static CriarExameMessage toMessage(Exame exame) {
        return new CriarExameMessage(
                exame.getConsultaId(),
                exame.getTipoExame()
        );
    }
}
