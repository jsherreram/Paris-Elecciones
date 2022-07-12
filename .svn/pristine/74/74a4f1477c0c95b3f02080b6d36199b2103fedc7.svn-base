/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.AsignacionSuplenteDao;
import co.com.grupoasd.nomina.modelo.CierreAsistencia;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.Respuesta;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 *
 * @author Pedro Rodríguez
 */
@Path("/asignarsuplente")
public class AsignarSuplenteRest {

    @GET
    @Path("/obtenerSuplentes")
    @Produces({"application/json"})
    public String obtenerSuplentes(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("dcpe") int dcpe) {
        if (request.getUserPrincipal() != null) {
            AsignacionSuplenteDao asignarBussines = new AsignacionSuplenteDao();
            return asignarBussines.obtenerSuplentes(dcpe);
        } else {
            return "";
        }
    }

    @GET
    @Path("/GetRamdon")
    @Produces({"application/json"})
    public String GetRamdon(@javax.ws.rs.core.Context HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            AsignacionSuplenteDao asignarBussines = new AsignacionSuplenteDao();
            return asignarBussines.GetRamdon();
        } else {
            return "";
        }
    }

    @GET
    @Path("/obtenerfalton")
    @Produces({"application/json"})
    public String obtenerfalton(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("dcpe") int dcpe) {
        if (request.getUserPrincipal() != null) {
            AsignacionSuplenteDao asignarBussines = new AsignacionSuplenteDao();
            return asignarBussines.obtenerfalton(dcpe);
        } else {
            return "";
        }
    }

    @GET
    @Path("/obtenerFaltantes")
    @Produces({"application/json"})
    public String obtenerFaltantes(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idPrueba") int idPrueba, @QueryParam("idDivipol") int idDivipol, @QueryParam("codigoEvento") int codigoEvento) {
        if (request.getUserPrincipal() != null) {
            AsignacionSuplenteDao asignarBussines = new AsignacionSuplenteDao();
            return asignarBussines.obtenerFaltantes(idPrueba, idDivipol, codigoEvento);
        } else {
            return "";
        }
    }

    @POST
    @Path("/Actualizar")
    @Consumes({"application/json"})
    public Response Actualizar(Prueba prueba) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        AsignacionSuplenteDao asignarBussines = new AsignacionSuplenteDao();

        boolean estado = asignarBussines.Actualizar(prueba.getIdprueba(), prueba.getDias());
        if (estado == true) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registros Actualizados");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("No se pudo Actualizar");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    @POST
    @Path("/CierreAsistencia")
    @Consumes({"application/json"})
    public Response CierreAsistencia(CierreAsistencia cierre) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        AsignacionSuplenteDao asignarBussines = new AsignacionSuplenteDao();

        int estado = asignarBussines.insertarCierreAsistencia(cierre);
        if (estado > 0) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registros Actualizados");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("No se pudo Actualizar");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }
    
}
