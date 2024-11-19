package br.com.upe.gestaoconsultasexames.repository;

import br.com.upe.gestaoconsultasexames.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query("SELECT c FROM Consulta c LEFT JOIN FETCH c.exames WHERE c.id = :id")
    Optional<Consulta> findByIdWithExames(@Param("id") Long id);
}
