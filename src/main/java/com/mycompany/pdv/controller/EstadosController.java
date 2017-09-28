/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.controller;

import com.mycompany.pdv.modelo.Estado;
import com.mycompany.pdv.repositorio.EstadoFacade;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "estadosController")
@Dependent
public class EstadosController {
 
    @Inject private EstadoFacade repositorio;

    public List<Estado> getEstados() {
        return repositorio.findAll();
    }
    
    
}
