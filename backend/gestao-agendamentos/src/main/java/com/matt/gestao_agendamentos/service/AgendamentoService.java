package com.matt.gestao_agendamentos.service;

import com.matt.gestao_agendamentos.controller.dto.AgendamentoDTO;
import com.matt.gestao_agendamentos.model.Agendamento;
import com.matt.gestao_agendamentos.producer.AgendamentoProducer;
import com.matt.gestao_agendamentos.producer.message.CriarAgendamentoMessage;
import com.matt.gestao_agendamentos.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.matt.gestao_agendamentos.mapper.ToEntity.toEntity;
import static com.matt.gestao_agendamentos.mapper.ToMessage.toMessage;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final AgendamentoProducer producer;

    public Long fazerAgendamento(AgendamentoDTO agendamentoDTO) {
        Agendamento novoAgendamento = toEntity(agendamentoDTO);
        agendamentoRepository.save(novoAgendamento);

        CriarAgendamentoMessage message = toMessage(novoAgendamento);
        producer.enviarDadosParaFila(message);

        return novoAgendamento.getId();
    }
}
