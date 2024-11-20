package com.dev.gabriel.autenticacao.model.entity;

import java.time.Instant;
import java.util.UUID;

public class RefreshToken {
  private UUID id;
  private String token;
  private Usuario usuario;
  private Instant expiracao;
}
