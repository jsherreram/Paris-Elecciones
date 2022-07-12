/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.EmpleadoDao;
import co.com.grupoasd.nomina.dao.PersonaAsignadaDao;
import co.com.grupoasd.nomina.dao.PruebaDao;
import co.com.grupoasd.nomina.modelo.PersonaAsignada;
import co.com.grupoasd.nomina.modelo.Respuesta;
import co.com.grupoasd.nomina.negocio.personaAsignada.PersonaAsignadaBusiness;
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
import org.json.JSONException;

/**
 *
 * @author Pedro Rodriguez
 */
@Path("/personaAsignada")
public class PersonaAsignadaRest {

    @PUT
    @Consumes({"application/json", "application/xml"})
    public Response modificar(PersonaAsignada p) {

        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();

        //si el numero de empleado es 0 ó es un empleado valido se asigna y se des-asigna
        if (p.getEmpleado().getNrodoc() == 0) {
            if (personaAsignadaDao.actualizar(p)) {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("200");
                respuesta.setDescripcion("Actualizado");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
            } else {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("201");
                respuesta.setDescripcion("No se actualizo el registro.");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
            }
        }

        EmpleadoDao empleadoDao = new EmpleadoDao();
        //validar si el empleado existe en registro de terceros

        int idEmp = empleadoDao.GetIdByNumeroDocumento(p.getEmpleado().getNrodoc());

        if (idEmp == 0) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("202");
            respuesta.setDescripcion("Identificación no existe como tercero");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }

        //validar si el empleado ya fue asignado
        PersonaAsignada personaAsignada = personaAsignadaDao.getPersonaAsignada(p.getEvento().getCodigoEvento(), p.getEmpleado().getNrodoc());

        if (personaAsignada.getEmpleado() != null) {
            if (personaAsignada.getEspolivalente() == 0) {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("202");
                respuesta.setDescripcion("Identificación se encuentra asignada en: \n"
                        + "Salon: " + personaAsignada.getCargo().getDescripcion() + "\n"
                        + "Departamento: " + personaAsignada.getMunicipio().getDepartamento().getNombre() + "\n"
                        + "Municipio: " + personaAsignada.getMunicipio().getNombre() + "\n"
                        + "Zona: " + personaAsignada.getZona() + "\n"
                        + "Puesto: " + personaAsignada.getPuesto() + "\n"
                        + "Ubicacion: " + personaAsignada.getUbicacion() + "\n"
                        + "Fecha: " + personaAsignada.getEvento().getFecha() + "\n"
                        + "Hora: " + personaAsignada.getEvento().getHora_inicial());
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
            }
        }

        PersonaAsignada personaAsignadatipo = personaAsignadaDao.getPersonaAsignadaTipo(p.getEvento().getPrueba().getIdprueba(), p.getEvento().getCodigoEvento(), p.getEmpleado().getNrodoc());

        if (personaAsignadatipo.getEmpleado() != null) {
            if (personaAsignada.getEspolivalente() == 0) {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("202");
                respuesta.setDescripcion("Identificación se encuentra asignada en: \n"
                        + "Salon: " + personaAsignadatipo.getCargo().getDescripcion() + "\n"
                        + "Departamento: " + personaAsignadatipo.getMunicipio().getDepartamento().getNombre() + "\n"
                        + "Municipio: " + personaAsignadatipo.getMunicipio().getNombre() + "\n"
                        + "Zona: " + personaAsignadatipo.getZona() + "\n"
                        + "Puesto: " + personaAsignadatipo.getPuesto() + "\n"
                        + "Ubicacion: " + personaAsignadatipo.getUbicacion() + "\n"
                        + "Fecha: " + personaAsignadatipo.getEvento().getFecha() + "\n"
                        + "Hora: " + personaAsignadatipo.getEvento().getHora_inicial());

                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
            }
        }

        if (personaAsignadaDao.actualizar(p)) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Actualizado");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("El registro no fue actualizado.");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    @GET
    @Path("/listar")
    @Produces({"application/json", "application/xml"})
    public List<PersonaAsignada> listar(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento, @QueryParam("idDepartamento") String idDepartamento,
            @QueryParam("idMunicipio") String idMunicipio, @QueryParam("idCargo") String idCargo,
            @QueryParam("idZona") String idZona, @QueryParam("idPuesto") String idPuesto) {
        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        return personaAsignadaDao.listar(idEvento, idDepartamento, idMunicipio, idCargo, idZona, idPuesto);
    }

    @GET
    @Path("/listarSitio")
    @Produces({"application/json", "application/xml"})
    public List<PersonaAsignada> listarSitio(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento, @QueryParam("idPuesto") String idPuesto) {
        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        return personaAsignadaDao.listar(idEvento, idPuesto);
    }

