/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.DepartamentoDaneDao;
import co.com.grupoasd.nomina.dao.DepartamentoDao;
import co.com.grupoasd.nomina.modelo.Departamento;
import co.com.grupoasd.nomina.modelo.DepartamentoDane;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Pedro Rodriguez
 */
@Path("/departamento")
public class DepartamentoRest {

    @GET
    @Path("/listar")
    @Produces({"application/json", "application/xml"})
    public List<Departamento> listar(@javax.ws.rs.core.Context HttpServletRequest request) {
        DepartamentoDao departamentoDao = new DepartamentoDao();
        return departamentoDao.listar();
    }

    @GET
    @Path("/buscar")
    @Produces({"application/xml", "application/json"})
    public Departamento find(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idDpto") String id) {
        DepartamentoDao departamentoDao = new DepartamentoDao();
        return departamentoDao.GetById(id);
    }
    
    @GET
    @Path("/listardepartamentouser")
    @Produces({"application/xml", "application/json"})
    public String listardepartamentouser (@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idprueba") int idprueba, @QueryParam("nrodoc") String nrodoc) {
        DepartamentoDao departamentoDao = new DepartamentoDao();
        return departamentoDao.ListarDepartamentoUsuario(idprueba,nrodoc);
    }

    @GET
    @Path("/listarDptoDane")
    @Produces({"application/json", "application/xml"})
    public List<DepartamentoDane> listarDptoDane(@javax.ws.rs.core.Context HttpServletRequest request) {
        DepartamentoDaneDao departamentoDao = new DepartamentoDaneDao();
        return departamentoDao.listar();
    }

    /**
     * Servicio encargado de retornar todos los departamentos dane asociados al
     * nodo
     *
     * @param codigoDepartamento
     * @return
     */
    @GET
    @Path("listarDepartamentosNodo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DepartamentoDane> listarDepartamentosNodo(@QueryParam("idDepartamento") String codigoDepartamento) {
        DepartamentoDaneDao departamentoDao = new DepartamentoDaneDao();
        return departamentoDao.listarDepartamentosNodo(codigoDepartamento);
    }

}
