/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.controller;

import com.mycompany.pdv.modelo.Usuario;
import com.mycompany.pdv.repositorio.UsuarioFacade;
import com.mycompany.pdv.util.jsf.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author raque
 */
@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController implements Serializable {

    private Usuario usuario;
    private List<Usuario> usuarios;
//    private List<Grupo> gruposSelecionados;

    @Inject
    private UsuarioFacade repositorio;

    public UsuarioController() {
        this.usuario = new Usuario();
//        this.gruposSelecionados = new ArrayList<Grupo>();
    }

    public void salvar() {
//        usuario.setGrupos(gruposSelecionados);
        if (usuario.getId() == null) {
            repositorio.create(usuario);
        } else {
            repositorio.edit(usuario);
        }
        JsfUtil.addMessage("Salvo com sucesso!");
//        gruposSelecionados.clear();
        this.usuario = new Usuario();
        this.usuarios = null;
    }

    public void novo() {
        this.usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

//    public List<Grupo> getGruposSelecionados() {
//        return gruposSelecionados;
//    }
//
//    public void setGruposSelecionados(List<Grupo> gruposSelecionados) {
//        this.gruposSelecionados = gruposSelecionados;
//    }

    public List<Usuario> getUsuarios() {
        if (usuarios == null) {
            usuarios = repositorio.findAll();
        }
        return usuarios;
    }

    public void remover() {
        this.repositorio.remove(usuario);
        this.usuario = new Usuario();
        this.usuarios = null;
    }

}
