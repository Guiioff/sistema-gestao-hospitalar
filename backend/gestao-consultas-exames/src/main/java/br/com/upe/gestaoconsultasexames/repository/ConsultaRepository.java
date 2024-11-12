package br.com.upe.gestaoconsultasexames.repository;

import br.com.upe.gestaoconsultasexames.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
