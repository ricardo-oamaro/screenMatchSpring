package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.model.DadosSerie;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IconverteDados{

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
