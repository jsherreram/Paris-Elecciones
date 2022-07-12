/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.EstadoEmpleadoDao;
import co.com.grupoasd.nomina.estadoempleado.EstadoEmpleadoController;
import co.com.grupoasd.nomina.modelo.EstadoEmpleado;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Pedro Rodriguez
 */
@Path("/EstadosPersonal")
public class EstadosEmpleados {
    
    @GET
    @Path("/listar")
    @Produces({"application/json","application/xml"})
    public List<EstadoEmpleado> listar(@javax.ws.rs.core.Context HttpServletRequest request) {
        EstadoEmpleadoController estados = new EstadoEmpleadoController();
        return estados.listar();
    }
    
    @GET
    @Path("/listarEstadosIcfes")
    @Produces({"application/json","application/xml"})
    public List<EstadoEmpleado> listarEstadosIcfes(@javax.ws.rs.core.Context HttpServletRequest request) {
        EstadoEmpleadoDao dao = new EstadoEmpleadoDao();
        return dao.listar();
    }
    
}

