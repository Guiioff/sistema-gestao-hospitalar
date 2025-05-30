package com.integracaodesistemas.gerenciadeusuario.repositories;

import com.integracaodesistemas.gerenciadeusuario.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByCrm(Long crm);
}
