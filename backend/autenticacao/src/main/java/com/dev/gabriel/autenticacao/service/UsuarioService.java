package com.dev.gabriel.autenticacao.service;

import com.dev.gabriel.autenticacao.dto.request.RegistrarUsuarioMensagem;
import com.dev.gabriel.autenticacao.exception.exceptions.DadoInvalidoException;
import com.dev.gabriel.autenticacao.exception.exceptions.DadoJaRegistradoException;
import com.dev.gabriel.autenticacao.model.entity.Usuario;
import com.dev.gabriel.autenticacao.model.enums.RoleUsuario;
import com.dev.gabriel.autenticacao.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {
  private final UsuarioRepository usuarioRepository;

  @Transactional
  private void registrarNovoUsuario(RegistrarUsuarioMensagem mensagem) {
    if (this.usuarioRepository.existsByEmail(mensagem.email())) {
      throw new DadoJaRegistradoException("Já existe um usuário com o email fornecido");
    }

    UUID usuarioId;

    try {
      usuarioId = UUID.fromString(mensagem.id());
    } catch (IllegalArgumentException ex) {
      throw new DadoInvalidoException("O ID fornecido está em formato inválido");
    }

    if (this.usuarioRepository.existsById(usuarioId)) {
      throw new DadoJaRegistradoException("Já existe um usuário com o ID fornecido");
    }

    RoleUsuario usuarioRole;

    try {
      usuarioRole = RoleUsuario.valueOf(mensagem.role());
    } catch (IllegalArgumentException ex) {
      throw new DadoInvalidoException("A role fornecida é inválida");
    }

    Usuario novoUsuario =
        Usuario.builder()
            .id(usuarioId)
            .email(mensagem.email())
            .senha(mensagem.password())
            .role(usuarioRole)
            .isBlocked(Boolean.FALSE)
            .build();

    this.usuarioRepository.save(novoUsuario);
  }
}
