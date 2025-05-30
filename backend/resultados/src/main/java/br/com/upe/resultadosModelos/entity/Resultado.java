package br.com.upe.resultadosModelos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long exameId;

    @Column(nullable = false)
    private String resultado;

    @Column(nullable = false)
    private Long pacienteId;
    
    @Column(nullable = false)
    private Long medicoId;
    
    @Column(nullable = false)
    private String tipoExame;

 
}
