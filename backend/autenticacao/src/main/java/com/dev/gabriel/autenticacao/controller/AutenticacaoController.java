package com.dev.gabriel.autenticacao.controller;

import com.dev.gabriel.autenticacao.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacaoController {
  private final LoginService loginService;
}
