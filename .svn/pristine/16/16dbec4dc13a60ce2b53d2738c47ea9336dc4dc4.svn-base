/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.ListaAsistenciaDao;
import co.com.grupoasd.nomina.dao.SoporteAsistenciaDao;
import co.com.grupoasd.nomina.modelo.ListaAsistencia;
import co.com.grupoasd.nomina.modelo.SoporteAsistencia;
import co.com.grupoasd.nomina.portal.servlet.Reporte;
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

@Path("/listaAsistencia")
public class ListaAsistenciaRest {
    
    @GET
    @Path("/getById")
    @Produces({"application/xml", "application/json"})
    public ListaAsistencia getById(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento, @QueryParam("idDepartamento") String idDepartamento, 
            @QueryParam("idMunicipio") String idMunicipio, @QueryParam("idCargo") String idCargo, 
            @QueryParam("idZona") String idZona, @QueryParam("idPuesto") String idPuesto) {
        ListaAsistenciaDao listaDao = new ListaAsistenciaDao();
        return listaDao.getById(idDepartamento, idMunicipio, idZona, idPuesto, idCargo, idEvento);
    }
    
    
}
