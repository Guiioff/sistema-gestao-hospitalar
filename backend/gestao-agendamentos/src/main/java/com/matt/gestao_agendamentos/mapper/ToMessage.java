package com.matt.gestao_agendamentos.mapper;

import com.matt.gestao_agendamentos.model.Agendamento;
import com.matt.gestao_agendamentos.producer.message.CriarAgendamentoMessage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ToMessage {

    public static CriarAgendamentoMessage toMessage(Agendamento agendamento) {
        return new CriarAgendamentoMessage(
                agendamento.getPacienteId(),
                agendamento.getMedicoId(),
                agendamento.getDataConsulta()
        );
    }
}
