package com.integracaodesistemas.gerenciadeusuario.services;

import com.integracaodesistemas.gerenciadeusuario.entities.Paciente;
import com.integracaodesistemas.gerenciadeusuario.producer.NovoUsuarioProducer;
import com.integracaodesistemas.gerenciadeusuario.producer.dto.NovoUsuarioCadastradoMensagem;
import com.integracaodesistemas.gerenciadeusuario.repositories.PacienteRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
  @Autowired private NovoUsuarioProducer novoUsuarioProducer;

  @Autowired private PacienteRepository pacienteRepository;

  public List<Paciente> listarPacientes() {
    return pacienteRepository.findAll();
  }

  public Optional<Paciente> buscarPorId(Long id) {
    return pacienteRepository.findById(id);
  }

  public Optional<Paciente> buscarPorCpf(Long cpf) {
    return pacienteRepository.findByCpf(cpf);
  }

  public Paciente salvarPaciente(Paciente paciente) {
    Paciente pacienteSalvo = this.pacienteRepository.save(paciente);
    NovoUsuarioCadastradoMensagem mensagem =
        new NovoUsuarioCadastradoMensagem(
            pacienteSalvo.getId().toString(),
            paciente.getEmail(),
            senhaCriptografada(paciente.getSenha()),
            "PACIENTE");
    this.novoUsuarioProducer.enviarMensagemNovoUsuario(mensagem);
    return pacienteSalvo;
  }

  public Optional<Paciente> atualizarPaciente(Long id, Paciente pacienteAtualizado) {
    return pacienteRepository
        .findById(id)
        .map(
            pacienteExistente -> {
              pacienteExistente.setNome(pacienteAtualizado.getNome());
              pacienteExistente.setCpf(pacienteAtualizado.getCpf());
              pacienteExistente.setDataNascimento(pacienteAtualizado.getDataNascimento());
              pacienteExistente.setEndereco(pacienteAtualizado.getEndereco());
              pacienteExistente.setTelefone(pacienteAtualizado.getTelefone());
              return pacienteRepository.save(pacienteExistente);
            });
  }

  public void deletarPaciente(Long id) {
    pacienteRepository.deleteById(id);
  }

  private static String senhaCriptografada(String senhaRaw) {
    return BCrypt.hashpw(senhaRaw, BCrypt.gensalt());
  }
}
