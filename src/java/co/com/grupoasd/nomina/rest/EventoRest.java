/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.EventoDao;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.Evento;
import co.com.grupoasd.nomina.modelo.InformacionConsultaOrden;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.Respuesta;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 *
 * @author Pedro Rodriguez
 */
@Path("/evento")
public class EventoRest {

    @GET
    @Path("/listarAll")
    @Produces({"application/json"})
    public List<Evento> listarAll(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idPrueba") int idPrueba) {

        List<Evento> levento = new ArrayList<>();
        if (request.getUserPrincipal() != null) {
            EventoDao evento = new EventoDao();
            return evento.listarAll(idPrueba);
        } else {
            return levento;
        }
    }

    @GET
    @Path("/listarDepartamento")
    @Produces({"application/json"})
    public List<Evento> listarDepartamento(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba, @QueryParam("usuario") String usuario) {

        List<Evento> levento = new ArrayList<>();
        if (request.getUserPrincipal() != null) {
            EventoDao evento = new EventoDao();
            return evento.listarDepartamento(idPrueba, usuario);
        } else {
            return levento;
        }
    }

    @GET
    @Path("/listarEvento")
    @Produces({"application/json"})
    public List<Evento> listarEvento(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("codigoEvento") int codigoEvento) {
        List<Evento> levento = new ArrayList<>();
        if (request.getUserPrincipal() != null) {
            EventoDao evento = new EventoDao();
            return evento.listarEvento(codigoEvento);
        } else {
            return levento;
        }
    }

    @GET
    @Path("/listarXSitio")
    @Produces({"application/json", "application/xml"})
    public List<Evento> listarXSitio(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba) {
        EventoDao eventoDao = new EventoDao();
        return eventoDao.listar(idPrueba, request.getUserPrincipal().getName());
    }

    @GET
    @Path("/GetEventoActualParaTomaAsistencia")
    @Produces({"application/json"})
    public Evento GetEventoActualParaTomaAsistencia(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba,
            @QueryParam("idDivipol") int idDivipol) {
        EventoDao eventoDao = new EventoDao();
        return eventoDao.GetEventoActualParaTomaAsistencia(idPrueba, idDivipol);
    }

    @GET
    @Path("/listarXSitioSinNombramiento")
    @Produces({"application/json", "application/xml"})
    public List<Evento> listarXSitioSinNombramiento(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba) {
        EventoDao eventoDao = new EventoDao();
        return eventoDao.listar(idPrueba);
    }

    @GET
    @Path("/buscar")
    @Produces({"application/xml", "application/json"})
    public Evento find(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int id) {
        EventoDao eventoDao = new EventoDao();
        return eventoDao.GetById(id);
    }

    @GET
    @Path("/GetEventoNombramiento")
    @Produces({"application/json"})
    public Evento GetEventoNombramiento(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int id) {

        Prueba prueba = new Prueba();
        prueba.setIdprueba(id);
        EventoDao eventoDao = new EventoDao();
        return eventoDao.GetEventoNombramiento(prueba);
    }

    @GET
    @Path("/buscarInfoOrdenes")
    @Produces({"application/json"})
    public InformacionConsultaOrden consultaOrdenxEvento(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("id") String id, @QueryParam("sitio") String sitio) {
        EventoDao dao = new EventoDao();
        return dao.consultaOrdenxEvento(Long.valueOf(id), sitio);
    }

    @GET
    @Path("/buscarInforOrdenesxEvento")
    @Produces({"application/json"})
    public InformacionConsultaOrden consultaOrdenxEventoOrden(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idOrden") String idOrden) {
        EventoDao dao = new EventoDao();
        return dao.consultaOrdenxEventoOrden(idOrden);
    }

