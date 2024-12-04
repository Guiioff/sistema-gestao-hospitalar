package br.com.upe.resultadosModelos.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoDTO {
//    private Long id;
    private Long exameId;

    @JsonAlias({"predict", "prediction"})
    private String resultado;

    private Long pacienteId;
    private Long medicoId;
    private String tipoExame;
	
}
