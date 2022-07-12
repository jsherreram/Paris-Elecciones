/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.EmpleadoDao;
import co.com.grupoasd.nomina.dao.EmpleadoPruebaEstadoDao;
import co.com.grupoasd.nomina.modelo.Defuncion;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EmpleadoPruebaEstado;
import co.com.grupoasd.nomina.modelo.EmpleadoSesion;
import co.com.grupoasd.nomina.modelo.Municipio;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.Respuesta;
import co.com.grupoasd.nomina.negocio.empleado.IEmpleadoImpl;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Pedro Rodriguez
 */
@Path("/empleado")
public class EmpleadoRest {

    @GET
    @Path("/listar")
    @Produces({"application/xml", "application/json"})
    public List<Empleado> listar(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idDpto") String idDepartamento) {
        EmpleadoDao empleadoDao = new EmpleadoDao();
        return empleadoDao.listar(idDepartamento);
    }

    @GET
    @Path("/listarPorEstado")
    @Produces({"application/xml", "application/json"})
    public List<Empleado> listar(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idDpto") String idDepartamento, @QueryParam("idEstado") int idEstado) {
        EmpleadoDao empleadoDao = new EmpleadoDao();
        return empleadoDao.listar(idDepartamento, idEstado);
    }

    @GET
    @Path("/listarPorEstadoJson")
    @Produces({"application/xml", "application/json"})
    public String listarJson(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idDpto") String idDepartamento, @QueryParam("idEstado") int idEstado) {
        EmpleadoDao empleadoDao = new EmpleadoDao();
        return empleadoDao.listarJson(idDepartamento, idEstado);
    }

    @GET
    @Path("/buscarJson")
    @Produces({"application/xml", "application/json"})
    public String buscarJson(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idDpto") String idDepartamento, @QueryParam("nrodoc") int nrodoc,
            @QueryParam("apellido1") String apellido1, @QueryParam("apellido2") String apellido2,
            @QueryParam("nombre1") String nombre1, @QueryParam("nombre2") String nombre2,
            @QueryParam("idpuesto") String idpuesto) {
        EmpleadoDao empleadoDao = new EmpleadoDao();
        return empleadoDao.listarJson(idDepartamento, nrodoc, apellido1, apellido2, nombre1, nombre2, idpuesto);
    }

    @GET
    @Path("/buscarEmpleadosSitio")
    @Produces({"application/xml", "application/json"})
    public String buscarEmpleadosSitio(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idpuesto") String idpuesto, @QueryParam("idPrueba") int idprueba) {
        EmpleadoDao empleadoDao = new EmpleadoDao();
        return empleadoDao.listarJsonPorSitio(idpuesto, idprueba);
    }

    @GET
    @Path("/buscar")
    @Produces({"application/xml", "application/json"})
    public Empleado find(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("id") int id) {
        Empleado emp = new Empleado();
        EmpleadoDao empleadoDao = new EmpleadoDao();
        emp = empleadoDao.GetById(id);
        return emp;
    }

    @GET
    @Path("/buscarSinHuella")
    @Produces({"application/xml", "application/json"})
    public Empleado findSinHuella(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("id") int id) {
        Empleado emp = new Empleado();
        EmpleadoDao empleadoDao = new EmpleadoDao();
        emp = empleadoDao.GetByIdSinImagenHuella(id);
        return emp;
    }

    @GET
    @Path("/buscarPorSesion")
    @Produces({"application/xml", "application/json"})
    public Empleado findBySession(@javax.ws.rs.core.Context HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        EmpleadoSesion emp = new EmpleadoSesion();
        Empleado empleado = new Empleado();
        emp = (EmpleadoSesion) sesion.getAttribute("empleado");
        EmpleadoDao empleadoDao = new EmpleadoDao();
        empleado = empleadoDao.GetById(emp.getIdEmpleado());
        return empleado;
    }

