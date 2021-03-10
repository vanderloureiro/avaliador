package com.br.avaliador.integracao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.br.avaliador.domain.dto.AvaliacaoDto;
import com.br.avaliador.domain.form.AvaliacaoForm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AvaliacaoTest {
    
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void criarNovaAvaliacaoTest() {

        AvaliacaoForm form = new AvaliacaoForm();
        form.setNota(5);
        form.setComentario("Mediano");

        HttpEntity<AvaliacaoForm> httpEntity = new HttpEntity<>(form);

        ResponseEntity<AvaliacaoDto> response = this.testRestTemplate
            .exchange("/avaliacao", HttpMethod.POST, httpEntity, AvaliacaoDto.class);
    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getNota(), 5);
    }

    @Test
    public void buscaUmaAvaliacaoTest() {

        AvaliacaoForm form = new AvaliacaoForm();
        form.setNota(5);
        form.setComentario("Mediano");

        HttpEntity<AvaliacaoForm> httpEntity = new HttpEntity<>(form);

        ResponseEntity<AvaliacaoDto> response = this.testRestTemplate
            .exchange("/avaliacao", HttpMethod.POST, httpEntity, AvaliacaoDto.class);
    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getNota(), 5);
    }

    @Test
    public void alteraUmaAvaliacaoTest() {

        AvaliacaoForm form = new AvaliacaoForm();
        form.setNota(5);
        form.setComentario("Mediano");

        HttpEntity<AvaliacaoForm> httpEntity = new HttpEntity<>(form);

        ResponseEntity<AvaliacaoDto> response = this.testRestTemplate
            .exchange("/avaliacao", HttpMethod.POST, httpEntity, AvaliacaoDto.class);
    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getNota(), 5);
    }
}
