/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.converter;

import com.mycompany.pdv.modelo.Fornecedor;
import com.mycompany.pdv.repositorio.FornecedorFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Gleywson
 */
@Named(value = "fornecedorConverter")
public class FornecedorConverter implements Converter {

    @Inject
    private FornecedorFacade repositorio;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Fornecedor fornecedor = null;
        if (value != null) {
            Long id = new Long(value);
            fornecedor = repositorio.find(id);
        }
        return fornecedor;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Fornecedor) value).getId().toString();
        } else {
            return "";
        }
    }
    
}
