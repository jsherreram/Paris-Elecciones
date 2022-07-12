/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.DivipolDao;
import co.com.grupoasd.nomina.dao.EstadosOrdenDao;
import co.com.grupoasd.nomina.dao.EventoDao;
import co.com.grupoasd.nomina.dao.OrdenesReportesDao;
import co.com.grupoasd.nomina.modelo.ActualizaRecepcionDevolucion;
import co.com.grupoasd.nomina.modelo.ConsultaOrdenes;
import co.com.grupoasd.nomina.modelo.DespachoDetalleRespuesta;
import co.com.grupoasd.nomina.modelo.DetalleOrden;
import co.com.grupoasd.nomina.modelo.DetallesOrdenGeneral;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.EstadosGeneral;
import co.com.grupoasd.nomina.modelo.EstadosOrden;
import co.com.grupoasd.nomina.modelo.Evento;
import co.com.grupoasd.nomina.modelo.InformacionControlPdsSuplencia;
import co.com.grupoasd.nomina.modelo.InformacionDespachosOrden;
import co.com.grupoasd.nomina.modelo.InformacionDetalladaPDS;
import co.com.grupoasd.nomina.modelo.InformacionOrdenPDS;
import co.com.grupoasd.nomina.modelo.ListadoRelacionOrden;
import co.com.grupoasd.nomina.modelo.Orden;
import co.com.grupoasd.nomina.modelo.OrdenCompleta;
import co.com.grupoasd.nomina.modelo.PersonalOrdenSuplencia;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.Respuesta;
import co.com.grupoasd.nomina.modelo.RespuestaDespacho;
import co.com.grupoasd.nomina.modelo.RespuestaGenerica;
import co.com.grupoasd.nomina.negocio.ordenes.OrdenesController;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author CristianAlexander
 */
@Path("/ordenes")
public class OrdenesRest {

    @GET
    @Path("/listar")
    @Produces({"application/json"})
    public List<Orden> listarOrdenes(@javax.ws.rs.core.Context HttpServletRequest request) throws Exception {
        OrdenesController ordenes = new OrdenesController();
        return ordenes.listarOrdenes();
    }

    @GET
    @Path("/buscarOrden")
    @Produces({"application/json"})
    public Orden buscarOrden(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idOrden") String idOrden) throws Exception {
        OrdenesController ordenes = new OrdenesController();
        return ordenes.getById(idOrden);
    }

    @GET
    @Path("/detalles")
    @Produces({"application/json"})
    public List<DetalleOrden> listarDetallesOrden(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idOrden") String idOrden) throws Exception {
        OrdenesController ordenes = new OrdenesController();
        return ordenes.listarDetalleOrden(idOrden);
    }

    @GET
    @Path("/ordenesConsulta")
    @Produces({"application/json"})
    public List<ConsultaOrdenes> consultarOrdenes(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idPrueba") String idPrueba) throws Exception {
        OrdenesController ordenes = new OrdenesController();
        return ordenes.consultaOrdenes(Long.valueOf(idPrueba));
    }

    @GET
    @Path("/despachaOrden")
    @Produces({"application/json"})
    public RespuestaDespacho despachaOrden(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idOrden") String idOrden, @QueryParam("tipoDespacho") String tipoDespacho, @QueryParam("placa") String placa, @QueryParam("conductor") String conductor) throws Exception {
        OrdenesController ordenes = new OrdenesController();
        return ordenes.despacharOrden(Long.valueOf(idOrden), tipoDespacho, placa, conductor);
    }

    @GET
    @Path("/obtieneInformacionGeneralPDS")
    @Produces({"application/json"})
    public List<InformacionControlPdsSuplencia> obtieneInformacionGeneralPDS(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("prueba") Long prueba, @QueryParam("evento") Long evento) throws Exception {
        return new OrdenesController().obtieneInformacionGeneralPDS(prueba, evento);
    }

    @GET
    @Path("/obtieneInformacionPDS")
    @Produces({"application/json"})
    public List<InformacionDetalladaPDS> obtieneInformacionPDS(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("pds") Long pds, @QueryParam("evento") Long evento) throws Exception {
        return new OrdenesController().obtieneInformacionPDS(pds, evento);
    }

