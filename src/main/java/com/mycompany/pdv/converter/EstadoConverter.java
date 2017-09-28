/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.converter;

import com.mycompany.pdv.modelo.Estado;
import com.mycompany.pdv.repositorio.EstadoFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Gleywson
 */
@Named(value = "estadoConverter")
public class EstadoConverter implements Converter{
    @Inject
    private EstadoFacade repositorio;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Estado estado = null;
        if (value != null) {
            Long id = new Long(value);
            estado = repositorio.find(id);
        }
        return estado;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Estado) value).getId().toString();
        } else {
            return "";
        }
    }
}
