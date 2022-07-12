/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.modelo.Grupo;
import co.com.grupoasd.nomina.negocio.grupo.GrupoController;
import co.com.grupoasd.nomina.negocio.grupo.IGrupoController;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author ASD
 */
@Stateless
@Path("/grupo")
public class GrupoRest {
    
    
    @GET
    @Path("/listarGruposFuncionarios")
    @Produces({"application/json", "application/xml"})
    public List<Grupo> listarGruposFuncionarios() {
        List<Grupo> lstGrupos = new ArrayList<>();
        try {
            IGrupoController objController = new GrupoController();
            lstGrupos = objController.listarGruposFuncionarios();
        } catch (Exception ex) {
            Logger.getLogger(MedioPagoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstGrupos;
    }
}
