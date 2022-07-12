/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.EstadoDao;
import co.com.grupoasd.nomina.modelo.Estado;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Pedro Rodriguez
 */
@Path("/estado")
public class EstadoRest {
    @GET
    @Path("/listar")
    @Produces({"application/json","application/xml"})
    public List<Estado> listar() {
        EstadoDao estadoDao = new EstadoDao();
        return estadoDao.listar();
    }

    @GET
    @Path("/buscar")
    @Produces({"application/xml", "application/json"})
    public Estado find(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("id") String id) {
        EstadoDao estadoDao = new EstadoDao();
        return estadoDao.GetById(id);
    }
    
}
