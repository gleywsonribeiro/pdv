/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.controller;

import com.mycompany.pdv.modelo.Unidade;
import com.mycompany.pdv.repositorio.UnidadeFacade;
import com.mycompany.pdv.util.jsf.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "unidadecontroller")
@ViewScoped
public class Unidadecontroller implements Serializable {
    
    private Unidade unidade;
    private List<Unidade> unidades;
    
    @Inject
    private UnidadeFacade repositorio;
    
    public Unidadecontroller() {
        unidade = new Unidade();
    }
    
    public void salvar() {
        if(unidade.getId() == null) {
            repositorio.create(unidade);
        } else {
            repositorio.edit(unidade);
        }
        unidade = new Unidade();
        unidades = null;
        JsfUtil.addMessage("Unidade salva com sucesso!");
    }
    
    public void remover() {
        repositorio.remove(unidade);
        unidade = new Unidade();
        unidades = null;
        JsfUtil.addMessage("Removido com sucesso!");
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public List<Unidade> getUnidades() {
        if(unidades == null) {
            unidades = repositorio.findAll();
        } 
        return unidades;
    }

}
