package br.com.upe.gestaoconsultasexames.controller;

import br.com.upe.gestaoconsultasexames.controller.dto.ExamePredicaoAtaqueCardiacoRequest;
import br.com.upe.gestaoconsultasexames.controller.dto.ExamePredicaoDiabetesRequest;
import br.com.upe.gestaoconsultasexames.service.iExameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/exames")
@RequiredArgsConstructor
public class ExameController {

    private final iExameService exameService;

    @PostMapping("/predicao-ataque-cardiaco")
    @ResponseStatus(HttpStatus.CREATED)
    public void realizarExameAtaqueCardiaco(
            @RequestParam Long exameId,
            @RequestBody ExamePredicaoAtaqueCardiacoRequest exameRequest) {

        Map<String, Object> dados = exameRequest.toMap();
        exameService.realizarExame(exameId, dados, "ExamePredicaoAtaqueCardiaco");
    }

    @PostMapping("/predicao-diabetes")
    @ResponseStatus(HttpStatus.CREATED)
    public void realizarExameDiabetes(
            @RequestParam Long exameId,
            @RequestBody ExamePredicaoDiabetesRequest exameRequest) {

        Map<String, Object> dados = exameRequest.toMap();
        exameService.realizarExame(exameId, dados, "ExamePredicaoDiabetes");
    }
}
