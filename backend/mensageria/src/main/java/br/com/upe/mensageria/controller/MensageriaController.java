package br.com.upe.mensageria.controller;

import br.com.upe.mensageria.controller.dto.ResultadoRequest;
import br.com.upe.mensageria.service.MensageriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/mensageria")
public class MensageriaController {

    @Autowired
    private MensageriaService mensageriaService;

    @PostMapping("/enviarResultado")
    public void enviarResultado(@RequestBody ResultadoRequest resultado) {
        mensageriaService.enviarResultadoParaMedico(resultado.corpoMensagem(), resultado.medicoEmail());
    }
}
