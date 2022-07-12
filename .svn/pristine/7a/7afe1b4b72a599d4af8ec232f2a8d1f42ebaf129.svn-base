/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.cargueaplicacion.EdicionCuadroController;
import co.com.grupoasd.nomina.dao.EdicionCuadroDao;
import co.com.grupoasd.nomina.dto.DataFiltros;
import co.com.grupoasd.nomina.modelo.CuadroAp;
import co.com.grupoasd.nomina.modelo.Respuesta;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.json.JSONException;

/**
 *
 * @author Pedro Rodríguez
 */

@Path("/EdicionCuadro")
public class EdicionCuadroRest {
    @GET
    @Path("/listar")
    @Produces({"application/json", "application/xml"})
    public String ConsultarCuadroAplicacion(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idPrueba") int idPrueba,
            @QueryParam("codigoSitio") String codigoSitio) {
        EdicionCuadroController objController = new EdicionCuadroController();
        return objController.ConsultarCuadroAplicacion(idPrueba, codigoSitio).toString();
    }

    @POST
    @Path("/filtros")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String filtros(@javax.ws.rs.core.Context HttpServletRequest request, DataFiltros data) throws JSONException {
        EdicionCuadroController objController = new EdicionCuadroController();
        return objController.ConsultarFiltros(data).toString();
    }

    @POST
    @Path("/consulta")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String consulta(@javax.ws.rs.core.Context HttpServletRequest request, DataFiltros data) throws JSONException {
        EdicionCuadroController objController = new EdicionCuadroController();
        return objController.Consulta(data).toString();
    }

    @GET
    @Path("/listarPrueba")
    @Produces({"application/json", "application/xml"})
    public String listarPrueba(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idPrueba") int idPrueba,
            @QueryParam("usuario") String usuario) {
        EdicionCuadroController objController = new EdicionCuadroController();
        return objController.ConsultarCuadroPrueba(idPrueba, usuario).toString();
    }

    @PUT
    @Path("/actualizar")
    @Consumes({"application/json"})
    public Response actualizar(CuadroAp cuadro) {
        ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        EdicionCuadroDao cp = new EdicionCuadroDao();
        if (cp.actualizar(cuadro)) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Actualizado");
            rb.status(200);
            rb.entity(respuesta);
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
}
