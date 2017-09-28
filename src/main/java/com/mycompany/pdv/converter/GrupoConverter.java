/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.converter;


import com.mycompany.pdv.modelo.Grupo;
import com.mycompany.pdv.repositorio.GrupoFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Gleywson
 */
@Named(value = "grupoConverter")
public class GrupoConverter implements Converter {

    @Inject
    private GrupoFacade repositorio;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Grupo grupo = null;
        if (value != null) {
            Long id = new Long(value);
            grupo = repositorio.find(id);
        }
        return grupo;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Grupo) value).getId().toString();
        } else {
            return "";
        }
    }
    
}
