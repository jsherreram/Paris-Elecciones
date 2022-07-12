/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.modelo.Divitrans;
import co.com.grupoasd.nomina.modelo.EstadoDivitrans;
import co.com.grupoasd.nomina.modelo.Respuesta;
import co.com.grupoasd.nomina.modelo.wrapper.DivitransConsultaAdmin;
import co.com.grupoasd.nomina.negocio.Divitrans.DivitransBussines;
import co.com.grupoasd.nomina.negocio.Divitrans.IDivitrans;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
 * @author Wilfer
 */
@Path("/Divitrans")
public class DivitransRest {

    /**
     *
     * @param request
     * @param idPrueba
     * @return
     */
    @GET
    @Path("/listarDivitrans")
    @Produces({"application/json"})
    public List<Divitrans> listar(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idPrueba") int idPrueba) {
        IDivitrans DiviBussines = new DivitransBussines();
        return DiviBussines.listarDivitrans(idPrueba);
    }

    /**
     *
     * @param request
     * @param idPruebaOrigen
     * @param idPrueba
     * @return
     */
    @GET
    @Path("/DuplicarDivitrans")
    @Produces({"application/json"})
    public String listar(@javax.ws.rs.core.Context HttpServletRequest request, @QueryParam("idPruebaOrigen") int idPruebaOrigen, @QueryParam("idPrueba") int idPrueba) {
        IDivitrans DiviBussines = new DivitransBussines();
        return DiviBussines.DuplicarDivitrans(idPruebaOrigen, idPrueba);
    }

