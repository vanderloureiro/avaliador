package com.br.avaliador.controller;

import java.util.List;

import com.br.avaliador.domain.AvaliacaoService;
import com.br.avaliador.domain.dto.AvaliacaoDto;
import com.br.avaliador.domain.form.AvaliacaoForm;
import com.br.avaliador.exception.NotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

        try {
            return ResponseEntity.ok().body(this.service.cadastrar(form));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoDto>> buscar() {

        try {
            return ResponseEntity.ok().body(this.service.buscar());
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDto> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(this.service.buscarPorId(id));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoDto> alterar(@RequestBody AvaliacaoForm form, @PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(this.service.alterar(form, id));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            this.service.remover(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
