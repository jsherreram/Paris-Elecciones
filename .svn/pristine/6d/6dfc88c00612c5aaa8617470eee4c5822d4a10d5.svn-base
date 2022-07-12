/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.EventoDao;
import co.com.grupoasd.nomina.modelo.Evento;
import co.com.grupoasd.nomina.modelo.Pago_Conciliado;
import co.com.grupoasd.nomina.modelo.Respuesta;
import co.com.grupoasd.nomina.negocio.pagoconciliado.PagoConciliadoController;
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

/**
 *
 * @author Pedro Rodríguez
 */

@Path("/pagosconciliacion")
public class PagosConciliacionRest {
    
    @GET
    @Path("/consultarpagos")
    @Produces({"application/json"})
    public String ConsultarPagos(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idPrueba") int idPrueba,
            @QueryParam("codigoSitio") String codigoSitio) {
        PagoConciliadoController controller = new PagoConciliadoController();
        return controller.ConsultarPagos(idPrueba, codigoSitio).toString();
    }

    @POST
    @Consumes({"application/json"})
    public Response crear(Pago_Conciliado p) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        PagoConciliadoController controller = new PagoConciliadoController();
        
        int pagoConciliado = controller.insertarPagoConciliado(p);
        if (pagoConciliado > 0) {
            p.setId(pagoConciliado);
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Creado");
            respuesta.setId(pagoConciliado);
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("se genero un problema al insertar el pago");
            respuesta.setId(pagoConciliado);
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    @PUT
    @Consumes({"application/json"})
    public Response actualizar(Pago_Conciliado p) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        PagoConciliadoController controller = new PagoConciliadoController();
        
        boolean resultado = controller.actualizarPagoConciliado(p);
        if (resultado == true) {
            p.setId(0);
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Actualizado");
            respuesta.setId(0);
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("se genero un problema al insertar el pago");
            respuesta.setId(0);
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }
     @GET
    @Path("/consultarCuadreCaja")
    @Produces({"application/json"})
    public String ConsultarCuadreCaja(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idPrueba") int idPrueba) {
        PagoConciliadoController controller = new PagoConciliadoController();
        return controller.ConsultarCuadreCaja(idPrueba).toString();
    }
    
    
    
}