    @GET
    @Path("/asistencia")
    @Produces({"application/json", "application/xml"})
    public List<PersonaAsignada> asistencia(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("nrodoc") int nrodoc) {

        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        return personaAsignadaDao.listar(nrodoc);
    }

    @GET
    @Path("/asistenciaSitio")
    @Produces({"application/json", "application/xml"})
    public List<PersonaAsignada> asistenciaSitio(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento, @QueryParam("usuario") String usuario) {
        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        return personaAsignadaDao.listar(idEvento, usuario);
    }

    @GET
    @Path("/asistenciaSitioEvento")
    @Produces({"application/json", "application/xml"})
    public List<PersonaAsignada> asistenciaSitioEvento(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento, @QueryParam("codigoSitio") String codigoSitio) {
        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        return personaAsignadaDao.listarXSitio(idEvento, codigoSitio);
    }

    @GET
    @Path("/listarXSitio")
    @Produces({"application/json", "application/xml"})
    public List<PersonaAsignada> listarXSitio(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento, @QueryParam("codigoSitio") String codigoSitio) {
        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        return personaAsignadaDao.listarXSitio(idEvento, codigoSitio);
    }

    @GET
    @Path("/buscar")
    @Produces({"application/json", "application/xml"})
    public PersonaAsignada findById(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int id) {
        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        return personaAsignadaDao.getById(id);
    }

