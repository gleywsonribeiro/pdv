/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.repositorio;


import com.mycompany.pdv.modelo.Cliente;
import com.mycompany.pdv.repositorio.filter.ClienteFilter;
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
public class ClienteFacade extends AbstractFacade<Cliente> implements Serializable {

    @PersistenceContext(unitName = "pdvPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    public List<Cliente> getClientesPorNome(String nome) {
        return getEntityManager().createQuery("SELECT c FROM Cliente AS c WHERE UPPER(c.nome) LIKE :nome", Cliente.class)
                .setParameter("nome", "%" + nome.toUpperCase() + "%").getResultList();
    }

    public List<Cliente> getClientesFiltrados(ClienteFilter filtro) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Cliente> query = builder.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);
        Predicate predicate = builder.and();

        if (filtro.getId() != null) {
            predicate = builder.and(predicate, builder.equal(from.get("id"), filtro.getId()));
        }

        if (filtro.getNome() != null) {
            predicate = builder.and(predicate, builder.like(from.<String>get("nome"), "%" + filtro.getNome().toUpperCase() + "%"));
        }

        TypedQuery<Cliente> typedQuery = getEntityManager().createQuery(
                query.select(from)
                .where(predicate)
        //                .orderBy(builder.asc(from.get("descricao")))
        );
        return typedQuery.getResultList();
    }

}
