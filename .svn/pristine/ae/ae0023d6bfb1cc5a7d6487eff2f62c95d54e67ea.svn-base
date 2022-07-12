/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.AsistenciaDao;
import co.com.grupoasd.nomina.dao.PersonaAsignadaDao;
import co.com.grupoasd.nomina.modelo.PersonaAsignada;
import co.com.grupoasd.nomina.modelo.Respuesta;
import co.com.grupoasd.nomina.negocio.asistencia.AsistenciaBusiness;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author Pedro Rodriguez
 */
@Path("/personaAsignadaAsistencia")
public class PersonaAsignadaAsistenciaRest {

    @PUT
    @Consumes({"application/json", "application/xml"})
    public Response modificar(@javax.ws.rs.core.Context HttpServletRequest request, PersonaAsignada p) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        AsistenciaDao asistencia = new AsistenciaDao();
        Boolean resultado;

        if (p.isAsistio() == true) {
            AsistenciaBusiness asistenciaB = new AsistenciaBusiness();
            resultado = asistenciaB.marcarAsistencia(p.getEmpleado().getIdEmpleado(), p.getEvento().getCodigoEvento(), p.getIddivipol(), false, request.getUserPrincipal().getName());
        } else {
            resultado = asistencia.EliminarAsistencia(p.getEmpleado().getIdEmpleado(), p.getEvento().getCodigoEvento(), p.getIddivipol());
        }

        if (resultado) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Actualizado");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("No se actualizo el registro.");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

}
