package com.integracaodesistemas.gerenciadeusuario.producer.dto;

public record NovoUsuarioCadastradoMensagem(String id, String email, String senha, String role) {}
