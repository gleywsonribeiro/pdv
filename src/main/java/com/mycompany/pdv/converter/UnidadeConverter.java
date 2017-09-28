/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.converter;

import com.mycompany.pdv.modelo.Unidade;
import com.mycompany.pdv.repositorio.UnidadeFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Gleywson
 */
@Named(value = "unidadeConverter")
public class UnidadeConverter implements Converter {

    @Inject
    private UnidadeFacade repositorio;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Unidade unidade = null;
        if (value != null) {
            Long id = new Long(value);
            unidade = repositorio.find(id);
        }
        return unidade;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value != null) {
            return ((Unidade) value).getId().toString();
        } else {
            return "";
        }
    }
    
}
