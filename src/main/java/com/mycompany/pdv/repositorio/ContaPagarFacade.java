/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.repositorio;

import com.mycompany.pdv.modelo.ContaPagar;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gleywson
 */
@Stateless
public class ContaPagarFacade extends AbstractFacade<ContaPagar> implements Serializable {

    @PersistenceContext(unitName = "pdvPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContaPagarFacade() {
        super(ContaPagar.class);
    }
    
    public List<ContaPagar> contasPendentes() {
        return getEntityManager().createQuery("SELECT conta FROM conta_pagar conta WHERE conta.dataPagamento IS NULL", ContaPagar.class).getResultList();
    }
}
