/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.EncuestaDao;
import co.com.grupoasd.nomina.dao.ParametroEncuestaDao;
import co.com.grupoasd.nomina.modelo.Encuesta;
import co.com.grupoasd.nomina.modelo.ParametroEncuesta;
import co.com.grupoasd.nomina.modelo.Respuesta;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Pedro Rodriguez
 */
@Path("/parametroencuesta")
public class ParametroEncuestaRest {

    @GET
    @Path("/buscarPorHora")
    @Produces({"application/xml", "application/json"})
    public ParametroEncuesta find(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento) {

        Date fecha = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
        ParametroEncuestaDao dao = new ParametroEncuestaDao();
        return dao.GetParametroEvento(idEvento, ft.format(fecha));
    }
    
     @GET
    @Path("/listarParametrosPorEvento")
    @Produces({"application/xml", "application/json"})
    public List<ParametroEncuesta> listarPorEvento(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento) {

        ParametroEncuestaDao dao = new ParametroEncuestaDao();
        return dao.listarParametrosEncuestaPorEvento(idEvento);
    }
}
