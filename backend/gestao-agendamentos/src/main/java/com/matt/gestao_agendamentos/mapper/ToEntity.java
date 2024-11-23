package com.matt.gestao_agendamentos.mapper;

import com.matt.gestao_agendamentos.controller.dto.AgendamentoDTO;
import com.matt.gestao_agendamentos.model.Agendamento;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ToEntity {

    public static Agendamento toEntity(AgendamentoDTO agendamentoDTO) {
        Agendamento novoAgendamento = new Agendamento();
        novoAgendamento.setPacienteId(agendamentoDTO.pacienteId());
        novoAgendamento.setMedicoId(agendamentoDTO.medicoId());
        novoAgendamento.setDataConsulta(agendamentoDTO.dataConsulta());

        return novoAgendamento;
    }
}
