/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.MunicipioDaneDao;
import co.com.grupoasd.nomina.dao.MunicipioDao;
import co.com.grupoasd.nomina.modelo.Municipio;
import co.com.grupoasd.nomina.modelo.MunicipioDane;
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

@Path("/municipio")
public class MunicipioRest {
    
    @GET
    @Path("/listar")
    @Produces({"application/json"})
    public List<Municipio> listar(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idDpto") String idDepartamento) {
        MunicipioDao municipioDao = new MunicipioDao();
        return municipioDao.listar(idDepartamento);
    }

    @GET
    @Path("/buscar")
    @Produces({"application/xml", "application/json"})
    public Municipio find(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idDpto") String idDepartamento, @QueryParam("idMpio") String idMunicipio) {
        MunicipioDao municipioDao = new MunicipioDao();
        return municipioDao.GetById(idDepartamento, idMunicipio);
    }
    
    @GET
    @Path("/listarDane")
    @Produces({"application/json"})
    public List<MunicipioDane> listarDane(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idDpto") String idDepartamento) {
        MunicipioDaneDao municipioDaneDao = new MunicipioDaneDao();
        return municipioDaneDao.listar(idDepartamento);
    }
    
    @GET
    @Path("/listarGeo")
    @Produces({"application/xml", "application/json"})
    public List<MunicipioDane> listarGeo(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("prueba") int prueba) {
        MunicipioDaneDao municipioDaneDao = new MunicipioDaneDao();
        return municipioDaneDao.listarGeo(prueba);
    }
    
    @GET
    @Path("/getByMunicipioUsuarioPrueba")
    @Produces({"application/xml", "application/json"})
    public String getByMunicipioUsuarioPrueba(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idPrueba") int idPrueba,@QueryParam("usuario") String usuario) {
        MunicipioDaneDao municipioDaneDao = new MunicipioDaneDao();
        return municipioDaneDao.municipioUsuarioPrueba(idPrueba,usuario);
    }
    
    
}