    @GET
    @Path("/empleadoSesion")
    @Produces({"application/json"})
    public EmpleadoSesion getEmpleadoSesion(@javax.ws.rs.core.Context HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        EmpleadoSesion emp = new EmpleadoSesion();
        emp = (EmpleadoSesion) sesion.getAttribute("empleado");
        return emp;

    }

    @GET
    @Path("/buscarDocumento2")
    @Produces({"application/xml", "application/json"})
    public Empleado findByDocumento2(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("nroDocumento") int nroDoc, @QueryParam("tipoDocumento") String tipoDocumento) {
        EmpleadoDao empleadoDao = new EmpleadoDao();
        return empleadoDao.GetByNumeroDocumento2(nroDoc, tipoDocumento);
    }

    @GET
    @Path("/buscarDocumento")
    @Produces({"application/xml", "application/json"})
    public Empleado findByDocumento(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("nroDocumento") int nroDoc, @QueryParam("codigoDepartamento") String codigoDepartamento) {
        EmpleadoDao empleadoDao = new EmpleadoDao();
        return empleadoDao.GetByNumeroDocumento(nroDoc, codigoDepartamento);
    }

    @GET
    @Path("/buscarNroDoc")
    @Produces({"application/xml", "application/json"})
    public Empleado findByNroDoc(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("nroDocumento") long nroDoc) {
        EmpleadoDao empleadoDao = new EmpleadoDao();
        int idEmpleado = empleadoDao.GetIdByNumeroDocumento(nroDoc);
        Empleado empleado = empleadoDao.GetByIdSinImagenHuella(idEmpleado);
        if (empleado.getHuella() != null) {
            empleado.setHuellaBase64(Base64.getEncoder().encodeToString(empleado.getHuella()));
        }
        return empleado;
    }

    @GET
    @Path("/eventos")
    @Produces({"application/xml", "application/json"})
    public List<EmpleadoPruebaEstado> getEventos(
            @javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int id
    ) {
        EmpleadoPruebaEstadoDao empleadoPruebaEstadoDao = new EmpleadoPruebaEstadoDao();
        return empleadoPruebaEstadoDao.listarPruebas(id);
    }

