package com.br.avaliador.controller;

import java.util.List;

import com.br.avaliador.domain.AvaliacaoService;
import com.br.avaliador.domain.dto.AvaliacaoDto;
import com.br.avaliador.domain.form.AvaliacaoForm;
import com.br.avaliador.repository.criteria.filtro.AvaliacaoFiltro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
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
        return ResponseEntity.ok().body(this.service.cadastrar(form));
    }

    @GetMapping
    /*
    isso é para caso use o swagger, serve para ocultar os campos normais do Pageable e 
    exibir os campos com nomes que você quer, no caso, page, size e sorte.
    adicionar tbm @ApiIgnore antes de (... Pageable pageable)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
                    + "Default sort order is ascending. " + "Multiple sort criteria are supported.") })
     */
    public ResponseEntity<List<AvaliacaoDto>> buscarTodas(AvaliacaoFiltro params,
            @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {

        Page<AvaliacaoDto> resultado = this.service.buscar(params, pageable);

        HttpHeaders headers = new HttpHeaders();
        headers.add("total-elementos", Long.toString(resultado.getTotalElements()));
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "total-elementos");

        return ResponseEntity.ok().headers(headers).body(resultado.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDto> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(this.service.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoDto> alterar(@RequestBody AvaliacaoForm form, @PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(this.service.alterar(form, id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        this.service.remover(id);
        return ResponseEntity.ok().build();
    }
}
