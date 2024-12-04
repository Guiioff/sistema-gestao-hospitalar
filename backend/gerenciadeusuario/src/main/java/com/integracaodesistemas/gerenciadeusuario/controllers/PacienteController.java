package com.integracaodesistemas.gerenciadeusuario.controllers;

import com.integracaodesistemas.gerenciadeusuario.entities.Paciente;
import com.integracaodesistemas.gerenciadeusuario.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> listarPacientes(){
        return pacienteService.listarPacientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id){
        return pacienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Paciente> buscarPacientePorCpf(@PathVariable String cpf){
        return pacienteService.buscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Paciente adicionarPaciente(@RequestBody Paciente paciente){
        return pacienteService.salvarPaciente(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> atualizarPaciente(@PathVariable Long id, @RequestBody Paciente pacienteAtualizado) {
        return ResponseEntity.ok(pacienteService.atualizarPaciente(id, pacienteAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id){
        pacienteService.deletarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
