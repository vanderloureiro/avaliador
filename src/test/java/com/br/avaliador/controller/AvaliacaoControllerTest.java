package com.br.avaliador.controller;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.br.avaliador.domain.AvaliacaoService;
import com.br.avaliador.domain.form.AvaliacaoForm;
import com.br.avaliador.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = AvaliacaoController.class)
public class AvaliacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AvaliacaoService avaliacaoService;

    @Test
    void testaCadastroAvaliacao() throws Exception {
        AvaliacaoForm form = new AvaliacaoForm(5, "Bom!");

        mockMvc.perform(post("/avaliacao")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(form))
            ).andExpect(status().isOk());
    }

    @Test
    void testaBuscaAvaliacoes() throws Exception {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/avaliacao")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk());
    }
        
    @Test
    void testaBuscaAvaliacoesSucesso() throws Exception {
        when(avaliacaoService.buscar()).thenThrow(new NotFoundException("Sem registros!"));

        mockMvc.perform(
            MockMvcRequestBuilders.get("/avaliacao")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    /*
    @Test
    void testaBuscaAvaliacaoPorId() throws Exception {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/avaliacao")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk());
    }*/
} 