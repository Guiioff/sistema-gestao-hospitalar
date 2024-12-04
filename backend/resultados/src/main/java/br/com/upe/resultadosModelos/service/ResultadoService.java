package br.com.upe.resultadosModelos.service;

import br.com.upe.resultadosModelos.entity.Resultado;
import br.com.upe.resultadosModelos.repository.ResultadoRepository;
import br.com.upe.resultadosModelos.dto.ResultadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultadoService {

    @Autowired
    private ResultadoRepository repository;

    public Resultado salvarResultado(ResultadoDTO resultadoDTO) {
        validarResultadoDTO(resultadoDTO);

        if (repository.existsByExameIdAndPacienteId(resultadoDTO.getExameId(), resultadoDTO.getPacienteId())) {
            throw new IllegalArgumentException("Já existe um resultado para este exame e paciente.");
        }
    
        String resultadoTraduzido = traduzirResultado(resultadoDTO.getTipoExame(), resultadoDTO.getResultado());
        resultadoDTO.setResultado(resultadoTraduzido);

     
        Resultado resultado = new Resultado();
        resultado.setPacienteId(resultadoDTO.getPacienteId());
        resultado.setExameId(resultadoDTO.getExameId());
        resultado.setResultado(resultadoDTO.getResultado());
        resultado.setTipoExame(resultadoDTO.getTipoExame());

       
        return repository.save(resultado);
    }

    private void validarResultadoDTO(ResultadoDTO resultadoDTO) {
        if (resultadoDTO.getExameId() == null || resultadoDTO.getPacienteId() == null || resultadoDTO.getResultado() == null) {
            throw new IllegalArgumentException("Os campos exameId, pacienteId e resultado são obrigatórios.");
        }
    }

    private String traduzirResultado(String tipoExame, String resultado) {
        switch (tipoExame) {
            case "PredicaoAtaqueCardiaco":
                if ("Doença cardíaca detectada".equals(resultado)) {
                    return "Doença cardíaca detectada";
                } else if ("Doença cardíaca não detectada".equals(resultado)) {
                    return "Doença cardíaca não detectada";
                }
                break;
            case "PredicaoDiabetes":
                if ("1".equals(resultado)) {
                    return "Diabético";
                } else {
                    return "Não diabético";
                }
            default:
                return "Exame desconhecido";
        }
        return "Resultado inválido";
    }
}
