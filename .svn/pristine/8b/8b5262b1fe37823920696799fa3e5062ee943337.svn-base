/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dto.BusquedaPagosDto;
import co.com.grupoasd.nomina.negocio.pagos.PagosBusiness;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Pedro Rodríguez
 */

@Path("/pago")
public class PagoRest {
    
    
    /**
     * Servicio encargado de generar el archivo cvs con la nomina del departamento
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @POST
    @Path("/exportarPagos")
    @Produces("application/vnd.ms-excel")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response exportarPagos(@javax.ws.rs.core.Context HttpServletRequest request,
            @javax.ws.rs.core.Context HttpServletResponse response, BusquedaPagosDto data) {
        Response.ResponseBuilder responseBuilder = javax.ws.rs.core.Response.status(500);
        try {
            PagosBusiness pagosBusiness = new PagosBusiness();
            File file = pagosBusiness.generaArchivoPagos(data.getCodigoDepartamento(), data.getNombreDepartamento(), data.getIdPrueba());
            responseBuilder = Response.ok((Object) file);
            responseBuilder.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+ data.getCodigoDepartamento()+ "-" + data.getNombreDepartamento()  +".xls");
            responseBuilder.header(HttpHeaders.CONTENT_TYPE, "text/csv;charset=utf-8");
            Response responseData = responseBuilder.build();
            return responseData;
        } catch (Exception ex) {
            Logger.getLogger(PagoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return responseBuilder.build();
    }
    
}
