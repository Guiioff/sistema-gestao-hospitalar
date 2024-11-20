package com.dev.gabriel.autenticacao.dto.request;

import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequest(
    @NotNull(message = "O refresh token fornecido não pode ser nulo ou estar em branco")
        String refreshToken) {}
