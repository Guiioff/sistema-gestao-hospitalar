package com.matt.gestao_agendamentos.controller;

import com.matt.gestao_agendamentos.controller.dto.AgendamentoDTO;
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

    @PostMapping()
    public ResponseEntity<Long> fazerAgendamento(@RequestBody AgendamentoDTO agendamentoDTO) {
        Long idAgendamento = this.agendamentoService.fazerAgendamento(agendamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(idAgendamento);
    }
}
