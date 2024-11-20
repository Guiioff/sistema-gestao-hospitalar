package com.dev.gabriel.predicao_doenca_do_coracao.controller;

import com.dev.gabriel.predicao_doenca_do_coracao.dto.request.PredicaoRequest;
import com.dev.gabriel.predicao_doenca_do_coracao.dto.response.PredicaoResponse;
import com.dev.gabriel.predicao_doenca_do_coracao.service.PredicaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coracao/")
@RequiredArgsConstructor
public class PredicaoController {
  private final PredicaoService predicaoService;

  @PostMapping("/predict")
  public ResponseEntity<PredicaoResponse> predict(@RequestBody @Valid PredicaoRequest request) {
    PredicaoResponse response = new PredicaoResponse(this.predicaoService.predict(request));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
