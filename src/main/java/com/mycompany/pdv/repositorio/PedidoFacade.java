/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.repositorio;

import com.mycompany.pdv.modelo.Pedido;
import com.mycompany.pdv.modelo.TipoPedido;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Gleywson
 */
@Stateless
public class PedidoFacade extends AbstractFacade<Pedido> {

    @PersistenceContext(unitName = "pdvPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }
    
    public List<Pedido> getPedidos() {
        Query query = getEntityManager().createQuery("SELECT pedido FROM Pedido pedido WHERE pedido.tipo = :tipo", Pedido.class);
        query.setParameter("tipo", TipoPedido.PEDIDO);
        return query.getResultList();
    } 
    
    public List<Pedido> getVendas() {
        Query query = getEntityManager().createQuery("SELECT pedido FROM Pedido pedido WHERE pedido.tipo = :tipo", Pedido.class);
        query.setParameter("tipo", TipoPedido.VENDA);
        return query.getResultList();
    }
    
}
