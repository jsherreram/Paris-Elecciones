/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.common.util.Constants;
import co.com.grupoasd.nomina.dao.EmpleadoDao;
import co.com.grupoasd.nomina.dao.EmpleadoPruebaEstadoDao;
import co.com.grupoasd.nomina.dao.NombramientoDao;
import co.com.grupoasd.nomina.dto.BusquedaPersonasDto;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EmpleadoPruebaEstado;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.Nombramiento;
import co.com.grupoasd.nomina.modelo.Respuesta;
import co.com.grupoasd.nomina.modelo.Sitio;
import co.com.grupoasd.nomina.modelo.wrapper.EmpleadoPruebaEstadoCargo;
import co.com.grupoasd.nomina.modelo.wrapper.NombramientoCargo;
import co.com.grupoasd.nomina.negocio.Divitrans.DivitransBussines;
import co.com.grupoasd.nomina.negocio.Divitrans.IDivitrans;
import co.com.grupoasd.nomina.negocio.nombramientomasivo.NombramientoController;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Pedro Rodriguez
 */
@Path("/nombramiento")
public class NombramientoRest {

    @PUT
    @Consumes({"application/json", "application/xml"})
    public Response modificar(Nombramiento p) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        NombramientoDao nombramientoDao = new NombramientoDao();

