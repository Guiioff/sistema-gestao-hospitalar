package br.com.upe.gestaoconsultasexames.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Entity
@DiscriminatorValue("PredicaoAtaqueCardiaco")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamePredicaoAtaqueCardiaco extends Exame{

    @Column(name = "cp")
    private String cp;  // Chest Pain type chest pain type

    @Column(name = "trtbps")
    private Integer trtbps;  // Resting blood pressure (em mmHg)

    @Column(name = "chol")
    private Double chol;  // Cholesterol in mg/dl fetched via BMI sensor

    @Column(name = "fbs")
    private Integer fbs;  // Fasting blood sugar > 120 mg/dl (1 = true; 0 = false)

    @Override
    public Map<String, Object> coletarDadosExame() {
        Map<String, Object> dados = new HashMap<>();
        dados.put("cp", cp);
        dados.put("trtbps", trtbps);
        dados.put("chol", chol);
        dados.put("fbs", fbs);
        return dados;
    }
}
