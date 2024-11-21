package com.dev.gabriel.api_gateway.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record ErroResponse(Integer status, String erro, List<String> detalhe, Instant timestamp) {}
