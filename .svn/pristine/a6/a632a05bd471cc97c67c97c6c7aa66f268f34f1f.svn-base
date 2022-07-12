/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.ZonaIcfesDao;
import co.com.grupoasd.nomina.modelo.ZonaIcfes;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Pedro Rodríguez
 */

@Path("/zonaIcfes")
public class ZonaIcfesRest {
    
    
    @GET
    @Path("/listar")
    @Produces({"application/json","application/xml"})
    public List<ZonaIcfes> listar() {
        ZonaIcfesDao zonaDao = new ZonaIcfesDao();
        return zonaDao.listar();
    }
    
    
}
