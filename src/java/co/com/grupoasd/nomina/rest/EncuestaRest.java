/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.EncuestaDao;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.Encuesta;
import co.com.grupoasd.nomina.modelo.Respuesta;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 *
 * @author Pedro Rodriguez
 */
@Path("/encuesta")
public class EncuestaRest {

    @GET
    @Path("/buscar")
    @Produces({"application/xml", "application/json"})
    public Encuesta find(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento, @QueryParam("idDivipol") int idDivipol) {

        HttpSession sesion = request.getSession();
        EmpleadoSesion emp = new EmpleadoSesion();
        emp = (EmpleadoSesion) sesion.getAttribute("empleado");
        EncuestaDao encuestaDao = new EncuestaDao();
        Encuesta encuesta = encuestaDao.GetEvento(idEvento, idDivipol);
        emp.setEstadoEncuesta(encuesta.getEstadoEncuesta());
        sesion.setAttribute("empleado", emp);
        return encuesta;
    }

    @GET
    @Path("/buscarPorSitio")
    @Produces({"application/xml", "application/json"})
    public Encuesta BuscarPorSitio(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento, @QueryParam("idDivipol") int idDivipol) {

        EncuestaDao encuestaDao = new EncuestaDao();
        return encuestaDao.GetEncuestaEventoSitio(idEvento, idDivipol);
    }

    @PUT
    @Consumes({"application/json", "application/xml"})
    public Response modificar(Encuesta e, @javax.ws.rs.core.Context HttpServletRequest request) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        HttpSession sesion = request.getSession();

        EmpleadoSesion emp = new EmpleadoSesion();
        emp = (EmpleadoSesion) sesion.getAttribute("empleado");
        EncuestaDao encuestaDao = new EncuestaDao();
        if (e.getIdEncuesta() == 0) {
            if (encuestaDao.insertar(e)) {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("200");
                respuesta.setDescripcion("Registro Exitoso");
                rb.status(200);
                rb.entity(respuesta);
                emp.setEstadoEncuesta(e.getEstadoEncuesta());
                sesion.setAttribute("empleado", emp);
                return rb.build();
            } else {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("201");
                respuesta.setDescripcion("No se registró");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
            }

        } else if (encuestaDao.actualizar(e)) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Actualizado");
            rb.status(200);
            rb.entity(respuesta);
            emp.setEstadoEncuesta(e.getEstadoEncuesta());
            sesion.setAttribute("empleado", emp);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("No se actualizo");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }

    }

    @GET
    @Path("/encuestaSesion")
    @Produces({"application/json"})
    public String getEncuestaSesion(@javax.ws.rs.core.Context HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        EmpleadoSesion emp = new EmpleadoSesion();
        emp = (EmpleadoSesion) sesion.getAttribute("empleado");
        Date fecha = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        JSONObject objJson = (JSONObject) new JSONObject();

        objJson.put("estado", emp.getEstadoEncuesta());
        objJson.put("hora", ft.format(fecha));
        objJson.put("fecha", sm.format(fecha));

        return objJson.toJSONString();

    }

}