    @POST
    @Consumes({"application/json"})
    public Response crear(Empleado e) {
        ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        IEmpleadoImpl iEmpleadoImpl = new IEmpleadoImpl();
        int idEmpleado = iEmpleadoImpl.Insertar(e);
        if (idEmpleado > 0) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Creado");
            respuesta.setId(idEmpleado);
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Empleado ya existe.");
            respuesta.setId(idEmpleado);
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    @POST
    @Path("/insertarDatosPrincipalesRDS")
    @Consumes({"application/json"})
    public Response insertarDatosPrincipalesRDS(Empleado e) {
        ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        Respuesta respuesta = new Respuesta();
        IEmpleadoImpl iEmpleadoImpl = new IEmpleadoImpl();

        int idEmpleado = iEmpleadoImpl.InsertarDatosPrincipalesRDS(e);
        if (idEmpleado > 0) {
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Creado");
        } else {
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Empleado no se pudo crear.");
        }
        respuesta.setId(idEmpleado);
        rb.status(200);
        rb.entity(respuesta);
        return rb.build();
    }

    @POST
    @Path("/insertarDatosBasicos")
    @Consumes({"application/json"})
    public Response insertarDatosBasicos(Empleado e) {
        ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        Respuesta respuesta = new Respuesta();

        IEmpleadoImpl iEmpleadoImpl = new IEmpleadoImpl();

        int idEmpleado = iEmpleadoImpl.InsertarDatosBasicos(e);
        if (idEmpleado > 0) {
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Creado");
        } else {
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Empleado no se pudo crear.");
        }
        respuesta.setId(idEmpleado);
        rb.status(200);
        rb.entity(respuesta);
        return rb.build();
    }

    @POST
    @Path("/enrolar")
    @Consumes({"application/json"})
    public Response enrolar(Empleado e) {
        ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        IEmpleadoImpl iEmpleadoImpl = new IEmpleadoImpl();

        byte[] huella = Base64.getDecoder().decode(e.getHuellaBase64());
        byte[] imagenHuella = Base64.getDecoder().decode(e.getImagenHuellaBase64());

        boolean resul = iEmpleadoImpl.anadirHuella(e.getIdEmpleado(), huella, imagenHuella);
        if (resul == true) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Actualizado");
            respuesta.setId(e.getIdEmpleado());
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("No se pudo actualizar la huella.");
            respuesta.setId(e.getIdEmpleado());
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    @PUT
    @Consumes({"application/json", "application/xml"})
    public Response modificar(Empleado e) {
        ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        IEmpleadoImpl iEmpleadoImpl = new IEmpleadoImpl();
        if (iEmpleadoImpl.Actualizar(e)) {
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
    @Path("/actualizarDatosPrincipalesRDS")
    @Consumes({"application/json"})
    public Response actualizarDatosPrincipalesRDS(Empleado e) {
        ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        Respuesta respuesta = new Respuesta();
        IEmpleadoImpl iEmpleadoImpl = new IEmpleadoImpl();

        if (e.getHuellaBase64().length() > 0) {
            byte[] huella = Base64.getDecoder().decode(e.getHuellaBase64());
            byte[] imagenHuella = Base64.getDecoder().decode(e.getImagenHuellaBase64());
            e.setHuella(huella);
            e.setImagenHuella(imagenHuella);
        }

        if (iEmpleadoImpl.actualizarDatosPrincipalesRDS(e)) {
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Actualizado");
        } else {
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Empleado no se pudo actualizar");
        }
        rb.status(200);
        rb.entity(respuesta);
        return rb.build();
    }

    @POST
    @Path("/actualizarDatosPrincipalesPistoleo")
    @Consumes({"application/json"})
    public Response actualizarDatosPrincipalesPistoleo(Empleado e) {

        ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        Respuesta respuesta = new Respuesta();
        IEmpleadoImpl iEmpleadoImpl = new IEmpleadoImpl();

        if (iEmpleadoImpl.actualizarDatosPrincipalesPistoleo(e)) {
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Pistoleo Actualizado");
        } else {
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Empleado no se pudo actualizar");
        }
        rb.status(200);
        rb.entity(respuesta);
        return rb.build();
    }

    @POST
    @Path("/actualizarDatosBasicos")
    @Consumes({"application/json"})
    public Response actualizarDatosBasicos(Empleado e) {
        ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        Respuesta respuesta = new Respuesta();
        EmpleadoDao dao = new EmpleadoDao();

        if (dao.actualizarDatosBasicos(e)) {
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Actualizado");
        } else {
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Empleado no se pudo actualizar");
        }
        rb.status(200);
        rb.entity(respuesta);
        return rb.build();
    }

    @GET
    @Path("/buscarCargoEmpleadoSesion")
    @Produces({"application/xml", "application/json"})
    public Empleado buscarCargoEmpleadoSesion(@javax.ws.rs.core.Context HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        EmpleadoSesion emp = new EmpleadoSesion();
        emp = (EmpleadoSesion) sesion.getAttribute("empleado");
        EmpleadoDao empleadoDao = new EmpleadoDao();
        return empleadoDao.obtenerCargoEmpleadoSesion(emp.getIdEmpleado());
    }

    @GET
    @Path("/verHistorial")
    @Produces({"application/xml", "application/json"})
    public String verHistorial(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("tipoDoc") String tipoDoc, @QueryParam("idEmpleado") int idEmpleado) {

        EmpleadoDao empleadoDao = new EmpleadoDao();
        return empleadoDao.listarHistorialArchivosEmpleado(idEmpleado, tipoDoc);
    }

    @GET
    @Path("/filtrarPorGeolocalizacion")
    @Produces({"application/xml", "application/json"})
    public List<Empleado> filtrarPorGeolocalizacion(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int tipoFiltro, @QueryParam("departamento") int departamento, @QueryParam("cargo") int cargo, @QueryParam("prueba") int prueba) throws Exception {
        EmpleadoDao empleadoDao = new EmpleadoDao();
        return empleadoDao.filtrarPorGeolocalizacion(tipoFiltro, departamento, cargo, prueba);
    }

    @GET
    @Path("/obtenerTotalGeolocalizados")
    @Produces({"application/xml", "application/json"})
    public Response obtenerTotalGeolocalizados(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int tipoFiltro, @QueryParam("departamento") int departamento, @QueryParam("cargo") int cargo, @QueryParam("prueba") int prueba) throws Exception {

        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        EmpleadoDao empleadoDao = new EmpleadoDao();
        int cantidad = empleadoDao.getTotalGeolocalizados(tipoFiltro, departamento, cargo, prueba);

        Respuesta respuesta = new Respuesta();
        respuesta.setCodigo("200");
        respuesta.setDescripcion("Registro Creado");
        respuesta.setId(cantidad);
        rb.status(200);
        rb.entity(respuesta);
        return rb.build();
    }

    @PUT
    @Path("/actualizarCoordenadas")
    @Consumes({"application/json"})
    public Response actualizarCoordenadas(Empleado e) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        EmpleadoDao empleado = new EmpleadoDao();
        boolean guarda = empleado.actualizarCoordenadas(e);
        if (guarda) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Actualizado");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Error al guardar.");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    @GET
    @Path("/asignarMasivo")
    @Produces({"application/xml", "application/json"})
    public Response asignarMasivo(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("cargo") int cargo, @QueryParam("prueba") int prueba, @QueryParam("municipio") int municipio) throws Exception {

        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        EmpleadoDao empleadoDao = new EmpleadoDao();

        if (empleadoDao.asignarMasivo(0.5F, cargo, prueba, municipio)) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Empleados Asignados correctamente");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Es posible que se hallan presentados errores durante la asignaci&oacute;n");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    @GET
    @Path("/buscarAsignados")
    @Produces({"application/xml", "application/json"})
    public List<Empleado> buscarAsignados(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idSitio") int idSistio, @QueryParam("nivelCargo") int nivelCargo) {
        EmpleadoDao empleadoDao = new EmpleadoDao();
        return empleadoDao.buscarAsignados(idSistio, nivelCargo);
    }

    @GET
    @Path("/busquedaAvanzada")
    @Produces({"application/xml", "application/json"})
    public List<Empleado> busquedaAvanzada(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("departamento") String departamento, @QueryParam("municipio") String municipio,
            @QueryParam("nivel") int nivel, @QueryParam("cargo") int cargo, @QueryParam("bilingue") int bilingue,
            @QueryParam("experienciaHuellas") int dactiloscopista, @QueryParam("interpretaSenas") int interpretaSenas,
            @QueryParam("manejoDiscapacidad") int manejoDiscapacidad, @QueryParam("notaEvaluacionDesde") String notaEvaluacionDesde,
            @QueryParam("genero") String genero, @QueryParam("tipoPrueba") int tipoPrueba, @QueryParam("nodo") String nodo,
            @QueryParam("fechaInicial") String fechaInicial, @QueryParam("fechaFinal") String fechaFinal, @QueryParam("notaEvaluacionHasta") String notaEvaluacionHasta) throws ParseException {

        Empleado e = new Empleado();
        e.setNivelacademico(nivel);
        e.setCargo(cargo);
        e.setBilingue(bilingue);
        e.setIntepretacionsenas(interpretaSenas);
        e.setExperienciahuellas(dactiloscopista);
        e.setManejodiscapacidad(manejoDiscapacidad);
        e.setGenero(genero);
        EmpleadoDao empleadoDao = new EmpleadoDao();
        return empleadoDao.buscarEmpleadosPorFiltroAvanzado(departamento, nodo, municipio, e, tipoPrueba, fechaInicial, fechaFinal);

    }

    @GET
    @Path("/buscarEmpleadosPorDepartamentoMunicipio")
    @Produces({"application/xml", "application/json"})
    public List<Empleado> buscarEmpleadosPorDepartamentoMunicipio(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("dpto") String dpto, @QueryParam("municipio") String municipio) {

        EmpleadoDao empleadoDao = new EmpleadoDao();
        return empleadoDao.buscarEmpleadosPorDepartamentoMunicipio(dpto, municipio);
    }

    @GET
    @Path("/busquedaBasica")
    @Produces({"application/xml", "application/json"})
    public List<Empleado> busquedaBasica(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("nrodoc") String nrodoc, @QueryParam("nombre1") String nombre1,
            @QueryParam("nombre2") String nombre2, @QueryParam("apellido1") String apellido1,
            @QueryParam("apellido2") String apellido2, @QueryParam("dpto") String dpto) {

        EmpleadoDao empleadoDao = new EmpleadoDao();
        return empleadoDao.buscarEmpleadosPorFiltroBasico(nrodoc, nombre1, nombre2, apellido1, apellido2, dpto);

    }

    @GET
    @Path("/busquedaBasica_1")
    @Produces({"application/xml", "application/json"})
    public List<Defuncion> busquedaBasica_1(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("nrodoc") String nrodoc, @QueryParam("nombre1") String nombre1,
            @QueryParam("nombre2") String nombre2, @QueryParam("apellido1") String apellido1,
            @QueryParam("apellido2") String apellido2, @QueryParam("dpto") String dpto) {

        EmpleadoDao empleadoDao = new EmpleadoDao();
        return empleadoDao.buscarEmpleadosPorFiltroBasico_1(nrodoc, nombre1, nombre2, apellido1, apellido2, dpto);

    }

        
        
    @GET
    @Path("/consultaDocumentosEmpleados")
    @Produces({"application/xml", "application/json"})
    public String consultarDocumentosEmpleados(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEmpleado") int idEmpleado) {

        EmpleadoDao empleadoDao = new EmpleadoDao();
        return empleadoDao.consultarDocumentosEmpleados(idEmpleado).toString();

    }

    @GET
    @Path("/listarParaValidar")
    @Produces({"application/xml", "application/json"})
    public String listarParaValidar(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idDpto") String idDepartamento, @QueryParam("idEstado") int idEstado,
            @QueryParam("idMunicipio") String idMunicipio, @QueryParam("nrodoc") String nrodoc,
            @QueryParam("idPrueba") int idPrueba) {
        EmpleadoDao empleadoDao = new EmpleadoDao();
        return empleadoDao.listarPersonasPorEstado(idDepartamento, idMunicipio, idEstado, nrodoc, idPrueba);
    }

    @POST
    @Path("/actualizarEstadoEmpleado")
    @Consumes({"application/json"})
    public Response actualizarEstadoEmpleado(String data) throws JSONException {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
  
        JSONObject json = new JSONObject(data);
        String observaciones = json.getString("observaciones");
        Integer idEmpleado = json.getInt("idEmpleado");
        Integer idEstado = json.getInt("idEstado");
        EmpleadoDao empleado = new EmpleadoDao();
        boolean guarda = empleado.actualizarEstadoEmpleado(idEstado, idEmpleado, observaciones);
        if (guarda) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Estado Actualizado");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Error al guardar.");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    /**
     * Author: John Steak Herrera Moreno
     * Date: 29/06/2022
     * 
     * @param empleado
     * @return
     * @throws Exception 
     */
    @POST
    @Path("/actualizarContrasena")
    @Consumes({"application/json", "application/xml"})
    public Response actualizarContrasena(Empleado empleado) throws Exception {
        
        ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        IEmpleadoImpl iEmpleadoImpl = new IEmpleadoImpl();
        if (iEmpleadoImpl.actualizarContrasena(empleado)) {
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
    
}
