/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.controller;

import com.mycompany.pdv.modelo.Fornecedor;
import com.mycompany.pdv.modelo.TipoPessoa;
import com.mycompany.pdv.repositorio.FornecedorFacade;
import com.mycompany.pdv.repositorio.filter.FornecedorFilter;
import com.mycompany.pdv.util.jsf.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "fornecedorController")
@SessionScoped
public class FornecedorController implements Serializable {

    private Fornecedor fornecedor;
    private FornecedorFilter filtro;
    private List<Fornecedor> fornecedoresFiltrados;
    
    @Inject
    private FornecedorFacade repositorio;

    public FornecedorController() {
        this.fornecedor = new Fornecedor();
        this.filtro = new FornecedorFilter();
        this.fornecedoresFiltrados = new ArrayList<Fornecedor>();
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public FornecedorFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(FornecedorFilter filtro) {
        this.filtro = filtro;
    }

    public List<Fornecedor> getFornecedoresFiltrados() {
        return fornecedoresFiltrados;
    }

    public List<Fornecedor> getFornecedores() {
        return repositorio.findAll();
    }
    
    
    
    public void novo() {
        this.fornecedor = new Fornecedor();
    }

    public void salvar() {
        if (fornecedor.getId() == null) {
            repositorio.create(fornecedor);
            JsfUtil.addMessage("Fornecedor cadastrado com sucesso!");
        } else {
            repositorio.edit(fornecedor);
            JsfUtil.addMessage("Fornecedor alterado com sucesso!");
        }
        this.fornecedor = new Fornecedor();
        this.fornecedoresFiltrados = null;
    }
    
    public void pesquisar() {
        this.fornecedoresFiltrados = repositorio.getFornecedoresFiltrados(filtro);
    }

    public void remover() {
        this.repositorio.remove(fornecedor);
        fornecedor = new Fornecedor();
        fornecedoresFiltrados = null;
        JsfUtil.addMessage("Fornecedor removido com sucesso!");
    }

    public TipoPessoa[] getTiposPessoa() {
        return TipoPessoa.values();
    }
}
