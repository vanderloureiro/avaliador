package com.br.avaliador.repository.criteria;

import com.br.avaliador.domain.entity.Avaliacao;
import com.br.avaliador.repository.criteria.filtro.AvaliacaoFiltro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AvaliacaoRepositoryCustom {
    
    Page<Avaliacao> buscarComFiltro(AvaliacaoFiltro params, Pageable paginacao);
}
