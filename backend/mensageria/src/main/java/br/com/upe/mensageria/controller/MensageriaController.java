package br.com.upe.mensageria.controller;

import br.com.upe.mensageria.service.MensageriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/mensageria")
public class MensageriaController {

    @Autowired
    private MensageriaService mensageriaService;

    @PostMapping("/enviarResultado")
    public void enviarResultado(@RequestParam String corpoMensagem, @RequestParam String medicoEmail) {
        mensageriaService.enviarResultadoParaMedico(corpoMensagem, medicoEmail);
    }
}
