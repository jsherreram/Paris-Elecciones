/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.negocio.usuario.UsuarioController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


/**
 *
 * @author Pedro Rodriguez
 */
@Path("/UsuarioLogin")
public class UsuarioLoginRest {

    @GET
    @Path("/info")
    @Produces({"application/json"})
    public EmpleadoSesion listar(@javax.ws.rs.core.Context HttpServletRequest request) {

        return (EmpleadoSesion) request.getAttribute("empleado");
    }
    
    @POST
    @Path("/restablecerContrasena")    
    @Consumes({"application/json"})    
    public Boolean resetearContrasena(@javax.ws.rs.core.Context HttpServletRequest request, String usuario) throws Exception {
        JSONObject usuarioJson = (JSONObject) JSONValue.parse(usuario);
        UsuarioController usuarioC = new UsuarioController();        
        return usuarioC.recuperarContraseña(usuarioJson.get("idEmpleado").toString(), Boolean.TRUE);        
    }   
    
    @POST
    @Path("/aplicaBloqueo")
    @Consumes({"application/json"})    
    public Boolean aplicarBloqueo(@javax.ws.rs.core.Context HttpServletRequest request,EmpleadoSesion e) throws Exception{              
        UsuarioController usuarioC = new UsuarioController();        
        return usuarioC.bloqueaUsuario(e.getIdEmpleado().toString());        
    }
    
    @POST
    @Path("/verificaCambio")
    @Consumes({"application/json"})  
    public Boolean verificaCambioContraseña(@javax.ws.rs.core.Context HttpServletRequest request,EmpleadoSesion e) throws Exception{
        UsuarioController usuarioC = new UsuarioController(); 
        return usuarioC.compruebaCambioContrasena(e);
    }
                  
}
