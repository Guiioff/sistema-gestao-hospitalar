package com.dev.gabriel.autenticacao.repository;

import com.dev.gabriel.autenticacao.model.entity.RefreshToken;
import com.dev.gabriel.autenticacao.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
  void deleteByUsuario(Usuario usuario);
}
