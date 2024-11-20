package com.dev.gabriel.autenticacao.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistrarUsuarioMensagem(
    @NotBlank(message = "O ID fornecido não pode ser nulo ou estar em branco") String id,
    @NotBlank(message = "O email fornecido não pode ser nulo ou estar em branco")
        @Email(message = "O email fornecido tem um formato inválido")
        String email,
    @NotBlank(message = "A senha fornecida não pode ser nula ou estar em branco") String senha,
    @NotBlank(message = "A role fornecida não pode ser nula ou estar em branco") String role) {}
