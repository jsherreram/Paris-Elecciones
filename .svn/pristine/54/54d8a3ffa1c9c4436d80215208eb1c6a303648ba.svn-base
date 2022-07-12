/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.OperadorCelularDao;
import co.com.grupoasd.nomina.modelo.OperadorCelular;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


/**
 *
 * @author ASD
 */
@Path("/operadorcelular")
public class OperadorCelularRest {

    @GET
    @Path("/listarOperadores")
    @Produces({"application/json"})
    public List<OperadorCelular> listarOperadores(@javax.ws.rs.core.Context HttpServletRequest request) {
        OperadorCelularDao dao = new OperadorCelularDao();
        return dao.listar();
    }

}
