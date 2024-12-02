package br.com.upe.camelhub.camel.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Map;

public class RemoveTipoExameProcessor implements Processor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(Exchange exchange) throws Exception {

        Map<String, Object> body = exchange.getIn().getBody(Map.class);
        body.remove("tipoExame");

        String newJson = objectMapper.writeValueAsString(body);
        exchange.getIn().setBody(newJson);
    }
}
