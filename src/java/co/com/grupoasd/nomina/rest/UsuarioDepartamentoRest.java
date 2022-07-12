/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.UsuarioDepartamentoDao;
import co.com.grupoasd.nomina.modelo.UsuarioDepartamento;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Pedro Rodriguez
 */
@Path("/UsuarioDepartamento")
public class UsuarioDepartamentoRest {
    
    @GET
    @Path("/listar")
    @Produces({"application/json","application/xml"})
    public List<UsuarioDepartamento> listar(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("usuario") String usuario, @QueryParam("idPrueba") int idPrueba) {
        
        UsuarioDepartamentoDao usuarioDepartamentoDao = new UsuarioDepartamentoDao();
        return usuarioDepartamentoDao.listar(usuario,idPrueba);
        
    }
     
    
}
