package com.matt.gestao_agendamentos.controller;

import com.matt.gestao_agendamentos.controller.dto.AgendamentoDTO;
import com.matt.gestao_agendamentos.controller.dto.CriarExameDTO;
import com.matt.gestao_agendamentos.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping("/agendar-consulta")
    public ResponseEntity<Long> fazerAgendamento(@RequestBody AgendamentoDTO agendamentoDTO) {
        Long idAgendamento = this.agendamentoService.fazerAgendamento(agendamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(idAgendamento);
    }

    @PostMapping("/agendar-exame")
    public ResponseEntity<Long> fazerExame(@RequestBody CriarExameDTO exameDTO) {
        Long idExame = this.agendamentoService.fazerExame(exameDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(idExame);
    }
}
