package com.dev.gabriel.predicao_doenca_do_coracao.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record ErroResponse(Integer status, String erro, List<String> detalhe, Instant timestamp) {}
