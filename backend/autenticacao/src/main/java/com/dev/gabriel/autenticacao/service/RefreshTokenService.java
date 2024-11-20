package com.dev.gabriel.autenticacao.service;

import com.dev.gabriel.autenticacao.exception.exceptions.NaoEncontradoException;
import com.dev.gabriel.autenticacao.model.entity.RefreshToken;
import com.dev.gabriel.autenticacao.model.entity.Usuario;
import com.dev.gabriel.autenticacao.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
  private final RefreshTokenRepository refreshTokenRepository;

  @Value("${security.refresh-token-duracao-segundos}")
  private Long refreshTokenDuracaoSegundos;

  @Transactional
  public String gerarRefreshToken(Usuario usuario) {
    this.refreshTokenRepository.deleteByUsuario(usuario);

    RefreshToken refreshToken =
        RefreshToken.builder()
            .token(UUID.randomUUID().toString())
            .usuario(usuario)
            .expiracao(Instant.now().plusSeconds(refreshTokenDuracaoSegundos))
            .build();

    this.refreshTokenRepository.save(refreshToken);
    return refreshToken.getToken();
  }

  @Transactional
  public boolean validarRefreshToken(String token) {
    RefreshToken refreshToken =
        this.refreshTokenRepository
            .findByToken(token)
            .orElseThrow(
                () ->
                    new NaoEncontradoException(
                        "Refresh token não encontrado, realize o login novamente"));

    if (refreshToken.getExpiracao().isBefore(Instant.now())) {
      this.refreshTokenRepository.delete(refreshToken);
      return false;
    }
    return true;
  }
}
