/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.converter;

import com.mycompany.pdv.modelo.Cidade;
import com.mycompany.pdv.repositorio.CidadeFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Gleywson
 */
@Named(value = "cidadeConverter")
public class CidadeConverter implements Converter{
    @Inject
    private CidadeFacade repositorio;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Cidade cidade = null;
        if (value != null) {
            Long id = new Long(value);
            cidade = repositorio.find(id);
        }
        return cidade;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Cidade) value).getId().toString();
        } else {
            return "";
        }
    }
    
    
}
