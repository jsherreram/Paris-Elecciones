/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.AsignacionSalonDao;
import co.com.grupoasd.nomina.dao.NombramientoDao;
import co.com.grupoasd.nomina.modelo.AsignacionSalon;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.Evento;
import co.com.grupoasd.nomina.modelo.Nombramiento;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.Respuesta;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author ASD
 */
@Path("/asignacionSalon")
public class AsignacionSalonRest {

    @PUT
    @Consumes({"application/json", "application/xml"})
    public Response asignarSalon(Nombramiento p) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        AsignacionSalonDao dao = new AsignacionSalonDao();

        AsignacionSalon salonExiste = dao.buscarAsignacionSalon(p.getIdPrueba(), p.getIddivipol(), p.getEmpleado().getIdEmpleado());
        boolean insert = false;

        //Si ya estaba asignado, lo actualiza
        if (salonExiste.getId()!=0) {
            insert = dao.actualizarSalon(salonExiste.getId(), p.getSalon());
          
        } else {//Si no crea un nuevo registro
            AsignacionSalon nuevo = new AsignacionSalon();
            Empleado e=new Empleado();
            nuevo.setEmpleado(p.getEmpleado());
            Prueba prueba = new Prueba();
            prueba.setIdprueba(p.getIdPrueba());
            nuevo.setPrueba(prueba);
            nuevo.setSalon(p.getSalon());
            nuevo.setSitio(p.getIddivipol());
            insert = dao.InsertarSalon(nuevo);
           
        }

        if (insert) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Actualizado");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("No se asigno el salon");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }

    }
    
    
    @GET
    @Path("/listarPersonasSitio")
    @Produces({"application/json", "application/xml"})
    public List<Nombramiento> listarPersonasSitio(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba, @QueryParam("idPuesto") String idPuesto) {
        NombramientoDao nombramientoDao = new NombramientoDao();
        return nombramientoDao.listarNombramientoSitioCargo(idPrueba, idPuesto);
    }

}
