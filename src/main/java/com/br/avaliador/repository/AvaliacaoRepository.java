package com.br.avaliador.repository;

import com.br.avaliador.domain.entity.Avaliacao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    
}
