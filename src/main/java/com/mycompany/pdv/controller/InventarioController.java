/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.controller;

import com.mycompany.pdv.modelo.Inventario;
import com.mycompany.pdv.modelo.ItemInventario;
import com.mycompany.pdv.modelo.Produto;
import com.mycompany.pdv.modelo.Usuario;
import com.mycompany.pdv.repositorio.InventarioFacade;
import com.mycompany.pdv.repositorio.ProdutoFacade;
import com.mycompany.pdv.util.jsf.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TableColumn.CellEditEvent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "inventarioController")
@ViewScoped
public class InventarioController implements Serializable {

    @Inject
    private ProdutoFacade repositorio;
    private List<Produto> produtosSelecionados;
    
    
    @Inject
    private InventarioFacade inventarioRepository;
    private Inventario inventario;
    private List<Inventario> inventarios;
    
    @ManagedProperty(value = "#{loginController.usuario}")
    private Usuario usuario;

    public InventarioController() {
        this.inventario = new Inventario();
        this.produtosSelecionados = new ArrayList<Produto>();
//        List<Produto> produtos = repositorio.findAll();
//        for (Produto produto : produtos) {
//            ItemInventario item = new ItemInventario();
//            item.setProduto(produto);
//            this.inventario.getItens().add(item);
//        }
    }
   
    

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public List<Inventario> getInventarios() {
        if(inventarios == null) {
            inventarios = inventarioRepository.findAll();
        }
        return inventarios;
    }

    public List<Produto> getProdutosSelecionados() {
        return produtosSelecionados;
    }

    public void setProdutosSelecionados(List<Produto> produtosSelecionados) {
        this.produtosSelecionados = produtosSelecionados;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void pesquisar() {
        List<Produto> produtos = repositorio.findAll();
        for (Produto produto : produtos) {
            ItemInventario item = new ItemInventario();
            item.setProduto(produto);
            item.setQuantidadeAtual(produto.getEstoqueAtual());
            item.setInventario(inventario);
            this.inventario.getItens().add(item);
        }
    }
    
    public void adicionar() {
        for (Produto produtosSelecionado : produtosSelecionados) {
            ItemInventario item = new ItemInventario();
            item.setProduto(produtosSelecionado);
            item.setQuantidadeAtual(produtosSelecionado.getEstoqueAtual());
            item.setInventario(inventario);
            this.inventario.getItens().add(item);
        }
        produtosSelecionados.clear();
    }
    
    

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

//    public void atualizarEstoque() {
//        for (Produto item : produtos) {
//            repositorio.edit(item);
//        }
//        JsfUtil.addMessage("Inventário finalizado com sucesso!");
//    }
    
    public List<Produto> getProdutosInventario() {
        return repositorio.findAll();
    }

    
    public void salvar() {
        if(inventario.getId() == null) {
            inventarioRepository.create(inventario);
        }
        else {
            inventarioRepository.edit(inventario);
        }
        JsfUtil.addMessage("Salvo com sucesso!");
    }
    
}
