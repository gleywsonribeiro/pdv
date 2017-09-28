/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.repositorio;

import com.mycompany.pdv.modelo.Usuario;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gleywson
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements Serializable {

    @PersistenceContext(unitName = "pdvPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario getUsuarioPorLogin(String login) {
        Usuario usuario = null;

        try {
            usuario = getEntityManager().createQuery("SELECT user FROM Usuario AS user WHERE user.login = :login", Usuario.class)
                    .setParameter("login", login.toLowerCase()).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }

        return usuario;
    }

}
