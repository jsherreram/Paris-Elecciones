/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.DivipolDao;
import co.com.grupoasd.nomina.dao.PersonaAsignadaDao;
import co.com.grupoasd.nomina.dao.SeguimientoDao;
import co.com.grupoasd.nomina.modelo.PersonaAsignada;
import co.com.grupoasd.nomina.modelo.Sitio;
import co.com.grupoasd.nomina.modelo.VacantesSitioCargo;
import co.com.grupoasd.nomina.modelo.seguimiento.SeguimientoController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Pedro Rodriguez
 */
@Path("/seguimiento")
public class SeguimientoRest {
    
    @GET
    @Path("/nacional")
    @Produces({"application/json"})
    public String seguimientoNacional(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento, @QueryParam("usuario") String usuario) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.seguimientoNacional(idEvento, usuario);
    }
    
    @GET
    @Path("/seguimientoxdepartamento")
    @Produces({"application/json"})
    public String seguimientoxDepartamento(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idPrueba") int idEvento, @QueryParam("departamento") String codigodepartamento) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.estadoNombramientoxDepartamento(idEvento, codigodepartamento);
    }

    @GET
    @Path("/seguimientoxestados")
    @Produces({"application/json"})
    public String seguimientoxestados(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idPrueba") int idEvento) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.seguimientoxEstado(idEvento);
    }
    

    @GET
    @Path("/departamental")
    @Produces({"application/json"})
    public String seguimientoDepartamental(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento, @QueryParam("idDepartamento") String idDepartamento) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.seguimientoDepartamental(idEvento, idDepartamento);
    }
    
    @GET
    @Path("/municipal")
    @Produces({"application/json"})
    public String seguimientoMunicipal(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento, @QueryParam("idDepartamento") String idDepartamento, @QueryParam("idMunicipio") String idMunicipio) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.seguimientoMunicipal(idEvento, idDepartamento, idMunicipio);
    }

    @GET
    @Path("/zonal")
    @Produces({"application/json"})
    public String seguimientoZonal(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento, @QueryParam("idDepartamento") String idDepartamento, @QueryParam("idMunicipio") String idMunicipio, @QueryParam("idCargo") String idCargo) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.seguimientoZonal(idEvento, idDepartamento, idMunicipio, idCargo);
    }
    
    @GET
    @Path("/puesto")
    @Produces({"application/json"})
    public String seguimientoPuesto(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento, @QueryParam("idDepartamento") String idDepartamento, @QueryParam("idMunicipio") String idMunicipio, @QueryParam("idCargo") String idCargo, @QueryParam("idZona") String idZona) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.seguimientoPuesto(idEvento, idDepartamento, idMunicipio, idCargo, idZona);
    }
    
    @GET
    @Path("/ubicacion")
    @Produces({"application/json"})
    public String seguimientoUbicacion(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento, @QueryParam("idDepartamento") String idDepartamento, 
            @QueryParam("idMunicipio") String idMunicipio, @QueryParam("idCargo") String idCargo, 
            @QueryParam("idZona") String idZona, @QueryParam("idPuesto") String idPuesto) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.seguimientoUbicacion(idEvento, idDepartamento, idMunicipio, idCargo, idZona, idPuesto);
    }

    @GET
    @Path("/nombramientoCargosNacional")
    @Produces({"application/json"})
    public String nombramientoCargosNacional(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.consultaMonitoreo("nombramientoCargosNacional","TODOS",idEvento);
    }

    @GET
    @Path("/nombramientoNacionalSitios")
    @Produces({"application/json"})
    public String nombramientoNacionalSitios(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idPrueba") int idPrueba) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.consultaConfirmacionSitios("listarSitiosNodos","SITIOSNODOS",idPrueba);
    }

    @GET
    @Path("/capacitacionCargosNacionalDpto")
    @Produces({"application/json"})
    public String capacitacionCargosNacionalDpto(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento,@QueryParam("idD") String idD,@QueryParam("idPrueba") int idPrueba) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();

        String valor =  seguimientoDao.consultaMonitoreo("capacitacionNodos",idD,idPrueba,idPrueba,idPrueba,idPrueba);
        return valor;
        
    }

    @GET
    @Path("/listarSitiosAll")
    @Produces({"application/json"})
    public String listarSitiosAll(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idDepartamento") String idDepartamento,@QueryParam("idPrueba") int idPrueba) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.consultaConfirmacionSitios("listarSitiosAll",idDepartamento,idPrueba);
    }

    @GET
    @Path("/listarConsolidadoNacionalSitios")
    @Produces({"application/json"})
    public String listarConsolidadoNacionalSitios(@javax.ws.rs.core.Context HttpServletRequest request) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.consultaMonitoreoSitio("nombramientoCargosNacional","TODOS");
    }
    
    @GET
    @Path("/nombramientoCargosNodo")
    @Produces({"application/json"})
    public String nombramientoCargosNodo(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento) {
        
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        
        String usuario = request.getUserPrincipal().getName();
        return seguimientoDao.consultaMonitoreo("nombramientoCargosNodo","TODOS",idEvento, usuario);
    }

    
    @GET
    @Path("/nombramientoNacional")
    @Produces({"application/json"})
    public String nombramientoNacional(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento, 
            @QueryParam("codigoCargo") String codigoCargo) {
        
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.consultaMonitoreo("nombramientoNacional",codigoCargo,idEvento);
    }
    
    @GET
    @Path("/listarDepartamentosCapacitacion")
    @Produces({"application/json"})
    public String listarDepartamentosCapacitacion(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idPrueba") int idPrueba, 
            @QueryParam("codigoCargo") String codigoCargo) {
        
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.consultaMonitoreo("nombramientoNacionalCapacitacion",codigoCargo,idPrueba);
    }
    
    @GET
    @Path("/nombramientoDepartamental")
    @Produces({"application/json"})
    public String nombramientoDepartamental(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento, 
            @QueryParam("idDepartamento") String idDepartamento,
            @QueryParam("codigoCargo") String codigoCargo) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.consultaMonitoreo("nombramientoDepartamental", codigoCargo ,idDepartamento, idEvento);
    }
    
    @GET
    @Path("/nombramientoMunicipal")
    @Produces({"application/json"})
    public String nombramientoMunicipal(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento, 
            @QueryParam("idDepartamento") String idDepartamento, 
            @QueryParam("idMunicipio") String idMunicipio,
            @QueryParam("codigoCargo") String codigoCargo) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.consultaMonitoreo("nombramientoMunicipal", codigoCargo, idDepartamento, idMunicipio, idEvento);
    }

    
    @GET
    @Path("/nombramientoSitio")
    @Produces({"application/json"})
    public String nombramientoSitio(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento, 
            @QueryParam("idDepartamento") String idDepartamento, 
            @QueryParam("idMunicipio") String idMunicipio,  
            @QueryParam("idPuesto") String idPuesto,
            @QueryParam("codigoCargo") String codigoCargo) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.consultaMonitoreo("nombramientoSitio", codigoCargo, idDepartamento, idMunicipio, idEvento, idPuesto);
    }
    
    @GET
    @Path("/monitoreoSitioXCargo")
    @Produces({"application/json"})
    public String monitoreoSitioXCargo(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idPrueba") int idPrueba, 
            @QueryParam("idEvento") int idEvento,
            @QueryParam("usuario") Long usuario) {
        
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.monitoreoSitioXCargo(idPrueba, idEvento,usuario);
    }
    
    
    @GET
    @Path("/capacitacion")
    @Produces({"application/json"})
    public String seguimientoCapacitacion(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento, @QueryParam("idDepartamento") String idDepartamento, 
            @QueryParam("idMunicipio") String idMunicipio, @QueryParam("idCargo") String idCargo, 
            @QueryParam("idZona") String idZona, @QueryParam("idPuesto") String idPuesto) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.seguimientoCapacitacion(idEvento, idDepartamento, idMunicipio, idCargo, idZona, idPuesto);
    }
    
    
    @GET
    @Path("/persona")
    @Produces({"application/json"})
    public String seguimientoPersona(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento, @QueryParam("idDepartamento") String idDepartamento, 
            @QueryParam("idMunicipio") String idMunicipio, @QueryParam("idCargo") String idCargo, 
            @QueryParam("idZona") String idZona, @QueryParam("idPuesto") String idPuesto,
            @QueryParam("idUbicacion") String idUbicacion) {
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.seguimientoPersona(idEvento, idDepartamento, idMunicipio, idCargo, idZona, idPuesto, idUbicacion);
    }

    @GET
    @Path("/personaAsignada")
    @Produces({"application/json"})
    public PersonaAsignada getPersonaAsignada(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idEvento") int idEvento, @QueryParam("idDepartamento") String idDepartamento, 
            @QueryParam("idMunicipio") String idMunicipio, @QueryParam("idCargo") String idCargo, 
            @QueryParam("idZona") String idZona, @QueryParam("idPuesto") String idPuesto,
            @QueryParam("idUbicacion") String idUbicacion) {
        
        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        
        return personaAsignadaDao.getPersonaAsignada(idEvento, idDepartamento, idMunicipio, idCargo, idZona, idPuesto, idUbicacion);
    }
    

    @GET
    @Path("/seguimientoTerceros")
    @Produces({"application/json"})
    public String getPersonaAsignada(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("usuario") String usuario) {
        
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.seguimientoTerceros(usuario);
    }
    
    
    @POST
    @Path("/vacantesSitioCargo")
    @Produces({"application/json"})
    public List<VacantesSitioCargo> getVacantesSitioCargo(VacantesSitioCargo busqueda ) throws Exception{
        SeguimientoController seguimiento = new SeguimientoController();
        return seguimiento.vacantesSitioCargo(busqueda);
    }
    
    
    
    
    @POST
    @Path("/persona/asignar/{idEvento}/{idDepartamento}/{idMunicipio}/{idCargo}/{idZona}/{idPuesto}/{idUbicacion}/{nroDoc}")
    public void asignar(
            @PathParam("idEvento") int idEvento, @PathParam("idDepartamento") String idDepartamento, 
            @PathParam("idMunicipio") String idMunicipio, @PathParam("idCargo") String idCargo, 
            @PathParam("idZona") String idZona, @PathParam("idPuesto") String idPuesto,
            @PathParam("idUbicacion") String idUbicacion, @PathParam("nroDoc") int nroDoc) {
        
        Response r = null;
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        if(seguimientoDao.asignarPersona(idEvento, idDepartamento, idMunicipio, idCargo, idZona, idPuesto, idUbicacion,nroDoc))
        {
            r= Response.ok("OK").build();
        }else
        {
            r= Response.ok("ERROR").build();
        }
        //return r;
    }
    
    
    @GET
    @Path("/seguimientoGeneralSitioEvento")
    @Produces({"application/json"})
    public String seguimientoGeneralSitioEvento(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("usuario") String usuario, @QueryParam("idPrueba") int idPrueba, @QueryParam("idEvento") int idEvento) {
        
        SeguimientoDao seguimientoDao = new SeguimientoDao();
        return seguimientoDao.seguimientoGeneralSitioEvento(idEvento, idPrueba, usuario);
    }
    

    @PUT
    @Path("/updatedesconetado")
    @Consumes({"application/json", "application/xml"})
    public Sitio updatedesconetado(@javax.ws.rs.core.Context HttpServletRequest request, Sitio sitio) {
        DivipolDao divipol = new DivipolDao();
        divipol.actualizardesconectado(sitio);
        return (sitio);
    }

}



