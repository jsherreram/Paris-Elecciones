/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.EmpleadoDao;
import co.com.grupoasd.nomina.dao.TareaConfirmacionDao;
import co.com.grupoasd.nomina.modelo.Respuesta;
import co.com.grupoasd.nomina.modelo.TareaConfirmacion;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ASD
 */
@Path("/confirmacion")
public class Confirmacion {

    @GET
    @Path("/listarEmpleados")
    @Produces({"application/json"})
    public String listarEmpleadosConfirmados(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba, @QueryParam("idEmp") int idEmp) {
        TareaConfirmacionDao confirmacionDao = new TareaConfirmacionDao();
        return confirmacionDao.listarConfirmacionTipo(idPrueba, idEmp);

    }

    @POST
    @Path("/firmarCuentaDeCobro")
    @Consumes({"application/json"})
    public Response actualizarEstadoEmpleado(String data) throws JSONException {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        JSONObject json = new JSONObject(data);

        Long nrodoc= json.getLong("nrodoc");
        int idEmpleado = new EmpleadoDao().GetIdByNumeroDocumento(nrodoc);
        TareaConfirmacion tarea = new TareaConfirmacion();
        tarea.setIdEmpleado(idEmpleado);
        tarea.setCodigoCargo("10");
        Date fecha = new Date();
        tarea.setFechaConfirmacion(fecha);
        tarea.setFormaConfirmacion("Huella");
        tarea.setTipo("cuenta de cobro");
        tarea.setIdPrueba(38);
        tarea.setEstado(0);

        TareaConfirmacionDao tareaDao = new TareaConfirmacionDao();
        if (tareaDao.insertar(tarea)) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Estado Actualizado");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Error al guardar.");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

}
