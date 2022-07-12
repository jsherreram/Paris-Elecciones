/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.UsuarioSitioDao;
import co.com.grupoasd.nomina.modelo.Sitio;
import co.com.grupoasd.nomina.modelo.UsuarioSitio;
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
@Path("/UsuarioSitio")
public class UsuarioSitioRest {
    
    @GET
    @Path("/listar")
    @Produces({"application/json","application/xml"})
    public List<Sitio> listar(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("usuario") String usuario, @QueryParam("idPrueba") int idPrueba) {
        
        UsuarioSitioDao usuarioSitioDao = new UsuarioSitioDao();
        return usuarioSitioDao.listar(usuario, idPrueba);
        
    }
     
    @GET
    @Path("/listarSinCierre")
    @Produces({"application/json","application/xml"})
    public List<Sitio> listarSinCierre(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("usuario") String usuario, @QueryParam("idPrueba") int idPrueba) {
        
        UsuarioSitioDao usuarioSitioDao = new UsuarioSitioDao();
        return usuarioSitioDao.listarsinCierre(usuario, idPrueba);
        
    }

    @GET
    @Path("/listarXEvento")
    @Produces({"application/json","application/xml"})
    public List<Sitio> listarXEvento(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("usuario") String usuario, @QueryParam("idEvento") int idEvento) {
        
        UsuarioSitioDao usuarioSitioDao = new UsuarioSitioDao();
        return usuarioSitioDao.listarXEvento(usuario, idEvento);
    }
    
     @GET
    @Path("/listarArchivoPagoSitio")
    @Produces({"application/json","application/xml"})
    public String listarArchivoPagoSubidoSitio(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("usuario") String usuario, @QueryParam("idPrueba") int idPrueba) {
        
        UsuarioSitioDao usuarioSitioDao = new UsuarioSitioDao();
        return usuarioSitioDao.listarArchivoPagoSubidoSitio(usuario, idPrueba);
        
    }
    
       @GET
    @Path("/listarArchivoAsistenciaSitio")
    @Produces({"application/json","application/xml"})
    public String listarArchivoAsistenciaSitio(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("usuario") String usuario, @QueryParam("idPrueba") int idPrueba, @QueryParam("codEvento") String codEvento) {
        
        UsuarioSitioDao usuarioSitioDao = new UsuarioSitioDao();
        return usuarioSitioDao.listarArchivoListadoAsistenciaSitio(usuario, idPrueba, codEvento);
        
    }
    
     @GET
    @Path("/listarSitiosyRPS")
    @Produces({"application/json","application/xml"})
    public List<UsuarioSitio> listarSitiosyRPS(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("usuario") String usuario, @QueryParam("idPrueba") int idPrueba) {
        
        UsuarioSitioDao usuarioSitioDao = new UsuarioSitioDao();
        return usuarioSitioDao.GetSitiosYRpsPorUsuario(usuario, idPrueba);
        
    }
    
    
    
}
