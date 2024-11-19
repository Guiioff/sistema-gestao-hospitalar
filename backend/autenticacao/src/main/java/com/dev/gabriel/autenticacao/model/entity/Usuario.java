package com.dev.gabriel.autenticacao.model.entity;

import com.dev.gabriel.autenticacao.model.enums.RoleUsuario;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String senha;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private RoleUsuario role;

  @Column(nullable = false)
  private Boolean isBlocked;
}
