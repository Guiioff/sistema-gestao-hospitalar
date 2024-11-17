package br.com.upe.gestaoconsultasexames.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "exames_predicao_diabetes")
@DiscriminatorValue("DIABETES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamePredicaoDiabetes extends Exame {

    @Column(name = "idade")
    private Float idade;

    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "raca")
    private String raca;

    @Column(name = "imc")
    private Float imc;

    @Column(name = "hba1c")
    private Float hba1c;

    @Column(name = "glicose_sanguinea")
    private Float glicoseSanguinea;

    @Column(name = "genero")
    private String genero;

    @Column(name = "historico_tabagismo")
    private String historicoTabagismo;

    @Column(name = "hipertensao")
    private Boolean hipertensao;

    @Column(name = "doenca_cardiaca")
    private Boolean doencaCardiaca;

    @Override
    public Map<String, Object> coletarDadosExame() {
        Map<String, Object> dados = new HashMap<>();
        dados.put("idade", idade);
        dados.put("localizacao", localizacao);
        dados.put("raca", raca);
        dados.put("imc", imc);
        dados.put("hba1c", hba1c);
        dados.put("glicoseSanguinea", glicoseSanguinea);
        dados.put("genero", genero);
        dados.put("historicoTabagismo", historicoTabagismo);
        dados.put("hipertensao", hipertensao);
        dados.put("doencaCardiaca", doencaCardiaca);
        return dados;
    }
}
