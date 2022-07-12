/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.modelo.CantidadPds;
import co.com.grupoasd.nomina.modelo.Pds;
import co.com.grupoasd.nomina.negocio.pds.PdsController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author CristianAlexander
 */
@Path("/pds")
public class PdsRest {

    @GET
    @Path("/listarpdsxSitio")
    @Produces({"application/json"})
    public List<Pds> listarpdsxSitio(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("sitio") String sitio, @QueryParam("evento") String evento) throws Exception {
        PdsController pds = new PdsController();
        return pds.listarpdsxSitio(sitio, evento);
    }

    @GET
    @Path("/cantidadCargos")
    @Produces({"application/json"})
    public CantidadPds cantidadCargos(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("sitio") String sitio, @QueryParam("cargo") String cargo, @QueryParam("evento") String evento) {
        PdsController pds = new PdsController();
        return pds.cantidadCargosDisponibles(sitio, cargo, evento);
    }

}
