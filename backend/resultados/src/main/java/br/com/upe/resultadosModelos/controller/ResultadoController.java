package br.com.upe.resultadosModelos.controller;

import br.com.upe.resultadosModelos.dto.ResultadoDTO; 
import br.com.upe.resultadosModelos.service.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resultados")
public class ResultadoController {

    @Autowired
    private ResultadoService resultadoService;


    @PostMapping
    public ResponseEntity<?> salvarResultado(@RequestBody ResultadoDTO resultadoDTO) {
        try {
          
            resultadoService.salvarResultado(resultadoDTO);
            return new ResponseEntity<>("Resultado salvo com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
      
            return new ResponseEntity<>("Erro ao salvar o resultado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
