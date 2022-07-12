/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.modelo.EstadosOrden;
import co.com.grupoasd.nomina.negocio.estadosOrden.EstadosOrdenController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author CristianAlexander
 */
@Path("/EstadosOrden")
public class EstadosOrdenRest {
    
    @GET
    @Path("/listar")
    @Produces({"application/json"})
    public List<EstadosOrden> listar(@javax.ws.rs.core.Context HttpServletRequest request) throws Exception{
        EstadosOrdenController estados = new EstadosOrdenController();
        return estados.listar();
    }
    
    
}
