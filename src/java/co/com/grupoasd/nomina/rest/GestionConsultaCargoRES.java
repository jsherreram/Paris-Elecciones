/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.EmpleadoDao;
import co.com.grupoasd.nomina.dao.GestionPagosDao;
import co.com.grupoasd.nomina.dao.TareaConfirmacionDao;
import co.com.grupoasd.nomina.modelo.MiAsignacion;
import co.com.grupoasd.nomina.modelo.MiAsistencia;
import co.com.grupoasd.nomina.modelo.MisPruebas;
import co.com.grupoasd.nomina.modelo.MisPagos;
import co.com.grupoasd.nomina.modelo.MisViaticos;
import co.com.grupoasd.nomina.modelo.PagoViaticos;
import co.com.grupoasd.nomina.modelo.PendienteAprobarViatico;
import co.com.grupoasd.nomina.modelo.Respuesta;
import co.com.grupoasd.nomina.modelo.TareaConfirmacion;
import co.com.grupoasd.nomina.negocio.gestionmediopago.IAsignacion;
import co.com.grupoasd.nomina.negocio.gestionmediopago.IMedioPago;
import co.com.grupoasd.nomina.negocio.gestionmediopago.IMedioViaticos;
import co.com.grupoasd.nomina.negocio.gestionmediopago.IMiAsistencia;
import co.com.grupoasd.nomina.negocio.gestionmediopago.IMisPagos;
import co.com.grupoasd.nomina.negocio.gestionmediopago.IPagoViaticos;
import co.com.grupoasd.nomina.negocio.gestionmediopago.IPendienteAprobarViaticos;
import co.com.grupoasd.nomina.negocio.gestionmediopago.MedioPagoBussines;
import co.com.grupoasd.nomina.negocio.gestionmediopago.MiAsignacionBussines;
import co.com.grupoasd.nomina.negocio.gestionmediopago.MiAsistenciaBussines;
import co.com.grupoasd.nomina.negocio.gestionmediopago.MisPagosBussines;
import co.com.grupoasd.nomina.negocio.gestionmediopago.MisViaticosBussines;
import co.com.grupoasd.nomina.negocio.gestionmediopago.PagoViaticosBussines;
import co.com.grupoasd.nomina.negocio.gestionmediopago.PendienteAproViaticosBussines;
import java.util.ArrayList;
import java.util.Date;
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

@Path("/gestionmediopagos")
public class GestionConsultaCargoRES {

    @GET
    @Path("/listar")
    @Produces({"application/json"})
    public List<MisPruebas> listar(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idEmp") int idEmp) {
        IMedioPago pruebaBussines = new MedioPagoBussines();
        return pruebaBussines.listar(idEmp, 0, "%");
    }

    @GET
    @Path("/listarcargo")
    @Produces({"application/json"})
    public List<MisPruebas> listarcargo(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEmp") int idEmp, @QueryParam("idPrueba") int idPrueba,
            @QueryParam("cargoNombre") String cargoNombre) {
        IMedioPago pruebaBussines = new MedioPagoBussines();
        return pruebaBussines.listar(idEmp, idPrueba, cargoNombre);
    }

    @GET
    @Path("/listarasistencia")
    @Produces({"application/json"})
    public List<MiAsistencia> listarasistencia(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEmp") int idEmp, @QueryParam("idPrueba") int idPrueba,
            @QueryParam("cargoNombre") String cargoNombre) {
        IMiAsistencia pruebaBussines = new MiAsistenciaBussines();
        return pruebaBussines.listar(idEmp, idPrueba, cargoNombre);
    }

    @GET
    @Path("/listarpagos")
    @Produces({"application/json"})
    public List<MisPagos> listarpagos(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEmp") int idEmp, @QueryParam("idPrueba") int idPrueba,
            @QueryParam("cargoNombre") String cargoNombre) {
        IMisPagos pruebaBussines = new MisPagosBussines();
        return pruebaBussines.listar(idEmp, idPrueba, cargoNombre);
    }

    @GET
    @Path("/listarviaticos")
    @Produces({"application/json"})
    public List<MisViaticos> listarviaticos(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEmp") int idEmp, @QueryParam("idPrueba") int idPrueba,
            @QueryParam("cargoNombre") String cargoNombre) {
        IMedioViaticos pruebaBussines = new MisViaticosBussines();
        return pruebaBussines.listar(idEmp, idPrueba, cargoNombre);
    }