    /**
     * Servicio que consulta los viaticos de acuerdo a los filtros ingresados
     *
     * @param request
     * @param data
     * @return
     */
    @POST
    @Path("/consultarPorFiltros")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<DivitransConsultaAdmin> consultarPorFiltros(@javax.ws.rs.core.Context HttpServletRequest request,
            String data) {

        try {
            JSONObject json = new JSONObject(data);
            IDivitrans divitransBussines = new DivitransBussines();
            return divitransBussines.consultarPorFiltros(json.optInt("departamento", 0),
                    json.optInt("municipio", 0), json.optDouble("nroDocumento", 0),
                    json.optLong("codigoSitio", 0), json.optInt("idPrueba", 0),
                    json.optInt("idEstado", 0), json.optInt("codigoCargo", 0));
        } catch (JSONException ex) {
            Logger.getLogger(DivitransRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Servicio encargado de obtener los valores del viatico especifico
     *
     * @param request
     * @param data
     * @return
     */
    @POST
    @Path("/getValoresViaticoById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DivitransConsultaAdmin getValoresViaticoById(@javax.ws.rs.core.Context HttpServletRequest request,
            String data) {
        try {
            JSONObject json = new JSONObject(data);
            IDivitrans divitransBussines = new DivitransBussines();
            return divitransBussines.getValoresViaticoById(json.getInt("id"));
        } catch (JSONException ex) {
            Logger.getLogger(DivitransRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Metodo para editar los valores de los viaticos de la tabla divitrans
     *
     * @param request
     * @param data
     * @return
     */
    @POST
    @Path("/editarValoresViaticos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarValoresViaticos(@javax.ws.rs.core.Context HttpServletRequest request,
            DivitransConsultaAdmin data) {
        Response.ResponseBuilder response = javax.ws.rs.core.Response.status(500);
        try {
            IDivitrans divitransBusiness = new DivitransBussines();
            boolean resultUpdateValues = divitransBusiness.actualizarValoresDivitrans(data);
            //Se valida respuesta de actualizacion de divitrans
            if (resultUpdateValues) {
                Respuesta resp = new Respuesta();
                resp.setCodigo(String.valueOf(Response.Status.OK.getStatusCode()));
                resp.setDescripcion("Exitoso");
                response.status(Response.Status.OK.getStatusCode());
                response.entity(resp);
                return response.build();
            }

        } catch (Exception ex) {
            Logger.getLogger(DivitransRest.class.getName()).log(Level.SEVERE, null, ex);
            response = javax.ws.rs.core.Response.status(500);
        }
        return response.build();
    }

    /**
     * Servicio Rest encargado de retornar los valores de frecuencia y tiempo a
     * la vista
     *
     * @param request
     * @param data
     * @return
     */
    @POST
    @Path("/getFrecuenciaViaticoById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DivitransConsultaAdmin getFrecuenciaViaticoById(@javax.ws.rs.core.Context HttpServletRequest request,
            String data) {
        try {
            JSONObject json = new JSONObject(data);
            IDivitrans divitransBussines = new DivitransBussines();
            DivitransConsultaAdmin frecuencias = divitransBussines.getFrecuenciaTiempoViaticoById(json.getInt("id"));
            return frecuencias;
        } catch (JSONException ex) {
            Logger.getLogger(DivitransRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Servicio para editar los valores de frecuencia y tiempo del viatico
     * especifico
     *
     * @param request
     * @param data
     * @return
     */
    @POST
    @Path("/editarFrecuenciaViaticos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarFrecuenciaViaticos(@javax.ws.rs.core.Context HttpServletRequest request,
            String data) {
        Response.ResponseBuilder response = javax.ws.rs.core.Response.status(500);
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(java.sql.Time.class, new JsonDeserializer<java.sql.Time>() {
                final DateFormat df = new SimpleDateFormat("HH:mm");

                @Override
                public Time deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
                    try {
                        return new Time(df.parse(je.getAsString()).getTime());
                    } catch (ParseException ex) {
                        Logger.getLogger(DivitransRest.class.getName()).log(Level.SEVERE, null, ex);
                        return null;
                    }
                }
            });
            Gson json = builder.create();
            DivitransConsultaAdmin fromJson = json.fromJson(data, DivitransConsultaAdmin.class);
            IDivitrans divitransBusiness = new DivitransBussines();
            boolean resultUpdateValues = divitransBusiness.actualizarFrecuenciaTiempoDivitrans(fromJson);
            //Se valida respuesta de actualizacion de divitrans
            if (resultUpdateValues) {
                Respuesta resp = new Respuesta();
                resp.setCodigo(String.valueOf(Response.Status.OK.getStatusCode()));
                resp.setDescripcion("Exitoso");
                response.status(Response.Status.OK.getStatusCode());
                response.entity(resp);
                return response.build();
            }

        } catch (Exception ex) {
            Logger.getLogger(DivitransRest.class.getName()).log(Level.SEVERE, null, ex);
            response = javax.ws.rs.core.Response.status(500);
        }
        return response.build();
    }

    /**
     * Servicio encargado de generar el archivo cvs para exportar los viaticos
     * de la prueba
     *
     * @param request
     * @param response
     * @param data
     * @return
     */
    @POST
    @Path("/exportarViaticos")
    @Produces("application/vnd.ms-excel")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response exportarViaticos(@javax.ws.rs.core.Context HttpServletRequest request,
            @javax.ws.rs.core.Context HttpServletResponse response, String data) {
        Response.ResponseBuilder responseBuilder = javax.ws.rs.core.Response.status(500);
        try {
            JSONObject json = new JSONObject(data);
            IDivitrans divitransBusiness = new DivitransBussines();
            File file = divitransBusiness.generarArchivoViaticos(json.optInt("departamento", 0),
                    json.optInt("municipio", 0), json.optDouble("nroDocumento", 0),
                    json.optLong("codigoSitio", 0), json.optInt("idPrueba", 0),
                    json.optInt("idEstado", 0), json.optInt("codigoCargo", 0));
            responseBuilder = Response.ok((Object) file);
            responseBuilder.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=new-excel-file.xls");
            responseBuilder.header(HttpHeaders.CONTENT_TYPE, "text/csv;charset=utf-8");
            Response responseData = responseBuilder.build();
            return responseData;
        } catch (JSONException ex) {
            Logger.getLogger(DivitransRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return responseBuilder.build();
    }

    /**
     * Metodo encargado de listar los estados disponibles en los que puede estar
     * un registro divitrans
     *
     * @param request
     * @return
     */
    @GET
    @Path("/listarEstadosDivitrans")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstadoDivitrans> listarEstadosDivitrans(@javax.ws.rs.core.Context HttpServletRequest request) {
        IDivitrans divitransBusiness = new DivitransBussines();
        return divitransBusiness.listarEstadosDivitrans();
    }

    /**
     * Servicio encargado de consultar los registros divitrans para realizar la
     * aprobación de los viaticos de los sitios asignados segun el usuario
     *
     * @param request
     * @param data
     * @return
     */
    @POST
    @Path("/consultarAprobarPorFiltros")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<DivitransConsultaAdmin> consultarAprobarPorFiltros(@javax.ws.rs.core.Context HttpServletRequest request,
            String data) {
        try {
            JSONObject json = new JSONObject(data);
            IDivitrans divitransBussines = new DivitransBussines();
            return divitransBussines.consultarAprobarPorFiltros(json.optInt("departamento", 0),
                    json.optInt("municipio", 0), json.optDouble("nroDocumento", 0),
                    json.optLong("codigoSitio", 0), json.optInt("idPrueba", 0),
                    json.optInt("idEstado", 0), json.optInt("codigoCargo", 0), json.optDouble("nroDocumentoUsuario"));
        } catch (JSONException ex) {
            Logger.getLogger(DivitransRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Servicio encargado de cambiar el estado de los registros divitrans
     * entrantes, segun el estado recibido
     *
     * @param request
     * @param data
     * @return
     */
    @POST
    @Path("/cambiarEstadoViaticos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cambiarEstadoViaticos(@javax.ws.rs.core.Context HttpServletRequest request, String data) {
        Response.ResponseBuilder response = javax.ws.rs.core.Response.status(500);
        try {
            IDivitrans divitransBusiness = new DivitransBussines();
            JSONObject json = new JSONObject(data);
            int estado = json.getInt("estado");
            String listaViaticos = json.get("lista").toString();
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Integer>>() {
            }.getType();
            List<Integer> listaInteger = gson.fromJson(listaViaticos, listType);
            String idsError = divitransBusiness.cambiarEstadoViaticos(listaInteger, estado);
            if (idsError.isEmpty()) {
                Respuesta resp = new Respuesta();
                resp.setCodigo(String.valueOf(Response.Status.OK.getStatusCode()));
                resp.setDescripcion("Exitoso");
                response.status(Response.Status.OK.getStatusCode());
                response.entity(resp);
                return response.build();
            }
        } catch (JSONException | JsonParseException ex) {
            Logger.getLogger(DivitransRest.class.getName()).log(Level.SEVERE, null, ex);
            response = javax.ws.rs.core.Response.status(500);
        }
        return response.build();
    }

    /**
     * Servicio encargado de actualizar el viatico para cambiar estado a PARA
     * APROBAR, y se realiza la insercion del log de reverso
     *
     * @param request
     * @param data
     * @return
     */
    @POST
    @Path("/reversarAprobacionViatico")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reversarAprobacionViatico(@javax.ws.rs.core.Context HttpServletRequest request, DivitransConsultaAdmin data) {
        Response.ResponseBuilder response = javax.ws.rs.core.Response.status(500);
        try {
            IDivitrans divitransBusiness = new DivitransBussines();
            boolean success = divitransBusiness.reversarAprobacionViatico(data);
            if (success) {
                Respuesta resp = new Respuesta();
                resp.setCodigo(String.valueOf(Response.Status.OK.getStatusCode()));
                resp.setDescripcion("Exitoso");
                response.status(Response.Status.OK.getStatusCode());
                response.entity(resp);
                return response.build();
            }
        } catch (Exception e) {
            Logger.getLogger(DivitransRest.class.getName()).log(Level.SEVERE, null, e);
            response = javax.ws.rs.core.Response.status(500);
        }
        return response.build();
    }

    /**
     * Metodo encargado de responder el archivo csv de ejemplo para el pago de
     * viaticos
     *
     * @param request
     * @param response
     * @return
     */
    @POST
    @Path("/demoPagoViaticos")
    @Produces("application/vnd.ms-excel")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response demoPagoViaticos(@javax.ws.rs.core.Context HttpServletRequest request,
            @javax.ws.rs.core.Context HttpServletResponse response) {
        Response.ResponseBuilder responseBuilder;
        Response responseData;
        try {
            DivitransBussines divitransBusiness = new DivitransBussines();
            File file = divitransBusiness.generarDemoPagoViaticos();
            responseBuilder = Response.ok((Object) file);
            responseBuilder.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=demoPagoViaticos.csv");
            responseBuilder.header(HttpHeaders.CONTENT_TYPE, "text/csv;charset=utf-8");
            responseData = responseBuilder.build();
        } catch (IOException ex) {
            Logger.getLogger(DivitransRest.class.getName()).log(Level.SEVERE, null, ex);
            responseData = javax.ws.rs.core.Response.status(500).build();
        }
        return responseData;
    }

}
