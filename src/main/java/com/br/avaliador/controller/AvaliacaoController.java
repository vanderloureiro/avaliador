package com.br.avaliador.controller;

import java.util.List;

import com.br.avaliador.domain.AvaliacaoService;
import com.br.avaliador.domain.dto.AvaliacaoDto;
import com.br.avaliador.domain.form.AvaliacaoForm;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequestMapping("/avaliacao")
@RestController
public class AvaliacaoController {

    private AvaliacaoService service;

    public AvaliacaoController(AvaliacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDto> cadastrar(@RequestBody AvaliacaoForm form) {
        return ResponseEntity.ok().body(this.service.cadastrar(form));
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoDto>> buscar() {

        return ResponseEntity.ok().body(this.service.buscar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDto> buscarPorUuid(@PathVariable Long id) {

        try {
            return ResponseEntity.ok().body(this.service.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
