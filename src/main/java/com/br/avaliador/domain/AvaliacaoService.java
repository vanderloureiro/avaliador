package com.br.avaliador.domain;

import com.br.avaliador.domain.dto.AvaliacaoDto;
import com.br.avaliador.domain.entity.Avaliacao;
import com.br.avaliador.domain.form.AvaliacaoForm;
import com.br.avaliador.domain.mapper.AvaliacaoMapper;
import com.br.avaliador.repository.AvaliacaoRepository;
import com.br.avaliador.repository.criteria.filtro.AvaliacaoFiltro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class AvaliacaoService {
    
    private AvaliacaoRepository repository;
    private AvaliacaoMapper mapper;

    public AvaliacaoService(AvaliacaoRepository repository, AvaliacaoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AvaliacaoDto cadastrar(AvaliacaoForm form) {

        Avaliacao entidadeParaSalvar = this.mapper.toEntity(form);
        Avaliacao entidadeSalva = this.repository.save(entidadeParaSalvar);
        return this.mapper.toDto(entidadeSalva);
    }


    public Page<AvaliacaoDto> buscar(AvaliacaoFiltro params, Pageable paginacao) {

        Page<Avaliacao> avaliacoes = this.repository.buscarComFiltro(params, paginacao);

        return avaliacoes.map(avaliacao -> {
            return this.mapper.toDto(avaliacao);
        });
    }

    public AvaliacaoDto buscarPorId(Long id) throws NotFoundException {

        return this.mapper.toDto(this.buscarEntidadePorId(id));
    }

    private Avaliacao buscarEntidadePorId(Long id) throws NotFoundException {
        return this.repository.findById(id).orElseThrow(
            () -> new NotFoundException("Avaliacao não encontrada"));
    }

    public AvaliacaoDto alterar(AvaliacaoForm form, Long id) throws NotFoundException {
        Avaliacao entidade = this.buscarEntidadePorId(id);
        entidade.setComentario(form.getComentario());
        entidade.setNota(form.getNota());
        return this.mapper.toDto(this.repository.save(entidade));
    }

    public void remover(Long id) {
        this.repository.deleteById(id);
    }
    
}
