/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.PruebaDao;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.negocio.prueba.PruebaBusiness;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.Respuesta;
import co.com.grupoasd.nomina.modelo.TipoPruebaEsp;
import co.com.grupoasd.nomina.negocio.prueba.IPrueba;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 *
 * @author Pedro Rodríguez
 */


@Path("/prueba")
public class PruebaRest {
 
    @GET
    @Path("/listar")
    @Produces({"application/json"})
    public List<Prueba> listar(@javax.ws.rs.core.Context HttpServletRequest request){
        List<Prueba> prueba = new ArrayList<>();
        if(request.getUserPrincipal() != null)
        {
          IPrueba pruebaBussines = new PruebaBusiness();
          return pruebaBussines.listar();
        }else
        {
            return prueba;
        }
    }
    
    @GET
    @Path("/listarnocerradas")
    @Produces({"application/json"})
    public List<Prueba> listarnocerradas(@javax.ws.rs.core.Context HttpServletRequest request){
        List<Prueba> prueba = new ArrayList<>();
        if(request.getUserPrincipal() != null)
        { IPrueba pruebaBussines = new PruebaBusiness();
          return pruebaBussines.listarnocerradas();
        }else
        {return prueba;
        }
    }

    @GET
    @Path("/listarOnDivitrans")
    @Produces({"application/json"})
    public List<Prueba> listarOnDivitrans(@javax.ws.rs.core.Context HttpServletRequest request){
        List<Prueba> prueba = new ArrayList<>();
        if(request.getUserPrincipal() != null)
        {
          IPrueba pruebaBussines = new PruebaBusiness();
          return pruebaBussines.listarOnDivitrans();
        }else
        {
            return prueba;
        }
    }

    @GET
    @Path("/buscar")
    @Produces({"application/json"})
    public Prueba buscar(@javax.ws.rs.core.Context HttpServletRequest request,
          @QueryParam("id") int id){
          IPrueba pruebaBussines = new PruebaBusiness();
          return pruebaBussines.GetById(id) ;
    }    
    
    @GET
    @Path("/listarxFecha")
    @Produces({"application/json"})
    public List<Prueba> listarxFecha(@javax.ws.rs.core.Context HttpServletRequest request,@QueryParam("fechaInicial") String fechaInicial,@QueryParam("fechaFinal") String fechaFinal) throws Exception{
        PruebaBusiness prueba = new PruebaBusiness();
        return prueba.listarxFecha(fechaInicial, fechaFinal);
    }

    @GET
    @Path("/listarxEstado")
    @Produces({"application/json"})
    public List<Prueba> listarxEstado(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("estado") String estado) {
        EmpleadoSesion emp = new EmpleadoSesion();
        emp = (EmpleadoSesion) request.getSession().getAttribute("empleado");                      
        return new PruebaBusiness().listarxEstado(estado, emp.getSitios());
    }

    @POST
    @Consumes({"application/json"})
    public Response crear(Prueba p) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        
        IPrueba pruebaBussines = new PruebaBusiness();
        
        int idPrueba = pruebaBussines.Insertar(p);
        if(idPrueba > 0)
        {
                p.setIdprueba(idPrueba);
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("200");
                respuesta.setDescripcion("Registro Creado");
                respuesta.setId(idPrueba);
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
        }else
        {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("201");
                respuesta.setDescripcion("prueba ya existe, o se genero un problema al insertar");
                respuesta.setId(idPrueba);
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
        }
    }
    
    @GET
    @Path("/listarAll")
    @Produces({"application/json"})
    public List<Prueba> listarAll(@javax.ws.rs.core.Context HttpServletRequest request,@QueryParam("idPrueba") int idPrueba){
        List<Prueba> prueba = new ArrayList<>();
        if(request.getUserPrincipal() != null)
        { IPrueba pruebaBussines = new PruebaBusiness();
          return pruebaBussines.listarAll(idPrueba);
        }else{ return prueba; }
    }
    
    

    @GET
    @Path("/listarTipPrueba")
    @Produces({"application/json"})
    public String listarTipPrueba(@javax.ws.rs.core.Context HttpServletRequest request){
        if(request.getUserPrincipal() != null)
        { PruebaDao pruebaBussines = new PruebaDao();
          return pruebaBussines.listarTipPrueba();
        }else{ return ""; }
    }
    
    @GET
    @Path("/listarTipPruebaEsp")
    @Produces({"application/json"})
    public List<TipoPruebaEsp> listarTipPruebaEsp(@javax.ws.rs.core.Context HttpServletRequest request){
        if(request.getUserPrincipal() != null)
        { PruebaDao pruebaBussines = new PruebaDao();
          return pruebaBussines.listarTipPruebaEsp();
        }
        return null;
    }
    
    @POST
    @Path("/actualizar")
    @Consumes({"application/json"})
    public Response actualizar(Prueba p) {
       ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        PruebaBusiness ipruebaBusiness = new PruebaBusiness();
        if(ipruebaBusiness.Actualizar(p))
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
    
    @GET
    @Path("/listarEstPrueba")
    @Produces({"application/json"})
    public String listarEstPrueba(@javax.ws.rs.core.Context HttpServletRequest request){
        if(request.getUserPrincipal() != null)
        { PruebaDao pruebaBussines = new PruebaDao();
          return pruebaBussines.listarEstPrueba();
        }else{ return ""; }
    }

}
