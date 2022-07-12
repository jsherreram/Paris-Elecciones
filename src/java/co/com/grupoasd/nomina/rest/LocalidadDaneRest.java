/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.LocalidadDaneDao;
import co.com.grupoasd.nomina.dao.ZonaIcfesDao;
import co.com.grupoasd.nomina.modelo.LocalidadDane;
import co.com.grupoasd.nomina.modelo.ZonaIcfes;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Pedro Rodríguez
 */

@Path("/localidad")
public class LocalidadDaneRest {
    
    
    @GET
    @Path("/listar")
    @Produces({"application/json","application/xml"})
    public List<LocalidadDane> listar(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idMunicipio") String codMunicipio) {
        LocalidadDaneDao localidadDao = new LocalidadDaneDao();
        return localidadDao.listar(codMunicipio);
    }
    
}
