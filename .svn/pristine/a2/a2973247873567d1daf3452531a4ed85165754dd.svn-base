/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.SoporteAsistenciaDao;
import co.com.grupoasd.nomina.modelo.SoporteAsistencia;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Pedro Rodriguez
 */
@Path("/soporteAsistencia")
public class SoporteAsistenciaRest {
    
    @GET
    @Path("/listar")
    @Produces({"application/xml", "application/json"})
    public List<SoporteAsistencia> listar(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento, @QueryParam("idDepartamento") String idDepartamento, 
            @QueryParam("idMunicipio") String idMunicipio, @QueryParam("idCargo") String idCargo, 
            @QueryParam("idZona") String idZona, @QueryParam("idPuesto") String idPuesto) {
        SoporteAsistenciaDao soporteAsistenciaDao = new SoporteAsistenciaDao();
        return soporteAsistenciaDao.listar(idEvento, idDepartamento, idMunicipio, idCargo, idZona, idPuesto);
    }
    
    @POST
    @Consumes({"application/json"})
    public Response crear(SoporteAsistencia s) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        SoporteAsistenciaDao soporteAsistenciaDao = new SoporteAsistenciaDao();
        soporteAsistenciaDao.insertar(s);
        rb.status(200);
        rb.entity(s);
        
        return rb.build();
    }
    
    
}