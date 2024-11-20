package com.dev.gabriel.autenticacao.repository;

import com.dev.gabriel.autenticacao.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
  boolean existsByEmail(String email);

  Optional<Usuario> findByEmail(String email);
}
