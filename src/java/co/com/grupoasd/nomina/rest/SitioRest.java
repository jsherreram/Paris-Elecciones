/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.common.model.Foto;
import co.com.grupoasd.nomina.dao.CategoriaSitioDao;
import co.com.grupoasd.nomina.dao.EstadoSitioDao;
import co.com.grupoasd.nomina.dao.SitioDao;
import co.com.grupoasd.nomina.dao.SitioEventoValidadoDao;
import co.com.grupoasd.nomina.dao.TipoSitioDao;
import co.com.grupoasd.nomina.modelo.CategoriaSitio;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EstadoSitio;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.Respuesta;
import co.com.grupoasd.nomina.modelo.Sitio;
import co.com.grupoasd.nomina.modelo.TipoSitio;
import co.com.grupoasd.nomina.negocio.empleado.IEmpleadoImpl;
import co.com.grupoasd.nomina.negocio.sitio.SitioBusiness;
import java.util.Date;
import java.util.List;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Pedro Rodriguez
 */
@Path("/sitio")
public class SitioRest {

    @GET
    @Path("/buscar")
    @Produces({"application/xml", "application/json"})
    public Sitio find(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") String id) {

        SitioDao sitioDao = new SitioDao();
        return sitioDao.GetById(id);
    }

    @GET
    @Path("/getById")
    @Produces({"application/xml", "application/json"})
    public Sitio getById(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int id) {
        SitioDao sitioDao = new SitioDao();
        return sitioDao.GetById(id);
    }

    /**
     * Author: John Steak Herrera Moreno 
     * Date: 02/06/2022
     *
     * @param request
     * @param id
     * @return
     */
    @GET
    @Path("/findById")
    @Produces({"application/json", "application/xml"})
    public Sitio findById(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int id) {
        SitioDao sitioDao = new SitioDao();
        return sitioDao.findById(id);
    }

    @GET
    @Path("/buscarPorCodigoPrueba")
    @Produces({"application/xml", "application/json"})
    public Sitio buscarSitioPorCodigoPrueba(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") String id, @QueryParam("idPrueba") int idPrueba) throws Exception {
        SitioDao sitioDao = new SitioDao();
        Prueba prueba = new Prueba();
        prueba.setIdprueba(idPrueba);
        return sitioDao.GetSitio(id, prueba);
    }

    @GET
    @Path("/obtenerSitioPorId")
    @Produces({"application/xml", "application/json"})
    public Sitio BuscarSitioPorId(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int id) {
        SitioDao sitioDao = new SitioDao();
        return sitioDao.BuscarSitioPorId(id);
    }

    @GET
    @Path("/listar")
    @Produces({"application/xml", "application/json"})
    public List<Sitio> listar(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba) {
        SitioDao sitioDao = new SitioDao();
        return sitioDao.listarSitiosPrueba(idPrueba);
    }

    @GET
    @Path("/consultarPorFiltros")
    @Produces({"application/xml", "application/json"})
    public List<Sitio> consultarPorFiltros(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("codigoSitio") String codigoSitio, @QueryParam("tipoSitio") int tipo,
            @QueryParam("departamento") String codigoDepartamento, @QueryParam("municipio") String codigoMunicipio,
            @QueryParam("idPrueba") int idPrueba) {
        SitioDao sitioDao = new SitioDao();
        return sitioDao.consultarSitios(idPrueba, codigoSitio, tipo, codigoDepartamento, codigoMunicipio);
    }

    @GET
    @Path("/listarTiposSitio")
    @Produces({"application/xml", "application/json"})
    public List<TipoSitio> listarTiposSitio(@javax.ws.rs.core.Context HttpServletRequest request) {
        TipoSitioDao tsitioDao = new TipoSitioDao();
        return tsitioDao.listar();
    }

