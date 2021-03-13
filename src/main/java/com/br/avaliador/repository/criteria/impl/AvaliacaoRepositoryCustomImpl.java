package com.br.avaliador.repository.criteria.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.br.avaliador.domain.entity.Avaliacao;
import com.br.avaliador.repository.criteria.AvaliacaoRepositoryCustom;
import com.br.avaliador.repository.criteria.filtro.AvaliacaoFiltro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;

public class AvaliacaoRepositoryCustomImpl implements AvaliacaoRepositoryCustom {

    private EntityManager entityManager;

    public AvaliacaoRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<Avaliacao> buscarComFiltro(AvaliacaoFiltro params, Pageable paginacao) {

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Avaliacao> query = criteriaBuilder.createQuery(Avaliacao.class);
        Root<Avaliacao> root = query.from(Avaliacao.class);

        // criando predicados
        List<Predicate> predicates = this.criarListaDePredicados(params, criteriaBuilder, root);

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[predicates.size()]));
            
        }
        // ordenando
        query.orderBy(QueryUtils.toOrders(paginacao.getSort(), root, criteriaBuilder));
        TypedQuery<Avaliacao> typedQuery = entityManager.createQuery(query);
        // paginando
        typedQuery.setFirstResult(paginacao.getPageNumber() * paginacao.getPageSize());
        typedQuery.setMaxResults(paginacao.getPageSize());

        return new PageImpl<>(typedQuery.getResultList(), paginacao, this.contarElementosFiltrados(predicates));
    }

    private List<Predicate> criarListaDePredicados(AvaliacaoFiltro params, CriteriaBuilder criteriaBuilder,
        Root<Avaliacao> root) {

        List<Predicate> predicates = new ArrayList<>();

        if (params.getNota() != null ) {
            predicates.add(criteriaBuilder.equal(root.get("nota"), params.getNota()));
        }

        return predicates;
    }

    private Long contarElementosFiltrados(List<Predicate> predicates) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);

        query.select(criteriaBuilder.count(query.from(Avaliacao.class)));

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[predicates.size()]));   
        }
        return entityManager.createQuery(query).getSingleResult();
    }
    
}
