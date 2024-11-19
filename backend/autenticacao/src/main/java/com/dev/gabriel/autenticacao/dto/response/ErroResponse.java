package com.dev.gabriel.autenticacao.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ErroResponse(Integer status, String erro, String detalhe, Instant timestamp) {}
