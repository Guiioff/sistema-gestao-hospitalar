package com.matt.gestao_agendamentos.mapper;

import com.matt.gestao_agendamentos.controller.dto.CriarExameDTO;
import com.matt.gestao_agendamentos.model.Exame;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ToEntityExame {

    public static Exame toEntity(CriarExameDTO exameDTO) {
        Exame exame = new Exame();
        exame.setConsultaId(exameDTO.consultaId());
        exame.setTipoExame(exameDTO.tipoExame());

        return exame;
    }
}
