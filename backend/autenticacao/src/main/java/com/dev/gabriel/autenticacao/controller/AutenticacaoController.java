package com.dev.gabriel.autenticacao.controller;

import com.dev.gabriel.autenticacao.dto.request.LoginRequest;
import com.dev.gabriel.autenticacao.dto.response.LoginResponse;
import com.dev.gabriel.autenticacao.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacaoController {
  private final LoginService loginService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest dto) {
    LoginResponse response = this.loginService.login(dto);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
