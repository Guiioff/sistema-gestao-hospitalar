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
@Table(name = "exames_predicao_ataque_cardiaco")
@DiscriminatorValue("PredicaoAtaqueCardiaco")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamePredicaoAtaqueCardiaco extends Exame {

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "tipo_dor_toracica")
    private Integer tipoDorToracica;

    @Column(name = "pressao_sanguinea_repouso")
    private Integer pressaoSanguineaRepouso;

    @Column(name = "colesterol_serico")
    private Integer colesterolSerico;

    @Column(name = "glicemia_jejum_maior_que_120")
    private Boolean glicemiaJejumMaiorQue120;

    @Column(name = "resultados_eletrocardiograma_repouso")
    private Integer resultadosEletrocardiogramaRepouso;

    @Column(name = "frequencia_cardiaca_maxima")
    private Integer frequenciaCardiacaMaxima;

    @Column(name = "angina_induzida")
    private Boolean anginaInduzida;

    @Column(name = "depressao_st_induzida")
    private Float depressaoSTInduzida;

    @Column(name = "inclinacao_pico_st")
    private Integer inclinacaoPicoST;

    @Column(name = "numero_vasos_principais")
    private Integer numeroVasosPrincipais;

    @Column(name = "avaliacao_teste_esforco")
    private String avaliacaoTesteEsforco;

    @Override
    public Map<String, Object> coletarDadosExame() {
        Map<String, Object> dados = new HashMap<>();
        dados.put("idade", idade);
        dados.put("sexo", sexo);
        dados.put("tipoDorToracica", tipoDorToracica);
        dados.put("pressaoSanguineaRepouso", pressaoSanguineaRepouso);
        dados.put("colesterolSerico", colesterolSerico);
        dados.put("glicemiaJejumMaiorQue120", glicemiaJejumMaiorQue120);
        dados.put("resultadosEletrocardiogramaRepouso", resultadosEletrocardiogramaRepouso);
        dados.put("frequenciaCardiacaMaxima", frequenciaCardiacaMaxima);
        dados.put("anginaInduzida", anginaInduzida);
        dados.put("depressaoSTInduzida", depressaoSTInduzida);
        dados.put("inclinacaoPicoST", inclinacaoPicoST);
        dados.put("numeroVasosPrincipais", numeroVasosPrincipais);
        dados.put("avaliacaoTesteEsforco", avaliacaoTesteEsforco);
        return dados;
    }
}
