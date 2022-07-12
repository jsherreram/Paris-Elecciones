/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import java.util.List;
import co.com.grupoasd.nomina.dao.CargoDao;
import co.com.grupoasd.nomina.modelo.Cargo;
import co.com.grupoasd.nomina.modelo.NivelCargo;
import co.com.grupoasd.nomina.modelo.Respuesta;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.json.JSONArray;

/**
 *
 * @author Pedro Rodríguez
 */


@Path("/cargo")
public class CargoRest {

    @GET
    @Path("/listar")
    @Produces({"application/json","application/xml"})
    public List<Cargo> listar(@javax.ws.rs.core.Context HttpServletRequest request){
        List<Cargo> Cargos = new ArrayList<>();
        
        if(request.getUserPrincipal() != null)
        {
          CargoDao cargoDao = new CargoDao();
          
          return cargoDao.listar();
        }else
        {
            return Cargos;
        }
    }
    
    @GET
    @Path("/listarCargosSuplencia")
    @Produces({"application/json"})
    public List<Cargo> listarCargosSuplencia(@javax.ws.rs.core.Context HttpServletRequest request){
        return new CargoDao().listarCargosSuplencia();
    }
    
    @POST
    @Path("/crear")
    @Consumes({"application/json","application/xml"})
    public Response crear(Cargo c) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        CargoDao cargoDao = new CargoDao();
        
        if(cargoDao.insertar(c) == true)
        {       Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("200");
                respuesta.setDescripcion("Registro Creado");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
        }else
        {       Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("201");
                respuesta.setDescripcion("Cargo ya existe, o se genero un problema al insertar");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
        }
    }
    
    @GET
    @Path("/buscar")
    @Produces({"application/xml", "application/json"})
    public Cargo find(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("id") String id) {
        //request.getUserPrincipal().getName()
        //request.getSession().setAttribute("USERID", "XXXXX");
        
        //request.getSession().getAttribute("USERID");
        
        CargoDao cargoDao = new CargoDao();
        return cargoDao.GetById(id);
    }

    @GET
    @Path("/listarNivCargo")
    @Produces({"application/json"})
    public List<NivelCargo> listarNivCargo(@javax.ws.rs.core.Context HttpServletRequest request){
        if(request.getUserPrincipal() != null)
        { CargoDao cargoBussines = new CargoDao();
          return cargoBussines.listarNivCargo();
        }
        return null;
    }
    
    @POST
    @Path("/actualizar")
    @Consumes({"application/json"})
    public Response actualizar(Cargo p) {
       ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        CargoDao cargoBusiness = new CargoDao();
        if(cargoBusiness.Actualizar(p))
        {       Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("200");
                respuesta.setDescripcion("Registro Actualizado");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
        }else{  Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("201");
                respuesta.setDescripcion("No se actualizo");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
        }
    }
}
