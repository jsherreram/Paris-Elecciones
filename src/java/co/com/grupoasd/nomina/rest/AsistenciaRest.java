/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.AsignacionSalonDao;
import co.com.grupoasd.nomina.dao.AsistenciaDao;
import co.com.grupoasd.nomina.dao.PersonaAsignadaDao;
import co.com.grupoasd.nomina.modelo.AsignacionSalon;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.Nombramiento;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.Respuesta;
import co.com.grupoasd.nomina.negocio.asistencia.AsistenciaBusiness;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 *
 * @author PEDRO RODRIGUEZ
 */
@Path("/asistencia")
public class AsistenciaRest {

    @GET
    @Path("/listarAsistenciaXSitio")
    @Produces({"application/json"})
    public String listarAsistenciaXSitio(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento, @QueryParam("codigoSitio") String codigoSitio) {
        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        return personaAsignadaDao.listarAsistenciaXSitio(idEvento, codigoSitio);
    }

    @GET
    @Path("/resumenAsistenciaXSitio")
    @Produces({"application/json"})
    public String resumenAsistenciaXSitio(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento, @QueryParam("codigoSitio") String codigoSitio) {
        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        return personaAsignadaDao.resumenAsistenciaXSitio(idEvento, codigoSitio);
    }

    @GET
    @Path("/getAsignacion")
    @Produces({"application/json"})
    public String getAsignacion(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento,
            @QueryParam("codigoSitio") String codigoSitio,
            @QueryParam("nrodoc") long nrodoc) {
        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        return personaAsignadaDao.getAsignacion(idEvento, codigoSitio, nrodoc);
    }

    @POST
    @Path("/marcarAsistencia")
    @Produces({"application/json"})
    public Response marcarAsistencia(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento,
            @QueryParam("idDivipol") int idDivipol,
            @QueryParam("idEmpleado") int idEmpleado,
            @QueryParam("biometrico") boolean biometrico) {

        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        AsistenciaBusiness asistenciaB = new AsistenciaBusiness();

        String usuario = request.getUserPrincipal().getName();
        boolean insert = asistenciaB.marcarAsistencia(idEmpleado, idEvento, idDivipol, biometrico, usuario);

        if (insert) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Asistencia Agregada");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("No se asigno agrego la asistencia");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }

    }

    @GET
    @Path("/marcarAsistencia2")
    @Produces({"application/json"})
    public Response marcarAsistencia2(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento,
            @QueryParam("idDivipol") int idDivipol,
            @QueryParam("idEmpleado") int idEmpleado,
            @QueryParam("biometrico") boolean biometrico) {

        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        String usuario = request.getUserPrincipal().getName();
        AsistenciaBusiness asistenciaB = new AsistenciaBusiness();
        boolean insert = asistenciaB.marcarAsistencia(idEmpleado, idEvento, idDivipol, biometrico, usuario);

        if (insert) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Asistencia Agregada");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("No se asigno agrego la asistencia");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }

    }

    @GET
    @Path("/validaAsistenciaEmpleado")
    @Produces({"application/json"})
    public String getAsistenciaEmpleado(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento, @QueryParam("idDivipol") int idDivipol,
            @QueryParam("idEmpleado") int idEmpleado) {

        AsistenciaDao dao = new AsistenciaDao();
        JSONObject objJson = (JSONObject) new JSONObject();
        objJson.put("respuesta", dao.ValidaTieneAsistencia(idEvento, idDivipol, idEmpleado));

        return objJson.toJSONString();

    }

    @GET
    @Path("/guardaValidacionAsistencia")
    @Produces({"application/json"})
    public Response guardaValidacionAsistenci(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento,
            @QueryParam("idDivipol") int idDivipol,
            @QueryParam("idEmpleado") int idEmpleado,
            @QueryParam("validacion") boolean validacion) {

        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        AsistenciaDao asisdao= new AsistenciaDao();

        String usuario = request.getUserPrincipal().getName();
        boolean insert = asisdao.guardaValidacionAsistencia(idEmpleado, idEvento, idDivipol, validacion, usuario);

        if (insert) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Asistencia Validada");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("No se guardó la validación de la asistencia");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }

    }

}