    @GET
    @Path("/listarTipSesion")
    @Produces({"application/json"})
    public String listarTipSesion(@javax.ws.rs.core.Context HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            EventoDao pruebaBussines = new EventoDao();
            return pruebaBussines.listarTipSesion();
        } else {
            return "";
        }
    }

    @POST
    @Path("/crear")
    @Consumes({"application/json", "application/xml"})
    public Response crear(Evento evento) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        EventoDao eventoDao = new EventoDao();

        int codigoEvento = eventoDao.insertar(evento);
        if (codigoEvento > 0) {
            evento.setCodigoEvento(codigoEvento);
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Creado");
            respuesta.setId(codigoEvento);
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("C??digo de Evento ya existe, o se genero un problema al insertar");
            respuesta.setId(codigoEvento);
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    /**
     * Author: John Steak Herrera Moreno 
     * Date: 06/05/2022
     * 
     * @param evento
     * @return 
     */
    @POST
    @Path("/create")
    @Consumes({"application/json", "application/xml"})
    public Response create(Evento evento) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        EventoDao eventoDao = new EventoDao();

        int codigoEvento = eventoDao.insertarEvento(evento);
        if (codigoEvento > 0) {
            evento.setCodigoEvento(codigoEvento);
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Creado");
            respuesta.setId(codigoEvento);
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("C??digo de Evento ya existe, o se genero un problema al insertar");
            respuesta.setId(codigoEvento);
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }
    
    @POST
    @Path("/actualizar")
    @Consumes({"application/json", "application/xml"})
    public Response actualizar(Evento evento) {
        ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        EventoDao eventoDao = new EventoDao();
        if (eventoDao.Actualizar(evento)) {
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

    /**
     * Author: John Steak Herrera Moreno 
     * Date: 06/05/2022
     * 
     * @param evento
     * @return 
     */
    @POST
    @Path("/update")
    @Consumes({"application/json", "application/xml"})
    public Response update(Evento evento) {
        ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        EventoDao eventoDao = new EventoDao();
        if (eventoDao.actualizarEvento(evento)) {
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
    @Path("/actualizarCapacitacionDcpe")
    @Consumes({"application/json"})
    public Response actualizarCapacitacionDcpe(Evento evento) {
        ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        EventoDao tevento = new EventoDao();
        if (tevento.ActualizarCapacitacionDcpe(evento)) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Actualizado");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("No se actualizo ?");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    @GET
    @Path("/listarEventosAplicacion")
    @Produces({"application/json", "application/xml"})
    public List<Evento> listarEventosAplicacion(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba) {
        EventoDao eventoDao = new EventoDao();

        return eventoDao.listarEventosAplicacion(idPrueba, request.getUserPrincipal().getName());
    }

    @GET
    @Path("/listarEventosCapacitacion")
    @Produces({"application/json", "application/xml"})
    public List<Evento> listarEventosCapacitacion(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba, @QueryParam("usuario") String usuario) {
        EventoDao eventoDao = new EventoDao();
        return eventoDao.listarEventosCapacitacion(idPrueba, usuario);
    }

    @GET
    @Path("/listarEventoPorSitio")
    @Produces({"application/json", "application/xml"})
    public List<Evento> listarEventoPorSitio(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idDivipol") int idDivipol) {
        EventoDao eventoDao = new EventoDao();
        return eventoDao.listarEventoPorSitio(idDivipol);
    }

    @GET
    @Path("/listarEventoNoCapacitacionPorSitio")
    @Produces({"application/json", "application/xml"})
    public List<Evento> listarEventoNoCapacitacionPorSitio(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idDivipol") int idDivipol) {
        EventoDao eventoDao = new EventoDao();
        return eventoDao.listarEventoNoCapacitacionPorSitio(idDivipol);
    }

    @GET
    @Path("/listarEventosDepartamento")
    @Produces({"application/json", "application/xml"})
    public List<Evento> listarEventosPorDepartamento(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba, @QueryParam("idDpto") String departamento) {
        EventoDao eventoDao = new EventoDao();
        return eventoDao.listarEventosPorDepartamento(idPrueba, departamento);
    }
    
    /**
     * Author: John Steak Herrera Moreno 
     * Date: 17/05/2022
     * 
     * @param request
     * @param idPrueba
     * @return 
     */
    @GET
    @Path("/findAllEventosByIdPruebaNoCapacitacion")
    @Produces({"application/json", "application/xml"})
    public List<Evento> findAllEventosByIdPruebaNoCapacitacion(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba) {
        EventoDao eventoDao = new EventoDao();
        return eventoDao.findAllEventosByIdPruebaNoCapacitacion(idPrueba);
    }
    
}
