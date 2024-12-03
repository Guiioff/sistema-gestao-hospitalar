package br.com.upe.test.service;



import br.com.upe.resultadosModelos.dto.ResultadoDTO;
import br.com.upe.resultadosModelos.entity.Resultado;
import br.com.upe.resultadosModelos.repository.ResultadoRepository;
import br.com.upe.resultadosModelos.service.ResultadoService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ResultadoServiceTest {

    @Mock
    private ResultadoRepository resultadoRepository;

    @InjectMocks
    private ResultadoService resultadoService;

    public ResultadoServiceTest() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    void salvarResultado_CasoPredicaoAtaqueCardiaco_Positivo() {
     
        ResultadoDTO resultadoDTO = new ResultadoDTO();
        resultadoDTO.setExameId(1L);
        resultadoDTO.setPacienteId(100L);
        resultadoDTO.setResultado("Doença cardíaca detectada");
        resultadoDTO.setTipoExame("PredicaoAtaqueCardiaco");

        Resultado resultadoEsperado = new Resultado();
        resultadoEsperado.setId(1L);
        resultadoEsperado.setExameId(1L);
        resultadoEsperado.setPacienteId(100L);
        resultadoEsperado.setResultado("Doença cardíaca detectada");

        when(resultadoRepository.save(any(Resultado.class))).thenReturn(resultadoEsperado);

       
        Resultado resultadoSalvo = resultadoService.salvarResultado(resultadoDTO);

       
        assertNotNull(resultadoSalvo);
        assertEquals("Doença cardíaca detectada", resultadoSalvo.getResultado());
        assertEquals(1L, resultadoSalvo.getExameId());
        assertEquals(100L, resultadoSalvo.getPacienteId());
        verify(resultadoRepository, times(1)).save(any(Resultado.class));
    }

    @Test
    void salvarResultado_CasoPredicaoAtaqueCardiaco_Negativo() {
              ResultadoDTO resultadoDTO = new ResultadoDTO();
        resultadoDTO.setExameId(2L);
        resultadoDTO.setPacienteId(101L);
        resultadoDTO.setResultado("Doença cardíaca não detectada");
        resultadoDTO.setTipoExame("PredicaoAtaqueCardiaco");

        Resultado resultadoEsperado = new Resultado();
        resultadoEsperado.setId(2L);
        resultadoEsperado.setExameId(2L);
        resultadoEsperado.setPacienteId(101L);
        resultadoEsperado.setResultado("Doença cardíaca não detectada");

        when(resultadoRepository.save(any(Resultado.class))).thenReturn(resultadoEsperado);

      
        Resultado resultadoSalvo = resultadoService.salvarResultado(resultadoDTO);

      
        assertNotNull(resultadoSalvo);
        assertEquals("Doença cardíaca não detectada", resultadoSalvo.getResultado());
        assertEquals(2L, resultadoSalvo.getExameId());
        assertEquals(101L, resultadoSalvo.getPacienteId());
        verify(resultadoRepository, times(1)).save(any(Resultado.class));
    }

    @Test
    void salvarResultado_CasoPredicaoDiabetes_Diabetico() {
     
        ResultadoDTO resultadoDTO = new ResultadoDTO();
        resultadoDTO.setExameId(3L);
        resultadoDTO.setPacienteId(102L);
        resultadoDTO.setResultado("1");
        resultadoDTO.setTipoExame("PredicaoDiabetes");

        Resultado resultadoEsperado = new Resultado();
        resultadoEsperado.setId(3L);
        resultadoEsperado.setExameId(3L);
        resultadoEsperado.setPacienteId(102L);
        resultadoEsperado.setResultado("Diabético");

        when(resultadoRepository.save(any(Resultado.class))).thenReturn(resultadoEsperado);

  
        Resultado resultadoSalvo = resultadoService.salvarResultado(resultadoDTO);

 
        assertNotNull(resultadoSalvo);
        assertEquals("Diabético", resultadoSalvo.getResultado());
        assertEquals(3L, resultadoSalvo.getExameId());
        assertEquals(102L, resultadoSalvo.getPacienteId());
        verify(resultadoRepository, times(1)).save(any(Resultado.class));
    }

    @Test
    void salvarResultado_CasoPredicaoDiabetes_NaoDiabetico() {
  
        ResultadoDTO resultadoDTO = new ResultadoDTO();
        resultadoDTO.setExameId(4L);
        resultadoDTO.setPacienteId(103L);
        resultadoDTO.setResultado("0");
        resultadoDTO.setTipoExame("PredicaoDiabetes");

        Resultado resultadoEsperado = new Resultado();
        resultadoEsperado.setId(4L);
        resultadoEsperado.setExameId(4L);
        resultadoEsperado.setPacienteId(103L);
        resultadoEsperado.setResultado("Não diabético");

        when(resultadoRepository.save(any(Resultado.class))).thenReturn(resultadoEsperado);

     
        Resultado resultadoSalvo = resultadoService.salvarResultado(resultadoDTO);

     
        assertNotNull(resultadoSalvo);
        assertEquals("Não diabético", resultadoSalvo.getResultado());
        assertEquals(4L, resultadoSalvo.getExameId());
        assertEquals(103L, resultadoSalvo.getPacienteId());
        verify(resultadoRepository, times(1)).save(any(Resultado.class));
    }
}
