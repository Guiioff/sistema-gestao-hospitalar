package br.com.upe.gestaoconsultasexames.repository;

import br.com.upe.gestaoconsultasexames.model.Exame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExameRepository extends JpaRepository<Exame, Long> {
}
