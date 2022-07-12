/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.CierreSesionDao;
import co.com.grupoasd.nomina.modelo.CierreAsistencia;
import co.com.grupoasd.nomina.modelo.Respuesta;
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
 * @author Pedro Rodríguez
 */

@Path("/CierreSesion")
public class CierreSesionRest {
    
    
    @GET
    @Path("/listarJsonCierres")
    @Produces({"application/json", "application/xml"})
    public String listarJsonCierres(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("nrodoc") int nrodoc, @QueryParam("idPrueba") int idPrueba) {
        CierreSesionDao objController = new CierreSesionDao();
        return objController.listarJsonCierre(nrodoc, idPrueba);

    }
    
    @GET
    @Path("/listarJsonActualizame")
    @Produces({"application/json", "application/xml"})
    public String listarJsonActualizame(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("nrodoc") int nrodoc, @QueryParam("idPrueba") int idPrueba) {
        CierreSesionDao objController = new CierreSesionDao();
        return objController.listarJsonActualizame(nrodoc, idPrueba);

    }
            
    @POST
    @Path("/ReversarCierre")
    @Consumes({"application/json"})
    public Response ReversarCierre(CierreAsistencia cierre) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        CierreSesionDao reversarcierre = new CierreSesionDao();

        if(reversarcierre.ReversarCierre(cierre) == true )
        {       Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("200");
                respuesta.setDescripcion("Registros Actualizados");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
        }else
        {       Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("201");
                respuesta.setDescripcion("No se pudo Actualizar");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
        }
    }
    
    @GET
    @Path("/buscarCierre")
    @Produces({"application/json", "application/xml"})
    public List<CierreAsistencia> existeCierre(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idDivipol") int idDivipol, @QueryParam("idEvento") int idEvento) {
        
      CierreSesionDao dao=new CierreSesionDao();
      return dao.buscarCierre(idDivipol, idEvento);

    }

}