    @GET
    @Path("/listarasignacion")
    @Produces({"application/json"})
    public List<MiAsignacion> listarasignacion(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEmp") int idEmp, @QueryParam("idPrueba") int idPrueba,
            @QueryParam("cargoNombre") String cargoNombre) {
        IAsignacion pruebaBussines = new MiAsignacionBussines();
        return pruebaBussines.listar(idEmp, idPrueba, cargoNombre);
    }

    @GET
    @Path("/listarplanoviaticos")
    @Produces({"application/json"})
    public List<PagoViaticos> listarplanoviaticos(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEmp") int idEmp) {
        IPagoViaticos pruebaBussines = new PagoViaticosBussines();
        return pruebaBussines.listar(idEmp);
    }

    @GET
    @Path("/autorizarviaticos")
    @Produces({"application/json"})
    public List<PendienteAprobarViatico> autorizarviaticos(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEmp") int idEmp, @QueryParam("idPrueba") int idPrueba,
            @QueryParam("codCargo") String codCargo) {
        IPendienteAprobarViaticos pruebaBussines = new PendienteAproViaticosBussines();
        return pruebaBussines.listar(idEmp, idPrueba, codCargo);
    }

    @POST
    @Path("/confirmaviatico")
    @Consumes({"application/json"})
    public Response confirmaviatico(PendienteAprobarViatico reg) {
        Response r = null;
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        GestionPagosDao PagosDao = new GestionPagosDao();
        Respuesta respuesta = new Respuesta();
        if (PagosDao.InsertPagoViatico(reg)) {
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Actualizado");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            respuesta.setCodigo("201");
            respuesta.setDescripcion("El registro no fue actualizado.");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    @POST
    @Path("/confirmapago")
    @Consumes({"application/json"})
    public Response confirmapago(MisPagos reg) {
        Response r = null;
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        GestionPagosDao PagosDao = new GestionPagosDao();
        Respuesta respuesta = new Respuesta();
        if (PagosDao.actualizar(reg)) {
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Actualizado");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            respuesta.setCodigo("201");
            respuesta.setDescripcion("El registro no fue actualizado.");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    @POST
    @Path("/cancelaprueba")
    @Consumes({"application/json"})
    public Response cancelaprueba(MisPruebas reg) {
        Response r = null;
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        GestionPagosDao PagosDao = new GestionPagosDao();
        Respuesta respuesta = new Respuesta();
        if (PagosDao.cancelaprueba(reg)) {
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Actualizado");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            respuesta.setCodigo("201");
            respuesta.setDescripcion("El registro no fue actualizado.");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    @POST
    @Path("/confirmacontrato")
    @Consumes({"application/json"})
    public Response asignar(@QueryParam("nrodoc") long nrodoc) {
 
        System.out.println(nrodoc);
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        int idEmpleado = new EmpleadoDao().GetIdByNumeroDocumento(nrodoc);
        TareaConfirmacion tarea = new TareaConfirmacion();
        tarea.setIdEmpleado(idEmpleado);
        tarea.setCodigoCargo("10");
        Date fecha = new Date();
        tarea.setFechaConfirmacion(fecha);
        tarea.setFormaConfirmacion("Huella");
        tarea.setTipo("cuenta de cobro");
        tarea.setIdPrueba(38);
        tarea.setEstado(0);
        TareaConfirmacionDao tareaDao = new TareaConfirmacionDao();
        Respuesta respuesta = new Respuesta();

        if (tareaDao.insertar(tarea)) {
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Actualizado");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            respuesta.setCodigo("201");
            respuesta.setDescripcion("El registro no fue actualizado.");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }

    }

    @GET
    @Path("/buscarconfirmacion")
    @Produces({"application/json"})
    public TareaConfirmacion buscar(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idempleado") int idEmpleado, @QueryParam("idprueba") int idPrueba,
            @QueryParam("codcargo") String codCargo, @QueryParam("tipo") String tipo) {
        TareaConfirmacionDao tareaDao = new TareaConfirmacionDao();
        return tareaDao.buscarContratoEmpleado(idEmpleado, idPrueba, tipo);
    }
}
