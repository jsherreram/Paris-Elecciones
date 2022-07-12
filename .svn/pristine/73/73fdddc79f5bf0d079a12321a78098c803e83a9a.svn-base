package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.EmpleadoPruebaEstadoDao;
import co.com.grupoasd.nomina.modelo.EmpleadoPruebaEstado;
import co.com.grupoasd.nomina.modelo.Respuesta;
import co.com.grupoasd.nomina.modelo.wrapper.EmpleadoPruebaJoin;
import co.com.grupoasd.nomina.negocio.empleadopruebaestado.EmpleadoPruebaEstadoController;
import co.com.grupoasd.nomina.negocio.empleadopruebaestado.IEmpleadoPruebaEstado;
import co.com.grupoasd.nomina.negocio.estadopersonaprueba.EstadoPersonaPruebaController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Administrador
 */
@Path("/empleadoPrueba")
public class EmpleadoPruebaRest {

    @GET
    @Produces({"application/xml", "application/json"})
    public EmpleadoPruebaJoin findJoinBySession(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEmpleado") int idEmpleado,
            @QueryParam("idPrueba") int idPrueba,
            @QueryParam("idCodCargo") String codCargo) {

        // HttpSession sesion = request.getSession();
        // EmpleadoSesion emp = (EmpleadoSesion) sesion.getAttribute("empleado");        
        EmpleadoPruebaEstadoDao emprueba = new EmpleadoPruebaEstadoDao();
        EmpleadoPruebaJoin empleadoJoin = emprueba.GetAllById(idEmpleado, codCargo);
        empleadoJoin.setPrueba(emprueba.getByID(idEmpleado, idPrueba, codCargo));
        return empleadoJoin;
    }

    @GET
    @Path("/getPruebaEmpleado")
    @Consumes({"application/json", "application/xml"})
    public EmpleadoPruebaEstado getPruebaEmpleado(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idEmpleado") int idEmpleado, @QueryParam("idPrueba") int idPrueba, @QueryParam("cargo") String cargo) {
        EmpleadoPruebaEstado epe = new EmpleadoPruebaEstado();
        epe.setIdEmpleado(idEmpleado);
        epe.setIdPrueba(idPrueba);
        epe.setCargo(cargo);
        EmpleadoPruebaEstadoController controller = new EmpleadoPruebaEstadoController();
        return controller.getPrueba(epe);
    }

    @GET
    @Path("/getPruebaEmpleadoByID")
    @Consumes({"application/json", "application/xml"})
    public EmpleadoPruebaEstado getPruebaEmpleadoByID(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("id") int id) {
        EmpleadoPruebaEstado epe = new EmpleadoPruebaEstado();
        epe.setId(id);
        EmpleadoPruebaEstadoController controller = new EmpleadoPruebaEstadoController();
        return controller.getPruebaById(epe);
    }

    @PUT
    @Path("/update-estado-prueba")
    @Consumes({"application/json", "application/xml"})
    public EmpleadoPruebaEstado updateEstadoPrueba(@javax.ws.rs.core.Context HttpServletRequest request, EmpleadoPruebaEstado epe) {
        EmpleadoPruebaEstadoController controller = new EmpleadoPruebaEstadoController();
        if (controller.actualizarEstado(epe)) {
            return epe;
        }
        return null;
    }

    @PUT
    @Consumes({"application/json", "application/xml"})
    public Response update(EmpleadoPruebaJoin e) {

        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        EmpleadoPruebaEstadoDao dao = new EmpleadoPruebaEstadoDao();
        if (dao.actualizarMedioPago(e)) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Creado");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("No se actualizo");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    @GET
    @Path("/getEstadoActual")
    @Produces({"application/json"})
    public EmpleadoPruebaEstado getEstadoActual(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("nrodoc") int nrodoc,
            @QueryParam("idPrueba") int idPrueba,
            @QueryParam("codigoCargo") String codigoCargo) {
        EstadoPersonaPruebaController controller = new EstadoPersonaPruebaController();

        return controller.getEstadoActual(nrodoc, idPrueba, codigoCargo);
    }

    @GET
    @Path("/listadoPruebasEmpleado")
    @Produces({"application/json"})
    public String listarEmpleadosConfirmados(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int id) {
        EmpleadoPruebaEstadoDao empleadoDao = new EmpleadoPruebaEstadoDao();
        return empleadoDao.listarPruebasEmpleado(id);
    }

    @GET
    @Path("/listarJsonFuncionarios")
    @Produces({"application/json", "application/xml"})
    public String listarJsonFuncionarios(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("nrodoc") int nrodoc, @QueryParam("apellido1") String apellido1,
            @QueryParam("nombre1") String nombre1, @QueryParam("rol") String rol, @QueryParam("idPrueba") int idPrueba) {

        IEmpleadoPruebaEstado objController = new EmpleadoPruebaEstadoController();
        return objController.buscarJsonFuncionariosConRoles(nrodoc, apellido1, nombre1, rol, idPrueba);

    }

    @GET
    @Path("/buscarJsonFuncionario")
    @Produces({"application/json", "application/xml"})
    public String buscarJsonFuncionario(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEmpleado") int idEmpleado, @QueryParam("idPrueba") int idPrueba) {

        IEmpleadoPruebaEstado objController = new EmpleadoPruebaEstadoController();
        return objController.buscarJsonFuncionario(idEmpleado, idPrueba);

    }

    @GET
    @Path("/buscarJsonFuncionarioCedula")
    @Produces({"application/json", "application/xml"})
    public String buscarJsonFuncionarioCedula(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEmpleado") long nroDoc, @QueryParam("idPrueba") int idPrueba) {

        IEmpleadoPruebaEstado objController = new EmpleadoPruebaEstadoController();
        return objController.buscarJsonFuncionarioCedula(nroDoc, idPrueba);

    }

    @POST
    @Path("/asignarRoles")
    @Produces({"application/json"})
    public Response asignarRoles(String json) {

        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        try {

            EmpleadoPruebaEstadoController objController = new EmpleadoPruebaEstadoController();
            boolean actualizo = objController.asignarRolesNodos(json);

            if (actualizo) {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("200");
                respuesta.setDescripcion("Registro(s) Creado(s)");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
            } else {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("201");
                respuesta.setDescripcion("Error:No se pudo actualizar los roles y/o nodos del funcioanrio.");
                rb.status(201);
                rb.entity(respuesta);
                return rb.build();
            }
        } catch (Exception ex) {
            Logger.getLogger(MedioPagoRest.class.getName()).log(Level.SEVERE, null, ex);

            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Error:No se pudo actualizar los roles y/o nodos del funcioanrio.");
            rb.status(201);
            rb.entity(respuesta);
        }

        return rb.build();
    }

}
