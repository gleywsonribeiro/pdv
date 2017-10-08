/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pdv.controller.relatorio;

import com.mycompany.pdv.repositorio.CidadeFacade;
import com.mycompany.pdv.service.report.ExecutorRelatorio;
import com.mycompany.pdv.util.jsf.util.JsfUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Gleywson
 */
@Named(value = "fichaEstoqueController")
@RequestScoped
public class FichaEstoqueController {

    private FacesContext facesContext = FacesContext.getCurrentInstance();

    private HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

//    @PersistenceContext(unitName = "pdvPU")
//    private EntityManager manager;
    //@Resource(mappedName = "java:app/pdv")
    private DataSource source;

    public void emitir() {
        try {
            Map<String, Object> parametros = new HashMap<String, Object>();

            ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/estoque.jasper",
                    this.response, parametros, "ficha_de_estoque.xls");
            InitialContext context = new InitialContext();
            
            source = (DataSource) context.lookup("java:app/pdv");
            
            Connection connection = source.getConnection();
            System.out.println(connection == null?"Esta nula":"esta ok");
            executor.executeToPdf(connection);

            if (executor.isRelatorioGerado()) {
                facesContext.responseComplete();
            } else {
                JsfUtil.addErrorMessage("A execução do relatório não retornou dados.");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FichaEstoqueController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(FichaEstoqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
