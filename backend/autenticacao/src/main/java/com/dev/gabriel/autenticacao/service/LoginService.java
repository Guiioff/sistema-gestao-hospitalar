package com.dev.gabriel.autenticacao.service;

import com.dev.gabriel.autenticacao.dto.request.LoginRequest;
import com.dev.gabriel.autenticacao.dto.request.RefreshTokenRequest;
import com.dev.gabriel.autenticacao.dto.response.LoginResponse;
import com.dev.gabriel.autenticacao.exception.exceptions.NaoEncontradoException;
import com.dev.gabriel.autenticacao.model.entity.RefreshToken;
import com.dev.gabriel.autenticacao.model.entity.Usuario;
import com.dev.gabriel.autenticacao.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class LoginService {
  private final JwtEncoder jwtEncoder;
  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;
  private final RefreshTokenService refreshTokenService;

  @Value("${security.jwt.token-issuer}")
  private String tokenIssuer;

  @Value("${security.jwt.duracao-token-segundos}")
  private Long duracaoTokenSegundos;

  public LoginResponse login(LoginRequest dto) {
    Usuario usuario =
        this.usuarioRepository
            .findByEmail(dto.email())
            .orElseThrow(() -> new NaoEncontradoException("Usuário não encontrado"));

    if (Boolean.TRUE.equals(usuario.getIsBlocked())) {
      throw new LockedException("O acesso está bloqueado, procure um administrador do sistema");
    }

    if (!this.passwordEncoder.matches(dto.senha(), usuario.getSenha())) {
      throw new BadCredentialsException("A senha está incorreta, tente novamente");
    }

    String refreshToken = this.refreshTokenService.gerarRefreshToken(usuario);
    Jwt jwt = gerarToken(usuario);

    return new LoginResponse(jwt.getTokenValue(), jwt.getExpiresAt(), refreshToken);
  }

  public LoginResponse refreshToken(RefreshTokenRequest dto) {
    RefreshToken refreshToken = this.refreshTokenService.validarRefreshToken(dto.refreshToken());
    Usuario usuario = refreshToken.getUsuario();

    Jwt jwt = gerarToken(usuario);
    return new LoginResponse(jwt.getTokenValue(), jwt.getExpiresAt(), refreshToken.getToken());
  }

  private Jwt gerarToken(Usuario usuario) {
    JwtClaimsSet claims =
        JwtClaimsSet.builder()
            .issuer(this.tokenIssuer)
            .subject(usuario.getEmail())
            .expiresAt(Instant.now().plusSeconds(this.duracaoTokenSegundos))
            .issuedAt(Instant.now())
            .claim("role", usuario.getRole())
            .build();

    return this.jwtEncoder.encode(JwtEncoderParameters.from(claims));
  }
}
