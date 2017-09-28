/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.converter;

import com.mycompany.pdv.modelo.SubCategoria;
import com.mycompany.pdv.repositorio.SubCategoriaFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Gleywson
 */
@Named(value = "subcategoriaConverter")
public class SubcategoriaConverter implements Converter {

     @Inject
    private SubCategoriaFacade repositorio;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        SubCategoria categoria = null;
        if (value != null) {
            Long id = new Long(value);
            categoria = repositorio.find(id);
        }
        return categoria;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value != null) {
            return ((SubCategoria) value).getId().toString();
        } else {
            return "";
        }
    }
}
