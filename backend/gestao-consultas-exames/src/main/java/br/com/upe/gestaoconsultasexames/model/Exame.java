package br.com.upe.gestaoconsultasexames.model;

import br.com.upe.gestaoconsultasexames.model.enums.TipoExameEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "exames")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_exame")
@Data
public abstract class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataAgendamento;

    @ManyToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    private Consulta consulta;

    @Enumerated(EnumType.STRING)
    private TipoExameEnum tipoExame;

    public abstract Map<String, Object> coletarDadosExame();
}
