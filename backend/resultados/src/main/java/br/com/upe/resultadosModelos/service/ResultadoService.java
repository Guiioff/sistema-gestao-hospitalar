package br.com.upe.resultadosModelos.service;

import br.com.upe.resultadosModelos.entity.Resultado;
import br.com.upe.resultadosModelos.listener.ResultadosProducer;
import br.com.upe.resultadosModelos.repository.ResultadoRepository;

import br.com.upe.resultadosModelos.dto.ResultadoDTO;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ResultadoService {

	@Autowired
	private ResultadoRepository repository;

	@Autowired
	private ResultadosProducer resultadoProducer;

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
		resultado.setMedicoId(resultadoDTO.getMedicoId());
		resultado.setResultado(resultadoDTO.getResultado());
		resultado.setTipoExame(resultadoDTO.getTipoExame());

		Resultado resultadoSalvo = repository.save(resultado);

		enviarParaFila(resultadoSalvo);

		return resultadoSalvo;

	}

	private void validarResultadoDTO(ResultadoDTO resultadoDTO) {
		if (resultadoDTO.getExameId() == null || resultadoDTO.getPacienteId() == null
				|| resultadoDTO.getResultado() == null) {
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

	public void enviarParaFila(Resultado resultado) {
		Map<String, Object> dados = Map.of("pacienteId", resultado.getPacienteId(), "exameId", resultado.getExameId(),
				"resultado", resultado.getResultado(), "tipoExame", resultado.getTipoExame(),"medicoId", resultado.getMedicoId());
		resultadoProducer.enviarResultadoParaFila(dados);
	}
}
