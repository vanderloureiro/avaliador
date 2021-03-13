package com.br.avaliador.integracao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.br.avaliador.domain.dto.AvaliacaoDto;
import com.br.avaliador.domain.entity.Avaliacao;
import com.br.avaliador.domain.form.AvaliacaoForm;
import com.br.avaliador.repository.AvaliacaoRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AvaliacaoTest {
    
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AvaliacaoRepository repository;

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
    public void buscarTodasAvaliacoesTest() {

        ResponseEntity<AvaliacaoDto[]> response = this.testRestTemplate
            .exchange("/avaliacao", HttpMethod.GET, null, AvaliacaoDto[].class);
    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void buscarAvaliacaoPorIdTest() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setComentario("Bom");
        avaliacao.setNota(8);
        Avaliacao avaliacaoSalva = this.repository.save(avaliacao);

        ResponseEntity<AvaliacaoDto> response = this.testRestTemplate
            .exchange("/avaliacao/" + avaliacaoSalva.getId(), HttpMethod.GET, null, AvaliacaoDto.class);
    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getNota(), 8);
    }

    @Test
    public void alterarAvaliacaoTest() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setComentario("Bom");
        avaliacao.setNota(8);
        Avaliacao avaliacaoSalva = this.repository.save(avaliacao);

        AvaliacaoForm form = new AvaliacaoForm();
        form.setNota(5);
        form.setComentario("Mediano");

        HttpEntity<AvaliacaoForm> httpEntity = new HttpEntity<>(form);

        ResponseEntity<AvaliacaoDto> response = this.testRestTemplate
            .exchange("/avaliacao/" + avaliacaoSalva.getId(), HttpMethod.PUT, httpEntity, AvaliacaoDto.class);

    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getNota(), 5);
        assertEquals(response.getBody().getComentario(), "Mediano");
    }

    @Test
    public void removerAvaliacaoTest() {

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setComentario("Bom");
        avaliacao.setNota(8);
        Avaliacao avaliacaoSalva = this.repository.save(avaliacao);

        ResponseEntity<Void> response = this.testRestTemplate
            .exchange("/avaliacao/" + avaliacaoSalva.getId(), HttpMethod.DELETE, null, Void.class);
    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