    @GET
    @Path("/listarCategoriasSitio")
    @Produces({"application/xml", "application/json"})
    public List<CategoriaSitio> listarCategoriasSitio(@javax.ws.rs.core.Context HttpServletRequest request) {
        CategoriaSitioDao csitioDao = new CategoriaSitioDao();
        return csitioDao.listar();
    }

    @GET
    @Path("/listarEstadosSitio")
    @Produces({"application/xml", "application/json"})
    public List<EstadoSitio> listarEstadosSitio(@javax.ws.rs.core.Context HttpServletRequest request) {
        EstadoSitioDao dao = new EstadoSitioDao();
        return dao.listar();
    }

    @POST
    @Consumes({"application/json"})
    public Response crear(Sitio s) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        SitioDao sitio = new SitioDao();

        boolean guarda = sitio.insertarSitio(s);
        if (guarda) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Creado");
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

    @PUT
    @Consumes({"application/json"})
    public Response actualizar(Sitio s) {

        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        SitioDao sitio = new SitioDao();
        boolean guarda = sitio.actualizarSitio(s);
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
    @Path("/listarPDSSinAsignar")
    @Produces({"application/xml", "application/json"})
    public List<Sitio> listarPDsSinAsignar(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int idDivipol, @QueryParam("idPrueba") int idPrueba) {

        SitioDao sitioDao = new SitioDao();
        return sitioDao.listarPDdsSinAsignarASitio(idDivipol, idPrueba);
    }

    @GET
    @Path("/listarPDSAsignados")
    @Produces({"application/xml", "application/json"})
    public List<Sitio> listarPDsAsignados(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int idDivipol) {
        SitioDao sitioDao = new SitioDao();
        return sitioDao.listarPDsAsignadosASitio(idDivipol);
    }

    @GET
    @Path("/historialContactosSitio")
    @Produces({"application/xml", "application/json"})
    public List<Sitio> listarHistorialContactosSitio(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("codigoSitio") String codigoSitio) {
        SitioDao sitioDao = new SitioDao();
        return sitioDao.listarHistorialContactosSitio(codigoSitio);
    }

    @GET
    @Path("/filtrarPorGeolocalizacion")
    @Produces({"application/xml", "application/json"})
    public List<Sitio> filtrarPorGeolocalizacion(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int tipoFiltro, @QueryParam("prueba") int prueba, @QueryParam("municipio") int municipio) throws Exception {
        SitioDao sitioDao = new SitioDao();
        return sitioDao.filtrarPorGeolocalizacion(tipoFiltro, prueba, municipio);
    }

    @GET
    @Path("/obtenerTotalSitios")
    @Produces({"application/xml", "application/json"})
    public Response obtenerTotalSitios(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("id") int tipoFiltro, @QueryParam("prueba") int prueba, @QueryParam("municipio") int municipio) throws Exception {

        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        SitioDao sitioDao = new SitioDao();
        int cantidad = sitioDao.getTotalSitios(tipoFiltro, municipio, prueba);

        Respuesta respuesta = new Respuesta();
        respuesta.setCodigo("200");
        respuesta.setDescripcion("Registro Creado");
        respuesta.setId(cantidad);
        rb.status(200);
        rb.entity(respuesta);
        return rb.build();
    }

    @GET
    @Path("/ListarSitioUsuario")
    @Produces({"application/xml", "application/json"})
    public String ListarSitioUsuario(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba, @QueryParam("usuario") String usuario) {
        SitioDao sitio = new SitioDao();
        return sitio.ListarSitioUsuario(idPrueba, usuario);
    }

    @PUT
    @Path("/actualizarCoordenadas")
    @Consumes({"application/json"})
    public Response actualizarCoordenadas(Sitio s) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        SitioDao sitio = new SitioDao();
        boolean guarda = sitio.actualizarCoordenadas(s);
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

    /**
     * Servicio encargado de validar y devolver la lista de archvos de fotos en
     * el servidor para ese sitio segun la prueba recibida
     *
     * @param data
     * @return
     */
    @POST
    @Path("/getFotosSitio")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Foto> getFotosSitio(String data) {
        List<Foto> listaFotosSitio = null;
        try {
            SitioBusiness sitioNegocio = new SitioBusiness();
            JSONObject json = new JSONObject(data);
            String idSitio = json.getString("idSitio");
            Integer idPrueba = json.getInt("idPrueba");
            String folderFotos = json.getString("folder");
            Integer idEvento = json.getInt("idEvento");
            listaFotosSitio = sitioNegocio.obtenerFotosSitioPorPrueba(idSitio, idPrueba, folderFotos, idEvento);
        } catch (JSONException ex) {
            Logger.getLogger(SitioRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaFotosSitio;
    }

    /**
     * Servicio encargado de eliminar una foto del sitio seleccionada del
     * servidor
     *
     * @param data
     * @return
     */
    @POST
    @Path("/eliminarFotoSitio")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarFotoSitio(String data) {
        Response.ResponseBuilder response;
        try {
            JSONObject json = new JSONObject(data);
            String idSitio = json.getString("idSitio");
            Integer idPrueba = json.getInt("idPrueba");
            String nombreFoto = json.getString("nombreFoto");
            Integer idEvento = json.getInt("idEvento");
            String folderFoto = json.getString("folder");
            SitioBusiness sitioNegocio = new SitioBusiness();
            boolean success = sitioNegocio.eliminarFotoSitio(idPrueba, idSitio, nombreFoto, folderFoto, idEvento);
            if (success) {
                response = javax.ws.rs.core.Response.status(Response.Status.OK);
            } else {
                response = javax.ws.rs.core.Response.status(Response.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (JSONException ex) {
            Logger.getLogger(SitioRest.class.getName()).log(Level.SEVERE, null, ex);
            response = javax.ws.rs.core.Response.status(Response.Status.INTERNAL_SERVER_ERROR);
        }
        return response.build();
    }

    /**
     * Servicio encargado de obtener los sitios categoria
     *
     * @param request
     * @param idPrueba
     * @return
     */
    @GET
    @Path("/getPDSByPrueba")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sitio> getPDSByPrueba(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba) {
        SitioBusiness sitioBusiness = new SitioBusiness();
        return sitioBusiness.getPDSByPrueba(idPrueba);
    }

    /**
     * Servicio encargado de obtener los sitios categoria
     *
     * @param dpto
     * @param idPrueba
     * @param idEvento
     * @return
     */
    @GET
    @Path("/buscarSitioPorDepartamento")
    @Produces(MediaType.APPLICATION_JSON)
    public String buscarSitiosDepartamentoPrueba(
            @QueryParam("idPrueba") int idPrueba, @QueryParam("dpto") String dpto, @QueryParam("idEvento") int idEvento) {
        SitioDao sitioDao = new SitioDao();
        return sitioDao.buscarSitiosDepartamentoPrueba(idPrueba, dpto, idEvento);
    }

    @GET
    @Path("/guardaSitioEventoValidado")
    @Produces({"application/json"})
    public Response guardaValidacionSitioEvento(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idEvento") int idEvento, @QueryParam("idDivipol") int idDivipol) {

        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);

        SitioEventoValidadoDao sitiodao = new SitioEventoValidadoDao();

        String usuario = request.getUserPrincipal().getName();
        boolean insert = sitiodao.insertarSitioEventoValidado(idDivipol, idEvento, usuario);

        if (insert) {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Sitio con asistencia validada");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("No se guard?? la informaci??n");
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }

    }

    @GET
    @Path("/buscarSitioEventoValidado")
    @Produces(MediaType.APPLICATION_JSON)
    public String buscarSitioEventoValidado(
            @QueryParam("idEvento") int idEvento, @QueryParam("idDivipol") int idDivipol) {
        SitioEventoValidadoDao sitiodao = new SitioEventoValidadoDao();
        return sitiodao.buscarSitioEventoValidado(idDivipol, idEvento);
    }

}
