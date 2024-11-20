package com.dev.gabriel.autenticacao.dto.response;

import java.time.Instant;

public record LoginResponse(String token, Instant expiracao, String refreshToken) {}
