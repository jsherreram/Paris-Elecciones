/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.UsuarioSitioDao;
import co.com.grupoasd.nomina.modelo.Sitio;
import co.com.grupoasd.nomina.modelo.TipoDocumento;
import co.com.grupoasd.nomina.negocio.tiposdocumento.TiposDocumentoController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/TiposDocumento")
public class TiposDocumentoRest{
    
    @GET
    @Path("/listar")
    @Produces({"application/json","application/xml"})
    public List<TipoDocumento> listar(@javax.ws.rs.core.Context HttpServletRequest request) {
        TiposDocumentoController tiposDocumento = new TiposDocumentoController();
        return tiposDocumento.listarTiposDocumento();
    }
    
    @GET
    @Path("/listarValidos")
    @Produces({"application/json","application/xml"})
    public List<TipoDocumento> listarValidoCargo(@javax.ws.rs.core.Context HttpServletRequest request) {
        TiposDocumentoController tiposDocumento = new TiposDocumentoController();
        return tiposDocumento.listarTiposDocumentoCargo();
    }
}
