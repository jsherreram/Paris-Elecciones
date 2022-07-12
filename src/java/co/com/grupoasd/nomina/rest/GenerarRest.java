/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.GeneraSolicitudesDao;
import co.com.grupoasd.nomina.modelo.NotificacionPago;
import co.com.grupoasd.nomina.modelo.Respuesta;
import co.com.grupoasd.nomina.negocio.solicitudes.GeneraSolicitudesBusiness;
import java.io.File;
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

/**
 *
 * @author Pedro Rodriguez
 */
@Path("/generar")
public class GenerarRest {

    @GET
    @Path("/terceros")
    @Consumes({"application/json", "application/xml"})
    public Response generarTerceros(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idDpto") String idDepartamento) {

        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        GeneraSolicitudesDao genera = new GeneraSolicitudesDao();

        genera.GenerarTerceros(idDepartamento);

        Respuesta respuesta = new Respuesta();
        respuesta.setCodigo("200");
        respuesta.setDescripcion("Archivo Generado");
        rb.status(200);
        rb.entity(respuesta);
        return rb.build();

    }

    @GET
    @Path("/listas")
    @Consumes({"application/json", "application/xml"})
    public Response generarListas(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento, @QueryParam("idDepartamento") String idDepartamento,
            @QueryParam("idMunicipio") String idMunicipio, @QueryParam("idCargo") String idCargo,
            @QueryParam("idZona") String idZona, @QueryParam("idPuesto") String idPuesto) {

        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        GeneraSolicitudesDao genera = new GeneraSolicitudesDao();

        genera.GenerarListas(idDepartamento, idMunicipio, idZona, idPuesto, idCargo, idEvento);

        Respuesta respuesta = new Respuesta();
        respuesta.setCodigo("200");
        respuesta.setDescripcion("Archivo Generado");
        rb.status(200);
        rb.entity(respuesta);
        return rb.build();

    }

    /**
     * Servicio encargado de generar el archivo cvs para exportar los datos de
     * las notificaciones de pago
     *
     * @param request
     * @param response
     * @param notificacion
     * @return
     */
    @POST
    @Path("/exportarNotificacionPagos")
    @Produces("application/vnd.ms-excel")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response exportarNotificacionPagos(@javax.ws.rs.core.Context HttpServletRequest request,
            @javax.ws.rs.core.Context HttpServletResponse response, NotificacionPago notificacion) {
        Response.ResponseBuilder responseBuilder = javax.ws.rs.core.Response.status(500);
        try {
            GeneraSolicitudesBusiness generarSolicitudes = new GeneraSolicitudesBusiness();
            File file = generarSolicitudes.generarArchivoNotificacionPagos(notificacion.getPrueba().getIdprueba());
            responseBuilder = Response.ok((Object) file);
            responseBuilder.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=new-excel-file.xls");
            responseBuilder.header(HttpHeaders.CONTENT_TYPE, "text/csv;charset=utf-8");
            Response responseData = responseBuilder.build();
            return responseData;
        } catch (Exception ex) {
            Logger.getLogger(DivitransRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return responseBuilder.build();
    }

    /**
     * Servicio encargado de obtener el archivo demo del cargue de
     * notificaciones de pago
     *
     * @param request
     * @param response
     * @return
     */
    @POST
    @Path("/demoNotificacionPago")
    @Produces("application/vnd.ms-excel")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response demoNotificacionPago(@javax.ws.rs.core.Context HttpServletRequest request,
            @javax.ws.rs.core.Context HttpServletResponse response) {
        Response.ResponseBuilder responseBuilder = javax.ws.rs.core.Response.status(500);
        try {
            GeneraSolicitudesBusiness generarSolicitudes = new GeneraSolicitudesBusiness();
            File file = generarSolicitudes.generarDemoNotificacionPago();
            responseBuilder = Response.ok((Object) file);
            responseBuilder.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=new-excel-file.xls");
            responseBuilder.header(HttpHeaders.CONTENT_TYPE, "text/csv;charset=utf-8");
            Response responseData = responseBuilder.build();
            return responseData;
        } catch (Exception ex) {
            Logger.getLogger(DivitransRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return responseBuilder.build();
    }
}
