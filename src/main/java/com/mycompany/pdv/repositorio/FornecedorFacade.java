/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.repositorio;

import com.mycompany.pdv.modelo.Fornecedor;
import com.mycompany.pdv.repositorio.filter.FornecedorFilter;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Gleywson
 */
@Stateless
public class FornecedorFacade extends AbstractFacade<Fornecedor> implements Serializable {

    @PersistenceContext(unitName = "pdvPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FornecedorFacade() {
        super(Fornecedor.class);
    }
    
    public List<Fornecedor> getFornecedoresFiltrados(FornecedorFilter filtro) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Fornecedor> query = builder.createQuery(Fornecedor.class);
        Root<Fornecedor> from = query.from(Fornecedor.class);
        Predicate predicate = builder.and();

        if (filtro.getId() != null) {
            predicate = builder.and(predicate, builder.equal(from.get("id"), filtro.getId()));
        }

        if (filtro.getNome() != null) {
            predicate = builder.and(predicate, builder.like(from.<String>get("nome"), "%" + filtro.getNome().toUpperCase() + "%"));
        }

        TypedQuery<Fornecedor> typedQuery = getEntityManager().createQuery(
                query.select(from)
                .where(predicate)
        //                .orderBy(builder.asc(from.get("descricao")))
        );
        return typedQuery.getResultList();
    }
    
}
