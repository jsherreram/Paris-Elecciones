/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.modelo.StatusCargue;
import co.com.grupoasd.nomina.negocio.Divitrans.DivitransBussines;
import co.com.grupoasd.nomina.negocio.Divitrans.IDivitrans;
import co.com.grupoasd.nomina.negocio.statusCargue.IStatus;
import co.com.grupoasd.nomina.negocio.statusCargue.StatusBusiness;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Pedro Rodriguez
 */
@Path("/statuscargue")
public class StatusCargueRest {

    @GET
    @Path("/listar")
    @Produces({"application/json"})
    public List<StatusCargue> listar(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idTipoCargue") int idTipoCargue) {
        IStatus statusBussines = new StatusBusiness();
        String usuario = request.getUserPrincipal().getName();
        return statusBussines.listar(usuario, idTipoCargue);
    }

    @GET
    @Path("/listarPorRegistro")
    @Produces({"application/json"})
    public List<StatusCargue> listar(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idTipoCargue") int idTipoCargue, @QueryParam("identificadorRegistro") int identificadorRegistro) {
        IStatus statusBussines = new StatusBusiness();
        String usuario = request.getUserPrincipal().getName();
        return statusBussines.listar(usuario, idTipoCargue, identificadorRegistro);
    }

    /**
     * Servicio encargado de generar el archivo cvs para exportar los viaticos
     * de la prueba
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @POST
    @Path("/generarReporteErrores")
    @Produces("application/vnd.ms-excel")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generarReporteErrores(@javax.ws.rs.core.Context HttpServletRequest request,
            @javax.ws.rs.core.Context HttpServletResponse response, String data) {
        Response.ResponseBuilder responseBuilder = javax.ws.rs.core.Response.status(500);
        try {
            JSONObject json = new JSONObject(data);
            int idStatusCargue = json.getInt("idStatusCargue");
            IStatus statusBussines = new StatusBusiness();
            String usuario = request.getUserPrincipal().getName();
            File file = statusBussines.generarReporteErrores(idStatusCargue);
            responseBuilder = Response.ok((Object) file);
            responseBuilder.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=new-excel-file.xls");
            responseBuilder.header(HttpHeaders.CONTENT_TYPE, "text/csv;charset=utf-8");
            Response responseData = responseBuilder.build();
            return responseData;
        } catch (JSONException ex) {
            Logger.getLogger(DivitransRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return responseBuilder.build();
    }

}