    @GET
    @Path("/obtieneInformacionOrdenesPDS")
    @Produces({"application/json"})
    public List<InformacionOrdenPDS> obtieneInformacionOrdenesPDS(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("pds") Long pds, @QueryParam("evento") Long evento) throws Exception {
        return new OrdenesController().obtieneInformacionOrdenesPDS(pds, evento);
    }

    @GET
    @Path("/listaDespachados")
    @Produces({"application/json"})
    public List<ListadoRelacionOrden> listarDespachados(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idOrden") String idOrden, @QueryParam("tipo") String tipo) throws Exception {
        OrdenesController ordenes = new OrdenesController();
        return ordenes.listarDespachados(Long.valueOf(idOrden), tipo);
    }

    @GET
    @Path("/listaDespachadosOrden")
    @Produces({"application/json"})
    public List<ListadoRelacionOrden> listadoAsignacionOrdenEstado(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idOrden") String idOrden, @QueryParam("estado") String estado) throws Exception {
        OrdenesController ordenes = new OrdenesController();
        return ordenes.listadoAsignacionOrdenEstado(Long.valueOf(idOrden), Integer.valueOf(estado));
    }

    @POST
    @Path("/registrar")
    @Produces({"application/json"})
    public Orden registrarOrdenPost(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("codigoEvento") String codigoEvento, @QueryParam("codigoSitio") String codigoSitio) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Inicio registrarOrdenPost");
        try {
            HttpSession sesion;
            DivipolDao divipol = new DivipolDao();
            Prueba prueba = new Prueba();
            Evento evento = new Evento();
            EventoDao daoE = new EventoDao();
            EmpleadoSesion emp = new EmpleadoSesion();
            evento = daoE.GetById(Integer.parseInt(codigoEvento));
            prueba = evento.getPrueba();
            Orden orden = new Orden();
            orden.setCodigoEvento(Long.valueOf(codigoEvento));
            orden.setCodigoSitio(codigoSitio);
            /*obtener el iddivipol a partir del codigo de puesto*/
            // orden.setIdDivipolStio(((Orden) divipol.getIdByCodigoSitio(codigoSitio, String.valueOf(prueba.getIdprueba()))).getIdDivipolStio());
            orden.setIdDivipolStio(Long.valueOf(codigoSitio));//codigoEvento);
            sesion = request.getSession();
            emp = (EmpleadoSesion) sesion.getAttribute("empleado");
            orden.setUsuarioOrden(Long.valueOf(request.getUserPrincipal().toString()));
            OrdenesController ordenes = new OrdenesController();
            return ordenes.registrarOrden(orden);
        } catch (Exception ex) {
            Logger.getLogger(OrdenesRest.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @POST
    @Path("/registrarDetalles")
    @Produces({"application/json"})
    public Response registraDetalleOrden(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("detalles") String detalles) throws Exception {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        OrdenesController ordenes = new OrdenesController();
        ArrayList<DetalleOrden> listaDetalles = new ArrayList<DetalleOrden>();
        JSONArray arr = new JSONArray(detalles);
        for (int i = 0; i < arr.length(); i++) {
            DetalleOrden detalle = new DetalleOrden();
            JSONObject objeto = arr.getJSONObject(i);
            detalle.setIdOrden(objeto.getLong("idOrden"));
            detalle.setCantidad(objeto.getInt("cantidad"));
            detalle.setCodigoCargo(objeto.getString("codigoCargo"));
            detalle.setIdpds(objeto.getLong("idpds"));
            listaDetalles.add(detalle);
        }
        if (ordenes.registraDetalleOrden(listaDetalles)) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Actualizado");
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

    @POST
    @Path("/registrarDetallesRespuesta")
    @Produces({"application/json"})
    public Response registraDetalleOrdenRespuesta(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("detalles") String detalles, @QueryParam("estado") String estado) throws Exception {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        EstadosOrden estadoDespachos = new EstadosOrden();
        OrdenesController ordenes = new OrdenesController();
        ArrayList<DespachoDetalleRespuesta> listaDetalles = new ArrayList<DespachoDetalleRespuesta>();
        JSONArray arr = new JSONArray(detalles);
        for (int i = 0; i < arr.length(); i++) {
            DespachoDetalleRespuesta detalle = new DespachoDetalleRespuesta();
            JSONObject objeto = arr.getJSONObject(i);
            detalle.setIdOrden(objeto.getLong("orden"));
            detalle.setObservaciones(objeto.getString("observacion"));
            detalle.setIdsuplenciadetalle(objeto.getLong("idsuplenciadetalle"));
            EstadosOrdenDao estadoP = new EstadosOrdenDao();
            EstadosGeneral estadoF = estadoP.buscarPorCodigoEstadoAsignacion(objeto.getString("estado"));
            EstadosOrden estadoO = estadoP.buscarPorCodigo(objeto.getString("estadoOrden"));
            detalle.setEstadoOrden(estadoO.getIdEstadoOrden().intValue());
            detalle.setEstadoPersona((estadoF.getId().intValue()));
            listaDetalles.add(detalle);
        }
        if (ordenes.actualizaEstadoDetalleDespacho(listaDetalles)) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Actualizado");
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

    @POST
    @Path("/registrarDetallesDespacho")
    @Produces({"application/json"})
    public Response registraDetalleOrdenDespacho(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("detalles") String detalles, @QueryParam("estado") String estado) throws Exception {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        EstadosOrden estadoDespachos = new EstadosOrden();
        OrdenesController ordenes = new OrdenesController();
        ArrayList<DespachoDetalleRespuesta> listaDetalles = new ArrayList<DespachoDetalleRespuesta>();
        JSONArray arr = new JSONArray(detalles);
        for (int i = 0; i < arr.length(); i++) {
            DespachoDetalleRespuesta detalle = new DespachoDetalleRespuesta();
            JSONObject objeto = arr.getJSONObject(i);
            detalle.setIdOrden(objeto.getLong("orden"));
            detalle.setObservaciones(objeto.getString("observacion"));
            detalle.setIdsuplenciadetalle(objeto.getLong("idsuplenciadetalle"));
            EstadosOrdenDao estadoP = new EstadosOrdenDao();
            EstadosGeneral estadoF = estadoP.buscarPorCodigoEstadoAsignacion(objeto.getString("estado"));
            detalle.setEstadoPersona((estadoF.getId().intValue()));
            detalle.setTipoTransporte(objeto.getInt("opcionDespacho"));
            detalle.setNombreConductor(objeto.getString("conductor"));
            detalle.setPlacaMovil(objeto.getString("placa"));
            listaDetalles.add(detalle);
        }
        if (ordenes.actualizaEstadoDetalleDespachoI(listaDetalles)) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Actualizado");
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
    //metodo actual para el despacho
    @POST
    @Path("/actualizarDetallesDespacho")
    @Produces({"application/json"})
    public Response actualizarDetalleOrdenDespacho(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("detalles") String detalles, @QueryParam("estado") String estado) throws Exception {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        EstadosOrden estadoDespachos = new EstadosOrden();
        OrdenesController ordenes = new OrdenesController();
        ArrayList<DespachoDetalleRespuesta> listaDetalles = new ArrayList<DespachoDetalleRespuesta>();
        JSONArray arr = new JSONArray(detalles);
        for (int i = 0; i < arr.length(); i++) {
            DespachoDetalleRespuesta detalle = new DespachoDetalleRespuesta();
            JSONObject objeto = arr.getJSONObject(i);
            detalle.setIdOrden(objeto.getLong("orden"));
            detalle.setObservaciones(objeto.getString("observacion"));
            detalle.setIdsuplenciadetalle(objeto.getLong("idsuplenciadetalle"));
            EstadosOrdenDao estadoP = new EstadosOrdenDao();
            EstadosGeneral estadoF = estadoP.buscarPorCodigoEstadoAsignacion(objeto.getString("estado"));
            detalle.setEstadoPersona((estadoF.getId().intValue()));
            detalle.setTipoTransporte(objeto.getInt("opcionDespacho"));
            detalle.setNombreConductor(objeto.getString("conductor"));
            detalle.setPlacaMovil(objeto.getString("placa"));
            listaDetalles.add(detalle);
        }
        if (ordenes.actualizaDetalleDespachoI(listaDetalles)) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Actualizado");
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
    @Path("/prueba")
    @Produces({"application/json"})
    public String prueba(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("cadena") String cadena) {
        String respuesta = "xxx";
        return respuesta;
    }

    @GET
    @Path("/obtieneDetallesGenerales")
    @Produces({"application/json"})
    public List<DetallesOrdenGeneral> obtieneDetallesGenerales(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idOrden") String idOrden) throws Exception {
        OrdenesController ordenes = new OrdenesController();
        return ordenes.obtieneDetallesGenerales(Long.valueOf(idOrden));
    }

    @GET
    @Path("/despachosxOrden")
    @Produces({"application/json"})
    public List<InformacionDespachosOrden> despachosxOrden(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idOrden") Long idOrden) throws Exception {
        return new OrdenesController().despachosxOrden(idOrden);
    }

    @GET
    @Path("/consultarCapacidadSolicitud")
    @Produces({"application/json"})
    public RespuestaGenerica consultarCapacidadSolicitud(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idSitio") Long idSitio, @QueryParam("evento") Integer evento, @QueryParam("cargo") Integer cargo) throws Exception {        
        return new OrdenesController().consultarCapacidadSolicitud(idSitio, evento, cargo);
        
    }

    @GET
    @Path("/listarOrdenesxUsuario")
    @Produces({"application/json"})
    public List<Orden> listarOrdenesxUsuario(@javax.ws.rs.core.Context HttpServletRequest request) throws Exception {
        EmpleadoSesion emp = new EmpleadoSesion();
        emp = (EmpleadoSesion) request.getSession().getAttribute("empleado");
        return new OrdenesController().listarOrdenesxUsuario(emp);
    }

    @GET
    @Path("/listarOrdenesxEventoxSitio")
    @Produces({"application/json"})
    public List<Orden> listarOrdenesxEventoxSitio(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("evento") String evento, @QueryParam("sitio") String sitio) throws Exception {
        return new OrdenesController().listarOrdenesxEventoxSitio(evento, sitio);
    }

    @GET
    @Path("/consultarDisponibilidadPersonal")
    @Produces({"application/json"})
    public PersonalOrdenSuplencia consultarDisponibilidadPersonal(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idDivipol") Long idDivipol, @QueryParam("codigoEvento") Long codigoEvento, @QueryParam("codigoCargo") Long codigoCargo, @QueryParam("nrodoc") Long nrodoc, @QueryParam("cambioSuplencia") Integer cambioSuplencia) throws Exception {
        return new OrdenesController().consultarDisponibilidadPersonal(idDivipol, codigoEvento, codigoCargo, nrodoc, cambioSuplencia);
    }

    @GET
    @Path("/listadoAsignacionOrdenEstadoPds")
    @Produces({"application/json"})
    public List<ListadoRelacionOrden> listadoAsignacionOrdenEstadoPds(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idOrden") Long idOrden, @QueryParam("tipo") String tipo, @QueryParam("pds") Long pds) throws Exception {
        return new OrdenesController().listadoAsignacionOrdenEstadoPds(idOrden, tipo, pds);
    }

    @GET
    @Path("/ordenesxPds")
    @Produces({"application/json"})
    public List<Orden> ordenesxPds(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idDivipolPds") Long idDivipolPds) throws Exception {
        return new OrdenesController().ordenesxPds(idDivipolPds);
    }

    @GET
    @Path("/cambiaEstadoHistorial")
    @Produces({"application/json"})
    public String cambiaEstadoHistorial(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("estado") String estado, @QueryParam("guardaHistorial") boolean guardaHistorial, @QueryParam("idOrden") Long idOrden) throws Exception {
        if (new OrdenesController().cambiaEstadoHistorial(estado, true, idOrden)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("/obtieneInformacionTodosPDS")
    @Produces({"application/json"})
    public List<InformacionDetalladaPDS> obtieneInformacionTodosPDS(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idEvento") Long idEvento) throws Exception {
        return new OrdenesController().obtieneInformacionTodosPDS(idEvento);
    }


    @GET
    @Path("/obtieneOrdenesxSitio")
    @Produces({"application/json"})
    public List<OrdenCompleta> obtieneOrdenesxSitio(@javax.ws.rs.core.Context HttpServletRequest request,@QueryParam("idDivipol") Long idDivipol) throws Exception{
        return new OrdenesController().obtieneOrdenesxSitio(idDivipol);
    }
    

    @GET
    @Path("/SeguimientoOrdenesPds")
    @Produces({"application/json"})
    public String SeguimientoOrdenesPds(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("codigoEvento") int codigoEvento,@QueryParam("iddivipol") int iddivipol) {
        OrdenesReportesDao reporte = new OrdenesReportesDao();
        return reporte.seguimientoOrdenesSuplencia(codigoEvento,iddivipol);
    }
    
    @GET
    @Path("/SeguimientoPersonalPds")
    @Produces({"application/json"})
    public String SeguimientoPersonalPds(@javax.ws.rs.core.Context HttpServletRequest request, 
            @QueryParam("idprueba") int idprueba) {
        OrdenesReportesDao reporte = new OrdenesReportesDao();
        return reporte.seguimientoPersonalPds(idprueba);
    }


    @POST
    @Path("/actualizarRelacionTransporte")
    @Produces({"application/json"})
    public String actualizarRelacionTransporte(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("relacion") String relacion) throws Exception {
       
        return (new OrdenesController().actualizarRelacionTransporte(relacion)).toString();
    }

    @POST
    @Path("/actualizarRecepcionDevueltos")
    @Produces({"application/json"})
    public Response actualizarRecepcionDevueltos(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("detalles") String detalles) throws Exception {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        OrdenesController ordenes = new OrdenesController();
        ArrayList<ActualizaRecepcionDevolucion> listaDetalles = new ArrayList<ActualizaRecepcionDevolucion>();
        JSONArray arr = new JSONArray(detalles);
        for (int i = 0; i < arr.length(); i++) {
            ActualizaRecepcionDevolucion detalle = new ActualizaRecepcionDevolucion();
            JSONObject objeto = arr.getJSONObject(i);
            detalle.setIdDetalle(objeto.getLong("idDetalle"));
            detalle.setObservacion(objeto.getString("observaciones2"));
            listaDetalles.add(detalle);
        }
        if (ordenes.actualizarRecepcionDevueltos(listaDetalles)) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Actualizado");
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
    @Path("/listarInformacionDetalleDespacho")
    @Produces({"application/json"})
    public List<DetallesOrdenGeneral> listarInformacionDetalleDespacho(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idOrden") Long idOrden, @QueryParam("idPDS") int idPDS) throws Exception {

        return new OrdenesController().listarInformacionDetalleDespacho(idOrden, idPDS);
    }

    @GET
    @Path("/listarPersonasDisponibles")
    @Produces({"application/json"})
    public List<Empleado> listarPersonasDisponiblesParaAsignar(
            @QueryParam("idOrden") Long idOrden, @QueryParam("idPDS") int idPDS,
            @QueryParam("cargo") String codigoCargo) throws Exception {

        return new OrdenesController().listarPersonasDisponibles(idOrden, idPDS, codigoCargo);
    }

    @GET
    @Path("/actualizarNrodocDetalleDespacho")
    @Produces({"application/json"})
    public String actualizarNrodocDetalleDespacho(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("nrodoc") long nrodoc, @QueryParam("id") long id) throws Exception {
        return new OrdenesController().actualizarNrodocDetalleSuplencia(nrodoc, id);
    }
    
    
    @GET
    @Path("/quitarPersonaAsignadaSuplencia")
    @Produces({"application/json"})
    public String quitarPersonaAsignadaSuplencia(
            @QueryParam("nrodoc") long nrodoc, @QueryParam("idDetalle") long idDetalle,
            @QueryParam("idOrden") long idOrden, @QueryParam("codigoCargo") long codigoCargo ) throws Exception {
        return new OrdenesController().quitarPersonaAsignadaSuplencia(nrodoc, idOrden, idDetalle, codigoCargo);
    }
}
