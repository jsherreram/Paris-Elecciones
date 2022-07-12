/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.ActividadDao;
import co.com.grupoasd.nomina.modelo.Actividad;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Pedro Rodríguez
 */

@Path("/actividadEconomica")
public class ActividadRest {
    
    
    @GET
    @Path("/listar")
    @Produces({"application/json","application/xml"})
    public List<Actividad> listar() {
        ActividadDao actividadDao = new ActividadDao();
        return actividadDao.listar();
    }
    
    
}
