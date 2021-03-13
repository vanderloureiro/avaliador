package com.br.avaliador.repository;

import com.br.avaliador.domain.entity.Avaliacao;
import com.br.avaliador.repository.criteria.AvaliacaoRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>, AvaliacaoRepositoryCustom {
    
}
