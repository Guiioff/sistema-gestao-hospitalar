package com.dev.gabriel.autenticacao.service;

import com.dev.gabriel.autenticacao.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
  private final JwtEncoder jwtEncoder;
  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${security.jwt.token-issuer}")
  private String tokenIssuer;

  @Value("${security.jwt.duracao-token-segundos}")
  private String duracaoTokenSegundos;
}