    @GET
    @Path("/cargarPersonalTx")
    @Produces({"application/json", "application/xml"})
    public Response cargarPersonalTx(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento, @QueryParam("idCargo") String idCargo) {

        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();

        //if(personaAsignadaDao.cargar(idEvento, idCargo))
        if (personaAsignadaDao.cargarDetallesTransmision(idEvento, "12")) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Personal cargado");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Error al cargar personal.");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }

    }

    @POST
    @Consumes({"application/json"})
    public Response cargarAgendaCapacitacion(co.com.grupoasd.nomina.modelo.Capacitacion agendaCapacitacion) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();

        if (personaAsignadaDao.cargarAgendaCapacitacion(agendaCapacitacion)) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Agenda creada");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Error al crear la Lista.");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    @GET
    @Path("/listarCargosAsignados")
    @Produces({"application/json", "application/xml"})
    public List<PersonaAsignada> listarCargosAsignados(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idDcpe") int idDcpe, @QueryParam("idPrueba") int idPrueba) {

        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        return personaAsignadaDao.listarCargosAsignados(idDcpe, idPrueba);
    }

    @GET
    @Path("/listarPersonasAsignadasaCargo")
    @Produces({"application/json", "application/xml"})
    public List<PersonaAsignada> listarPersonasAsignadasaCargo(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba, @QueryParam("codigoEvento") int evento,
            @QueryParam("codigoPuesto") String codigoPuesto, @QueryParam("codigoCargo") String codigoCargo) {

        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        return personaAsignadaDao.listarPersonasAsignadasaCargo(evento, idPrueba, codigoCargo, codigoPuesto);
    }

    @GET
    @Path("/actualizarNrodoc")
    @Produces({"application/json", "application/xml"})
    public Response cambiarPersonaCargo(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("personaA") int p1, @QueryParam("personaB") int p2,
            @QueryParam("nrodocA") long nrodocA, @QueryParam("nrodocB") long nrodocB) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();

        if (personaAsignadaDao.actualizarPersonaCargo(p1, nrodocB)) {
            if (personaAsignadaDao.actualizarPersonaCargo(p2, nrodocA)) {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("200");
                respuesta.setDescripcion("Agenda creada");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
            } else {
                personaAsignadaDao.actualizarPersonaCargo(p1, nrodocA);
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("201");
                respuesta.setDescripcion("Error al actualizar");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
            }
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Error al actualizar");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    @GET
    @Path("/actualizarSalon")
    @Produces({"application/json", "application/xml"})
    public Response cambiarPersonaSalon(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("personaA") int p1, @QueryParam("personaB") int p2,
            @QueryParam("salonA") String salonA, @QueryParam("salonB") String salonB) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();

        if (personaAsignadaDao.actualizarPersonaSalon(p1, salonB)) {
            if (personaAsignadaDao.actualizarPersonaSalon(p2, salonA)) {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("200");
                respuesta.setDescripcion("Agenda creada");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
            } else {
                personaAsignadaDao.actualizarPersonaSalon(p1, salonA);
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("201");
                respuesta.setDescripcion("Error al actualizar");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
            }
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Error al actualizar");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    @GET
    @Path("/listarSalonesAsignados")
    @Produces({"application/json", "application/xml"})
    public List<PersonaAsignada> listarSalonesAsignados(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba, @QueryParam("codigoEvento") int evento,
            @QueryParam("codigoPuesto") String codigoPuesto, @QueryParam("salon") String salon,
            @QueryParam("codigoCargo") String codigoCargo) {

        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        return personaAsignadaDao.listarSalonesAsignados(evento, idPrueba, salon, codigoPuesto, codigoCargo);
    }

    @GET
    @Path("/buscarPersonaAsignadaSalon")
    @Produces({"application/json", "application/xml"})
    public PersonaAsignada buscarPersonaAsignadaSalon(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int id) {

        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        return personaAsignadaDao.buscarPersonaAsignadaSalon(id);
    }

    /**
     * Busca el historial donde ha sido asignada la person
     *
     * @param request
     * @param idEmpleado
     */
    @GET
    @Path("/listarHistorialLaboral")
    @Produces({"application/json", "application/xml"})
    public List<PersonaAsignada> listarHistorialLaboralPersona(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int idEmpleado) {
        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        return personaAsignadaDao.listarHistorialLaboralPersona(idEmpleado);
    }

    /**
     * Valida si hay al menos una persona titular sin asistencia y un suplente
     * con asistencia
     *
     * @param request
     * @param idEmpleado
     */
    @GET
    @Path("/validarAsistenciaTitularSuplente")
    @Produces({"application/json", "application/xml"})
    public String validarAsistenciaTitularySuplente(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int codigoEvento, @QueryParam("usuario") String usuario) throws JSONException {
        PersonaAsignadaBusiness validar = new PersonaAsignadaBusiness();
        return validar.validarPersonasTitularesySuplentesAsignados(codigoEvento, usuario);
    }

    /**
     * Valida si hay al menos una persona titular sin asistencia y un suplente
     * con asistencia
     *
     * @param idEvento
     * @param idPrueba
     * @param longitud
     * @param latitud
     */
    @GET
    @Path("/buscarSuplentesConAsistenciaCapacitacion")
    @Produces({"application/json", "application/xml"})
    public String buscarSuplentesConAsistenciaCapacitacion(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int codigoEvento, @QueryParam("idPrueba") int idPrueba,
            @QueryParam("longitud") float longitud, @QueryParam("latitud") float latitud) throws JSONException {
        PersonaAsignadaBusiness validar = new PersonaAsignadaBusiness();
        return validar.buscarSuplentesConAsistenciaCapacitacion(codigoEvento, idPrueba, latitud, longitud);
    }

    @GET
    @Path("/personalEnCapacitacion")
    @Produces({"application/json"})
    public String personalEnCapacitacion(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("usuario") int usuario, @QueryParam("idPrueba") int idPrueba) {
        PersonaAsignadaDao reporte = new PersonaAsignadaDao();
        return reporte.personalEnCapacitacion(usuario, idPrueba);
    }

    /*
     * Desasigna a una persona del sitio donde estaba y lo asigna en uno nuevo(PDS)
     */
    @GET
    @Path("/asignarPersonaOtroSitioAPDS")
    @Produces({"application/json", "application/xml"})
    public String asignarPersonaOtroSitioAPDS(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int id, @QueryParam("nrodoc") long nrodoc) throws JSONException {
        PersonaAsignadaBusiness validar = new PersonaAsignadaBusiness();
        return validar.asignarPersonaOtroSitioAPDS(id, nrodoc);
    }

    /*
     * Desasigna a una persona del sitio donde estaba y lo asigna en uno nuevo(PDS)
     */
    @GET
    @Path("/desasignarPersonaSitio")
    @Produces({"application/json", "application/xml"})
    public String desasignarPersona(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int id) throws JSONException {
        PersonaAsignadaBusiness validar = new PersonaAsignadaBusiness();
        return validar.desasignarPersonaSitio(id);
    }

    @GET
    @Path("/listarAsistenciaSitioEvento")
    @Produces({"application/json", "application/xml"})
    public List<PersonaAsignada> listarAsistenciaSitioEvento(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento, @QueryParam("idDivipol") int idDivipol) {
        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        return personaAsignadaDao.listarXSitioEvento(idEvento, idDivipol);
    }
    
    
    @GET
    @Path("/listarPorCargoEventoMunicipio")
    @Produces({"application/json", "application/xml"})
    public List<PersonaAsignada> listarPorEventoCargoMunicipio(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento, @QueryParam("codigoMunicipio") String idMunicipio, 
            @QueryParam("codigoCargo") String idCargo, @QueryParam("codigoDepartamento") String codigoDepartamento) {
        PersonaAsignadaDao personaAsignadaDao = new PersonaAsignadaDao();
        return personaAsignadaDao.listarPorCargoEventoMunicipio(idEvento, idMunicipio, idCargo, codigoDepartamento);
    }


}
