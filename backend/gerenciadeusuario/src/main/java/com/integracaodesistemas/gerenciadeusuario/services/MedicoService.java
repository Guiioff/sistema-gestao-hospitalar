package com.integracaodesistemas.gerenciadeusuario.services;

import com.integracaodesistemas.gerenciadeusuario.entities.Medico;
import com.integracaodesistemas.gerenciadeusuario.producer.NovoUsuarioProducer;
import com.integracaodesistemas.gerenciadeusuario.producer.dto.NovoUsuarioCadastradoMensagem;
import com.integracaodesistemas.gerenciadeusuario.repositories.MedicoRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

  @Autowired private NovoUsuarioProducer novoUsuarioProducer;
  @Autowired private MedicoRepository medicoRepository;

  public List<Medico> listarMedicos() {
    return medicoRepository.findAll();
  }

  public Optional<Medico> buscarPorId(Long id) {
    return medicoRepository.findById(id);
  }

  public Optional<Medico> buscarPorCrm(Long crm) {
    return medicoRepository.findByCrm(crm);
  }

  public Medico salvarMedico(Medico medico) {
    Medico medicoSalvo = this.medicoRepository.save(medico);
    NovoUsuarioCadastradoMensagem mensagem =
        new NovoUsuarioCadastradoMensagem(
            medicoSalvo.getId().toString(),
            medicoSalvo.getEmail(),
            senhaCriptografada(medicoSalvo.getSenha()),
            "MEDICO");
    this.novoUsuarioProducer.enviarMensagemNovoUsuario(mensagem);
    return medicoSalvo;
  }

  public Optional<Medico> atualizarMedico(Long id, Medico medicoAtualizado) {
    return medicoRepository
        .findById(id)
        .map(
            medicoExistente -> {
              medicoExistente.setNome(medicoAtualizado.getNome());
              medicoExistente.setCrm(medicoAtualizado.getCrm());
              medicoExistente.setTelefone(medicoAtualizado.getTelefone());
              return medicoRepository.save(medicoExistente);
            });
  }

  public void deletarMedico(Long id) {
    medicoRepository.deleteById(id);
  }

  private static String senhaCriptografada(String senhaRaw) {
    return BCrypt.hashpw(senhaRaw, BCrypt.gensalt());
  }
}
