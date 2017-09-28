/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.converter;

import com.mycompany.pdv.modelo.Marca;
import com.mycompany.pdv.repositorio.MarcaFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Gleywson
 */
@Named(value = "marcaConverter")
public class MarcaConverter implements Converter {

    @Inject
    private MarcaFacade repositorio;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Marca marca = null;
        if (value != null) {
            Long id = new Long(value);
            marca = repositorio.find(id);
        }
        return marca;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value != null) {
            return ((Marca) value).getId().toString();
        } else {
            return "";
        }
    }

}