        //si el numero de empleado es 0 ó es un empleado valido se asigna
        if (p.getEmpleado().getNrodoc() == 0) {
            if (nombramientoDao.actualizar(p)) {
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
        if (empleadoDao.GetByNumeroDocumento(p.getEmpleado().getNrodoc(), p.getMunicipio().getDepartamento().getCodigo()).getNrodoc() == 0) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("202");
            respuesta.setDescripcion("Identificación no existe como tercero");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
        if (p.getSepuederepetir() == 0) {
            //validar si el empleado ya fue asignado
            Nombramiento nombramiento = nombramientoDao.getNombramiento(p.getIdPrueba(), p.getEmpleado().getNrodoc());
            if (nombramiento.getEmpleado() != null) {
                if ((nombramiento.getEspolivalente() == 0) || (nombramiento.getEspolivalente() == 1 && nombramiento.getCargoespolivalente() == 0)) {
                    Respuesta respuesta = new Respuesta();
                    respuesta.setCodigo("201");
                    respuesta.setDescripcion("Identificación se encuentra asignada en: \n"
                            + "Cargo: " + nombramiento.getCargo().getDescripcion() + "\n"
                            + "Departamento: " + nombramiento.getMunicipio().getDepartamento().getNombre() + "\n"
                            + "Municipio: " + nombramiento.getMunicipio().getNombre() + "\n"
                            + "Zona: " + nombramiento.getZona() + "\n"
                            + "Puesto: " + nombramiento.getPuesto() + "\n"
                            + "Ubicacion: " + nombramiento.getUbicacion());
                    rb.status(200);
                    rb.entity(respuesta);
                    return rb.build();
                }
                Nombramiento nombramiento_nuevo = nombramientoDao.getById(p.getId());
                if (nombramiento_nuevo.getCargo().getPolivalente() == 0) {
                    Respuesta respuesta = new Respuesta();
                    respuesta.setCodigo("201");
                    respuesta.setDescripcion("Cargo al que se Quiere Asignar no es Polivalente: \n"
                            + "Asignado en:\n"
                            + "Cargo: " + nombramiento.getCargo().getDescripcion() + "\n"
                            + "Departamento: " + nombramiento.getMunicipio().getDepartamento().getNombre() + "\n"
                            + "Municipio: " + nombramiento.getMunicipio().getNombre() + "\n"
                            + "Zona: " + nombramiento.getZona() + "\n"
                            + "Puesto: " + nombramiento.getPuesto() + "\n"
                            + "Ubicacion: " + nombramiento.getUbicacion());
                    rb.status(200);
                    rb.entity(respuesta);
                    return rb.build();
                }

                if (nombramiento_nuevo.getPuesto().equals(nombramiento.getPuesto()) && nombramiento_nuevo.getCargo().getCodigoCargo().equals(nombramiento.getCargo().getCodigoCargo())) {
                    Respuesta respuesta = new Respuesta();
                    respuesta.setCodigo("201");
                    respuesta.setDescripcion("Se esta Nombrando la Persona 2 Veces en el Mismo Sitio, con el Mismo Cargo: \n"
                            + "Asignado en:\n"
                            + "Cargo: " + nombramiento.getCargo().getDescripcion() + "\n"
                            + "Departamento: " + nombramiento.getMunicipio().getDepartamento().getNombre() + "\n"
                            + "Municipio: " + nombramiento.getMunicipio().getNombre() + "\n"
                            + "Zona: " + nombramiento.getZona() + "\n"
                            + "Puesto: " + nombramiento.getPuesto() + "\n"
                            + "Ubicacion: " + nombramiento.getUbicacion());
                    rb.status(200);
                    rb.entity(respuesta);
                    return rb.build();
                }
            }
        }

        if (nombramientoDao.actualizar(p)) {
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
    public List<Nombramiento> listar(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento, @QueryParam("idDepartamento") String idDepartamento,
            @QueryParam("idMunicipio") String idMunicipio, @QueryParam("idCargo") String idCargo,
            @QueryParam("idZona") String idZona, @QueryParam("idPuesto") String idPuesto) {
        NombramientoDao nombramientoDao = new NombramientoDao();
        return nombramientoDao.listar(idEvento, idDepartamento, idMunicipio, idCargo, idZona, idPuesto);
    }

    @GET
    @Path("/listarSitio")
    @Produces({"application/json", "application/xml"})
    public List<Nombramiento> listarSitio(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento, @QueryParam("idPuesto") String idPuesto) {
        NombramientoDao nombramientoDao = new NombramientoDao();

        if (request.isUserInRole("capacitador")) {
            return nombramientoDao.listarCapacitacion(idEvento, idPuesto);
        } else {
            return nombramientoDao.listar(idEvento, idPuesto);
        }
    }

    @GET
    @Path("/listarNombramiento")
    @Produces({"application/json", "application/xml"})
    public List<Nombramiento> listarNombramiento(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba, @QueryParam("idDivipol") int idDivipol) {
        NombramientoDao nombramientoDao = new NombramientoDao();

        return nombramientoDao.listarNombramiento(idPrueba, idDivipol);
    }

    @GET
    @Path("/buscar")
    @Produces({"application/json", "application/xml"})
    public Nombramiento findById(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int id) {
        NombramientoDao nombramientoDao = new NombramientoDao();
        return nombramientoDao.getById(id);
    }

    @GET
    @Path("/validarSalonesCompletosCoordinadores")
    @Produces({"application/json", "application/xml"})
    public String validarSalonesCompletos(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba, @QueryParam("codigoSitio") String codigoSitio) {

        NombramientoController nombramiento = new NombramientoController();
        String respuesta = nombramiento.validarSalonesAsignadosaCoordinadores(idPrueba, codigoSitio);

        return respuesta;
    }

    /**
     * Servicio encargado de obtener el nombramiento segun los filtros
     * ingresados en la vista, los parametros obligatorios son
     * idPrueba,codigoDepartamento,codigoCargo
     *
     * @param data
     * @return
     */
    @POST
    @Path("/getNombramientoCargoFiltros")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<NombramientoCargo> getNombramientoCargoFiltros(String data) {
        List<NombramientoCargo> listaNombramiento = new ArrayList<>();
        try {
            NombramientoController nombramiento = new NombramientoController();
            JSONObject json = new JSONObject(data);
            listaNombramiento = nombramiento.getNombramientoCargoFiltros(json.getInt("idPrueba"),
                    json.getInt("codigoDepartamento"), json.getInt("codigoCargo"),
                    json.optInt("codigoMunicipio", 0), json.optInt("idZona", 0),
                    json.optInt("cantidadSalones", 0));

        } catch (JSONException ex) {
            Logger.getLogger(NombramientoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaNombramiento;
    }

    /**
     * Servicio encargado de actualizar el nombramiento para asignar o
     * desasignar el empleado
     *
     * @param nombramiento
     * @return
     */
    @POST
    @Path("/actualizarNombramiento")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarNombramiento(NombramientoCargo nombramiento) {
        Response.ResponseBuilder response;
        Boolean actualizar = false;
        Respuesta respuesta = new Respuesta();
        try {
            if (nombramiento.getEmpleado().getNrodoc() > 0) {

                NombramientoController controller = new NombramientoController();
                Constants.Errors errores = controller.validarEmpleadoPrueba(nombramiento);
                if (errores == null) {
                    NombramientoDao nombramientoDao = new NombramientoDao();
                    actualizar = nombramientoDao.actualizar(nombramiento);
                    if (actualizar) {
                        DivitransBussines divitransNegocio = new DivitransBussines();
                        EmpleadoDao empleadoDao = new EmpleadoDao();
                        Empleado empleado = empleadoDao.GetByNumeroDocumento(nombramiento.getEmpleado().getNrodoc(), null);
                        boolean viaticoNombramiento = divitransNegocio.actualizarViaticoNombramiento(nombramiento, empleado, nombramiento.getIdPrueba());
                        Logger.getLogger(NombramientoRest.class.getName()).log(Level.INFO, "Actualizando viatico en nombramiento:{0}", viaticoNombramiento);
                    }
                } else {
                    switch (errores) {
                        case ERROR_USUARIO_NO_EXISTE:
                            respuesta.setCodigo(String.valueOf(Constants.Errors.ERROR_USUARIO_NO_EXISTE.getCode()));
                            respuesta.setDescripcion(Constants.Errors.ERROR_USUARIO_NO_EXISTE.getDescription());
                            break;
                        case ERROR_USUARIO_NODO:
                            respuesta.setCodigo(String.valueOf(Constants.Errors.ERROR_USUARIO_NODO.getCode()));
                            respuesta.setDescripcion(Constants.Errors.ERROR_USUARIO_NODO.getDescription());
                            break;
                        case ERROR_USUARIO_YA_ASIGNADO:
                            NombramientoDao nombramientoDao = new NombramientoDao();
                            Nombramiento nombramientoActual = nombramientoDao.getNombramiento(nombramiento.getIdPrueba(), nombramiento.getEmpleado().getNrodoc());
                            respuesta.setCodigo(String.valueOf(Constants.Errors.ERROR_USUARIO_YA_ASIGNADO.getCode()));
                            if (nombramientoActual.getPuesto() == null) {
                                respuesta.setDescripcion(Constants.Errors.ERROR_USUARIO_YA_ASIGNADO.getDescription());
                            } else {
                                respuesta.setDescripcion(Constants.Errors.ERROR_USUARIO_YA_ASIGNADO.getDescription().
                                        concat(" en el sitio: ").concat(nombramientoActual.getPuesto()).
                                        concat(" con cargo:").concat(nombramientoActual.getCargo().getDescripcion()));
                            }
                            break;
                        case ERROR_USUARIO_NO_PREINSCRITO:
                            respuesta.setCodigo(String.valueOf(Constants.Errors.ERROR_USUARIO_NO_PREINSCRITO.getCode()));
                            respuesta.setDescripcion(Constants.Errors.ERROR_USUARIO_NO_PREINSCRITO.getDescription());
                            break;
                    }
                }
            } else {
                //Se actualiza el nombramiento de la persona para desasignarla
                NombramientoDao nombramientoDao = new NombramientoDao();
                actualizar = nombramientoDao.actualizar(nombramiento);
                //Se actualiza el eps con el estado preinscrito
                EmpleadoPruebaEstadoDao empleadoPruebaEstadoDao = new EmpleadoPruebaEstadoDao();
                EmpleadoPruebaEstado empleadoEnPrueba = empleadoPruebaEstadoDao.getEmpleadoEnPrueba(nombramiento.getEmpleado().getIdEmpleado(),
                        nombramiento.getIdPrueba(), nombramiento.getEmpleado().getCargoobj().getCodigoCargo());
                empleadoEnPrueba.setIdestpersona(7);
                empleadoPruebaEstadoDao.actualizarEstado(empleadoEnPrueba);
            }

            if (actualizar) {
                respuesta.setCodigo(String.valueOf(Response.Status.OK.getStatusCode()));
                respuesta.setDescripcion("Exitoso");
                response = javax.ws.rs.core.Response.ok(respuesta);
            } else {
                response = javax.ws.rs.core.Response.accepted(respuesta);
            }

        } catch (Exception e) {
            Logger.getLogger(NombramientoRest.class.getName()).log(Level.SEVERE, null, e);
            response = javax.ws.rs.core.Response.serverError();
        }
        return response.build();
    }

    /**
     * Servicio encargado de obtener las personas que pueden ser asignadas al
     * sitio dependiendo del cargo
     *
     * @param filtros
     * @return
     */
    @GET
    @Path("/buscarPersonasNombramiento")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<EmpleadoPruebaEstadoCargo> buscarPersonasNombramiento(@QueryParam("params") String filtros) {
        try {
            JSONObject json = new JSONObject(filtros);
            NombramientoController controller = new NombramientoController();
            BusquedaPersonasDto dtoFiltros = new BusquedaPersonasDto();
            dtoFiltros.setCodDepartamento(json.optString("codigoDepartamento"));
            dtoFiltros.setCodCargo(json.optInt("nivelCargo"));
            dtoFiltros.setIdPrueba(json.optInt("idPrueba"));
            dtoFiltros.setCodDeptoDane(json.optInt("codDeptoDane"));
            dtoFiltros.setCodigoMunDane(json.optInt("codigoMunDane"));
            dtoFiltros.setCantidadSalones(json.optInt("cantidadSalones"));
            dtoFiltros.setDistancia(json.optDouble("distancia", 0));
            dtoFiltros.setViajar(json.optInt("viajar", -1));
            Sitio sitio = new Sitio();
            sitio.setId(json.optInt("idSitio"));
            dtoFiltros.setSitio(sitio);
            return controller.buscarPersonasNombramiento(dtoFiltros);
        } catch (Exception e) {
            Logger.getLogger(NombramientoRest.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    /**
     * Servicio encargado de obtener el nombramientoCargo por el id especifico
     *
     * @param id
     * @return
     */
    @GET
    @Path("/getNombramientoCargoById")
    @Produces()
    @Consumes()
    public NombramientoCargo getNombramientoCargoById(@QueryParam("id") int id) {
        NombramientoController controller = new NombramientoController();
        return controller.getNombramientoCargoById(id);
    }

    /**
     * Servicio encargado de cambiar nombramiento en la capacitacion
     *
     * @param request
     * @param idPrueba
     * @param documentoTitular
     * @param documentoSuplente
     * @param idEmpleado
     * @param idDivipol
     * @param codigoEvento
     * @param idDcpe
     * @return
     */
    @GET
    @Path("/cambiarNombramiento")
    @Produces()
    @Consumes()
    public String cambiarNombramiento(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba,
            @QueryParam("documentoTitular") long documentoTitular, @QueryParam("documentoSuplente") long documentoSuplente,
            @QueryParam("idEmpleado") int idEmpleado, @QueryParam("idSitio") int idDivipol,
            @QueryParam("codigoEvento") int codigoEvento,
            @QueryParam("idDcpe") int idDcpe) {
        NombramientoController controller = new NombramientoController();

        HttpSession sesion = request.getSession();
        EmpleadoSesion emp;
        emp = (EmpleadoSesion) sesion.getAttribute("empleado");

        return controller.cambiarNombramiento(documentoTitular, documentoSuplente, idPrueba, idEmpleado, idDivipol, codigoEvento, idDcpe, emp.getNrodoc());
    }

    /**
     * Servicio encargado de asignar a la persona dentro del evento en la tabla
     * detalle_cargo_x_puesto_x_evento
     *
     * @param request
     * @param data
     * @return
     */
    @POST
    @Path("/asignarPersonaEvento")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response asignarPersonaEvento(@javax.ws.rs.core.Context HttpServletRequest request, String data) {
        Response.ResponseBuilder response;
        Respuesta respuesta = new Respuesta();
        try {
            NombramientoController controller = new NombramientoController();
            JSONObject json = new JSONObject(data);
            long documento = json.getLong("nroDocumento");
            long idDcpe = json.getLong("idDcpe");
            int idPrueba = json.getInt("idPrueba");
            Constants.Errors errores = controller.asignacionPersonaEvento(documento, idDcpe, idPrueba);
            if (errores != null) {
                switch (errores) {
                    case ERROR_EVENTO_YA_ASIGNADO:
                        respuesta.setCodigo(String.valueOf(Constants.Errors.ERROR_EVENTO_YA_ASIGNADO.getCode()));
                        respuesta.setDescripcion(Constants.Errors.ERROR_EVENTO_YA_ASIGNADO.getDescription().concat(String.valueOf(idDcpe)));
                        break;
                    case ERROR_USUARIO_NO_PREINSCRITO:
                        respuesta.setCodigo(String.valueOf(Constants.Errors.ERROR_USUARIO_NO_PREINSCRITO.getCode()));
                        respuesta.setDescripcion(Constants.Errors.ERROR_USUARIO_NO_PREINSCRITO.getDescription());
                        break;
                    case ERROR_USUARIO_YA_ASIGNADO_EVENTO:
                        respuesta.setCodigo(String.valueOf(Constants.Errors.ERROR_USUARIO_YA_ASIGNADO_EVENTO.getCode()));
                        respuesta.setDescripcion(Constants.Errors.ERROR_USUARIO_YA_ASIGNADO_EVENTO.getDescription());
                        break;
                    case ERROR_NIVEL_CARGO_INVALIDO:
                        respuesta.setCodigo(String.valueOf(Constants.Errors.ERROR_NIVEL_CARGO_INVALIDO.getCode()));
                        respuesta.setDescripcion(Constants.Errors.ERROR_NIVEL_CARGO_INVALIDO.getDescription());
                        break;
                    case ERROR_USUARIO_NO_EXISTE:
                        respuesta.setCodigo(String.valueOf(Constants.Errors.ERROR_USUARIO_NO_EXISTE.getCode()));
                        respuesta.setDescripcion(Constants.Errors.ERROR_USUARIO_NO_EXISTE.getDescription());
                        break;
                }
                response = javax.ws.rs.core.Response.accepted(respuesta);
            } else {
                respuesta.setCodigo(String.valueOf(Response.Status.OK.getStatusCode()));
                respuesta.setDescripcion("Exitoso");
                response = javax.ws.rs.core.Response.ok(respuesta);
            }

        } catch (Exception e) {
            Logger.getLogger(NombramientoRest.class.getName()).log(Level.SEVERE, null, e);
            response = javax.ws.rs.core.Response.serverError();
        }
        return response.build();
    }

    /**
     * Servicio encargado de generar el reporte de Boletos de Suplencia
     *
     * @param request
     * @param response
     * @param data Ejemplo - {codigoEvento:123,idPrueba:2}
     * @return
     */
    @POST
    @Path("/generarBoletosSuplencia")
    @Produces("application/pdf")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generarBoletosSuplencia(@javax.ws.rs.core.Context HttpServletRequest request,
            @javax.ws.rs.core.Context HttpServletResponse response, String data) {
        Response.ResponseBuilder responseBuilder;
        try {
            JSONObject json = new JSONObject(data);
            NombramientoController controller = new NombramientoController();
            File file = controller.generarBoletosSuplencia(json.optInt("idPrueba", 0),
                    json.getString("codigoEvento"));
            responseBuilder = Response.ok((Object) file);
            responseBuilder.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=boletosSuplencia.pdf");
            responseBuilder.header(HttpHeaders.CONTENT_TYPE, "application/pdf;charset=utf-8");
            Response responseData = responseBuilder.build();
            return responseData;
        } catch (JSONException ex) {
            Logger.getLogger(DivitransRest.class.getName()).log(Level.SEVERE, null, ex);
            responseBuilder = javax.ws.rs.core.Response.status(500);
        }
        return responseBuilder.build();
    }

}
