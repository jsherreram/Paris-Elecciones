/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;


import co.com.grupoasd.nomina.modelo.ConfirmarAsistencia;
import co.com.grupoasd.nomina.modelo.ConfirmarAsistenciaVO;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.Respuesta;
import co.com.grupoasd.nomina.negocio.confirmacionevento.ConfirmarEventoController;
import co.com.grupoasd.nomina.negocio.confirmarasistencia.IConfirmarAsistenciaImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


@Path("/ConfirmarEvento")
public class ConfirmarEventoRest {
    
    @GET
    @Path("/listar")
    @Produces({"application/json"})
    public List<ConfirmarAsistenciaVO> listar(@javax.ws.rs.core.Context HttpServletRequest request) {        
        
        ConfirmarEventoController eventos = new ConfirmarEventoController();
        EmpleadoSesion emp = new EmpleadoSesion();
        HttpSession sesion;
        
        sesion = request.getSession();
        
        emp = (EmpleadoSesion)sesion.getAttribute("empleado");       
        
        return eventos.consultarConvocatoria(emp.getIdEmpleado().toString());
    }

    @GET
    @Path("/listarXIdEmpleado")
    @Produces({"application/json"})
    public List<ConfirmarAsistenciaVO> listarXIdEmpleado(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEmpleado") String idEmpleado) {
        ConfirmarEventoController eventos = new ConfirmarEventoController();
        return eventos.consultarConvocatoria(idEmpleado);
    }    
    
    @PUT
    @Consumes({"application/json","application/xml"})
    public Response modificar(@javax.ws.rs.core.Context HttpServletRequest request, ConfirmarAsistencia e) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);        
        IConfirmarAsistenciaImpl confirmar = new IConfirmarAsistenciaImpl();
        if(confirmar.confirmar(e))        
        {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("200");
                respuesta.setDescripcion("Registro Creado");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
        }else
        {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("201");
                respuesta.setDescripcion("No se actualizo");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
        }
    }
    
}
