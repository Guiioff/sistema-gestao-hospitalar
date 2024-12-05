package com.matt.gestao_agendamentos.service;

import com.matt.gestao_agendamentos.controller.dto.AgendamentoDTO;
import com.matt.gestao_agendamentos.controller.dto.CriarExameDTO;
import com.matt.gestao_agendamentos.model.Agendamento;
import com.matt.gestao_agendamentos.model.Exame;
import com.matt.gestao_agendamentos.producer.AgendamentoProducer;
import com.matt.gestao_agendamentos.producer.message.CriarAgendamentoMessage;
import com.matt.gestao_agendamentos.producer.message.CriarExameMessage;
import com.matt.gestao_agendamentos.repository.AgendamentoRepository;
import com.matt.gestao_agendamentos.repository.ExameRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.matt.gestao_agendamentos.mapper.ToEntity.toEntity;
import static com.matt.gestao_agendamentos.mapper.ToEntityExame.toEntity;
import static com.matt.gestao_agendamentos.mapper.ToMessage.toMessage;
import static com.matt.gestao_agendamentos.mapper.ToExameMessage.toMessage;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ExameRepository exameRepository;
    private final AgendamentoProducer producer;

    @Transactional
    public Long fazerAgendamento(AgendamentoDTO agendamentoDTO) {
        Agendamento novoAgendamento = toEntity(agendamentoDTO);
        agendamentoRepository.save(novoAgendamento);

        CriarAgendamentoMessage message = toMessage(novoAgendamento);
        producer.enviarDadosParaFila(message);

        return novoAgendamento.getId();
    }

    @Transactional
    public Long fazerExame(CriarExameDTO exameDTO) {
        Exame exame = toEntity(exameDTO);
        exameRepository.save(exame);

        CriarExameMessage message = toMessage(exame);
        producer.enviarDadosParaFila(message);

        return exame.getConsultaId();
    }
}
