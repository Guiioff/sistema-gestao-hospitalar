package br.com.upe.gestaoconsultasexames.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "consultas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;

    @Column(name = "medico_id", nullable = false)
    private Long medicoId;

    @Column(name = "data_consulta", nullable = false)
    private LocalDateTime dataConsulta;

    @OneToMany(mappedBy = "consulta")
    private List<Exame> exames;
}
