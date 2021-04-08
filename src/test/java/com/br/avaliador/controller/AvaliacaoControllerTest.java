package com.br.avaliador.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.br.avaliador.domain.AvaliacaoService;
import com.br.avaliador.domain.form.AvaliacaoForm;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
} 