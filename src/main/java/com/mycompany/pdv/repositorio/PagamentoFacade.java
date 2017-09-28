/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.repositorio;

import com.mycompany.pdv.modelo.Pagamento;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author raque
 */
@Stateless
public class PagamentoFacade extends AbstractFacade<Pagamento> implements Serializable {

    @PersistenceContext(unitName = "pdvPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagamentoFacade() {
        super(Pagamento.class);
    }
    
    public List<Pagamento> getContasEmitidas() {
        return getEntityManager().createQuery("SELECT conta FROM Pagamento conta WHERE conta.pedido IS NOT NULL and conta.dataPagamento IS NULL", Pagamento.class).getResultList();
    }
    
}
