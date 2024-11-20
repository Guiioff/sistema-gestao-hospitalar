package com.dev.gabriel.autenticacao.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record ErroResponse(Integer status, String erro, List<String> detalhe, Instant timestamp) {}
