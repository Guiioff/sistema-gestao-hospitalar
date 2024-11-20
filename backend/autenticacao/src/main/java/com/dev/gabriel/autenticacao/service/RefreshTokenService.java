package com.dev.gabriel.autenticacao.service;

import com.dev.gabriel.autenticacao.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
  private final RefreshTokenRepository refreshTokenRepository;

  @Value("${security.refresh-token-duracao-segundos}")
  private Long refreshTokenDuracaoSegundos;
}
