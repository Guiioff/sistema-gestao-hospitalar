package com.dev.gabriel.autenticacao.model.entity;

import com.dev.gabriel.autenticacao.model.enums.RoleUsuario;

import java.util.UUID;

public class Usuario {
  private UUID id;
  private String email;
  private String senha;
  private RoleUsuario role;
  private Boolean isBlocked;
}
