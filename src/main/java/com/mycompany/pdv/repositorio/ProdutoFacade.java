/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.repositorio;


import com.mycompany.pdv.modelo.Produto;
import com.mycompany.pdv.repositorio.filter.ProdutoFilter;
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
public class ProdutoFacade extends AbstractFacade<Produto> implements Serializable{

    @PersistenceContext(unitName = "pdvPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutoFacade() {
        super(Produto.class);
    }
    
    public List<Produto> getProdutosSemCodigoBarras() {
        return getEntityManager().createQuery("SELECT produto FROM Produto AS produto WHERE produto.codigoDeBarras IS NULL", Produto.class).getResultList();
    }
    
    public List<Produto> getProdutosPorDescricao(String descricao) { 
        return getEntityManager().createQuery("SELECT p FROM Produto AS p WHERE P.ativo = TRUE AND UPPER(p.descricao) LIKE :descricao", Produto.class)
                .setParameter("descricao", "%" + descricao.toUpperCase() + "%").getResultList();
    }

    public List<Produto> getProdutosPorCodigo(String cod) {
        return getEntityManager().createQuery("SELECT p FROM Produto AS p WHERE P.ativo = TRUE AND p.codigoDeBarras LIKE :cod", Produto.class)
                .setParameter("cod", "%" + cod + "%").getResultList();
    }
    
    public List<Produto> getProdutosFiltrados(ProdutoFilter filtro) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);
        Predicate predicate = builder.and();
        
        if(filtro.getId() != null) {
            predicate = builder.and(predicate, builder.equal(from.get("id"), filtro.getId()));
        }
        
        if(filtro.getDescricao() != null) {
            predicate = builder.and(predicate, builder.like(from.<String>get("descricao"),"%"+ filtro.getDescricao().toUpperCase() +"%"));
        }
        
        TypedQuery<Produto> typedQuery = getEntityManager().createQuery(
                query.select(from)
                .where(predicate)
//                .orderBy(builder.asc(from.get("descricao")))
        );
        return typedQuery.getResultList();
//        Query query = getEntityManager().createQuery("SELECT p FROM Produto as p WHERE p.id = :cod OR p.descricao LIKE :descricao", Produto.class)
//                .setParameter("cod", filtro.getId())
//                .setParameter("descricao", filtro.getDescricao());
//        
//        return query.getResultList();
    }
}
