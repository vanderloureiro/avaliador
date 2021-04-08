package com.br.avaliador.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import com.br.avaliador.domain.AvaliacaoService;
import com.br.avaliador.domain.dto.AvaliacaoDto;
import com.br.avaliador.domain.form.AvaliacaoForm;
import com.br.avaliador.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
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

    @Test
    void testaBuscaAvaliacaoPorId() throws Exception {

        AvaliacaoDto avaliacaoDto = new AvaliacaoDto();
        avaliacaoDto.setNota(5);
        avaliacaoDto.setCodigoAvaliacao(10L);
        avaliacaoDto.setComentario("Ótimo!");

        when(avaliacaoService.buscarPorId(10L)).thenReturn(avaliacaoDto);

        MockHttpServletResponse response = mockMvc.perform(get("/avaliacao/"+10L)
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn().getResponse();

        String atual = objectMapper.writeValueAsString(avaliacaoDto);
        String esperado = response.getContentAsString(StandardCharsets.UTF_8);
        
        assertEquals(esperado, atual);
    }
} 