/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.controller;

import com.mycompany.pdv.modelo.Cliente;
import com.mycompany.pdv.modelo.TipoPessoa;
import com.mycompany.pdv.repositorio.ClienteFacade;
import com.mycompany.pdv.repositorio.filter.ClienteFilter;
import com.mycompany.pdv.util.jsf.JsfUtil;
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
@Named(value = "clienteController")
//@ViewScoped
@SessionScoped
public class ClienteController implements Serializable{

    private Cliente cliente;
    private ClienteFilter filtro;
    private List<Cliente> clientesFiltrados;
    
    
    @Inject
    private ClienteFacade repositorio;
    
    public ClienteController() {
        cliente  = new Cliente();
        this.filtro = new ClienteFilter();
        this.clientesFiltrados = new ArrayList<Cliente>();
    }
    
    public void novo() {
        this.cliente = new Cliente();
//        this.filtro = new ClienteFilter();
        this.clientesFiltrados = new ArrayList<Cliente>();
    }
    
    public void remover() {
        repositorio.remove(cliente);
        JsfUtil.addMessage("Cliente removido com sucesso!");
        this.clientesFiltrados = null;
    }
    
    public void salvar() {
        if(cliente.getId() == null) {
            repositorio.create(cliente);
        } else {
            repositorio.edit(cliente);
        }
        this.cliente = new Cliente();
//        this.filtro = new ClienteFilter();
        this.clientesFiltrados = new ArrayList<Cliente>();
        JsfUtil.addMessage("Cliente inserido com sucesso!");
    }

    public void pesquisar() {
        this.clientesFiltrados = repositorio.getClientesFiltrados(filtro);
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(ClienteFilter filtro) {
        this.filtro = filtro;
    }

    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }
    
    public TipoPessoa[] getTiposPessoa() {
        return TipoPessoa.values();
    }
    
}
