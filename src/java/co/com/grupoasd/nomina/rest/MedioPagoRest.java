/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.MedioPagoDao;
import co.com.grupoasd.nomina.modelo.MedioPago;
import co.com.grupoasd.nomina.modelo.Respuesta;
import co.com.grupoasd.nomina.modelo.TipoCuenta;
import co.com.grupoasd.nomina.modelo.TipoMedioPago;
import co.com.grupoasd.nomina.negocio.mediopago.CoberturaMedioPagoController;
import co.com.grupoasd.nomina.negocio.mediopago.ICoberturaMedioPagoController;
import co.com.grupoasd.nomina.negocio.mediopago.IMedioPagoController;
import co.com.grupoasd.nomina.negocio.mediopago.MedioPagoController;
import java.util.ArrayList;
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
import javax.ws.rs.core.Response;

/**
 *
 * @author Pedro Rodríguez
 */
@Path("/medioPago")
public class MedioPagoRest {

    @GET
    @Path("/listarMediosPago")
    @Produces({"application/json", "application/xml"})
    public List<MedioPago> listar(@javax.ws.rs.core.Context HttpServletRequest request) {
        List<MedioPago> lstMedioPago = new ArrayList<>();
        try {
            MedioPagoDao dao = new MedioPagoDao();
            lstMedioPago = dao.listarMediosPago();
        } catch (Exception ex) {
            Logger.getLogger(MedioPagoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstMedioPago;
    }

    @GET
    @Path("/listarTipoCta")
    @Produces({"application/json", "application/xml"})
    public List<TipoCuenta> listarTipoCuenta() {
        List<TipoCuenta> lstTipoCta = new ArrayList<>();
        try {
            IMedioPagoController objController = new MedioPagoController();
            lstTipoCta = objController.getListTipoCta();
        } catch (Exception ex) {
            Logger.getLogger(MedioPagoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstTipoCta;
    }

    @GET
    @Path("/listarMedios")
    @Produces({"application/json", "application/xml"})
    public String listarMedios(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba) {
        String lstMedioPago = null;
        try {
            IMedioPagoController objController = new MedioPagoController();
            lstMedioPago = objController.getListJsonMedioPago(idPrueba);
        } catch (Exception ex) {
            Logger.getLogger(MedioPagoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstMedioPago;
    }

    @GET
    @Path("/listarTiposMediosPago")
    @Produces({"application/json", "application/xml"})
    public List<TipoMedioPago> listarTiposMediosPago() {
        List<TipoMedioPago> lstTipoMedioPago = new ArrayList<>();
        try {
            IMedioPagoController objController = new MedioPagoController();
            lstTipoMedioPago = objController.getListTiposMediosPago();

        } catch (Exception ex) {
            Logger.getLogger(MedioPagoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstTipoMedioPago;
    }

    @POST
    @Consumes({"application/json"})
    public Response registrarMedioPago(MedioPago mp) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        try {
            IMedioPagoController objController = new MedioPagoController();
            int medioPago = objController.insertar(mp);

            if (medioPago > 0) {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("200");
                respuesta.setDescripcion("Registro Creado");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
            } else {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("201");
                respuesta.setDescripcion("Error:No se pudo registrar el medio de pago.");
                rb.status(201);
                rb.entity(respuesta);
                return rb.build();
            }
        } catch (Exception ex) {
            Logger.getLogger(MedioPagoRest.class.getName()).log(Level.SEVERE, null, ex);

            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Error:No se pudo registrar el medio de pago.");
            rb.status(201);
            rb.entity(respuesta);
        }

        return rb.build();
    }

    @PUT
    @Consumes({"application/json"})
    public Response actualizarMedioPago(MedioPago mp) {
        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        try {
            IMedioPagoController objController = new MedioPagoController();
            boolean actualizoMedioPago = objController.actualizar(mp);

            if (actualizoMedioPago) {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("200");
                respuesta.setDescripcion("Registro actualizado");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();

            } else {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("201");
                respuesta.setDescripcion("Error:No se pudo actualizar el medio de pago.");
                rb.status(201);
                rb.entity(respuesta);
                return rb.build();
            }
        } catch (Exception ex) {
            Logger.getLogger(MedioPagoRest.class.getName()).log(Level.SEVERE, null, ex);

            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Error: No se pudo actualizar el medio de pago." + ex.getMessage());
            rb.status(201);
            rb.entity(respuesta);
        }

        return rb.build();
    }

    @GET
    @Path("/findMedioPago")
    @Produces({"application/json", "application/xml"})
    public MedioPago findMedioPago(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idmedio_pago") int idmedio_pago, @QueryParam("idPrueba") int idPrueba) {
        MedioPago objMedioPago = null;
        try {
            IMedioPagoController objController = new MedioPagoController();
            objMedioPago = objController.findMedioPago(idmedio_pago, idPrueba);
        } catch (Exception ex) {

            Logger.getLogger(MedioPagoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objMedioPago;
    }

    @GET
    @Path("/listarMediosMunicipio")
    @Produces({"application/json", "application/xml"})
    public String listarMediosMunicipio(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("codMunicipio") String codMunicipio, @QueryParam("idPrueba") int idPrueba) {
        String lstMedioPago = null;
        try {
            ICoberturaMedioPagoController objController = new CoberturaMedioPagoController();
            lstMedioPago = objController.listarMediosMunicipioJson(codMunicipio, 1, 1, idPrueba);
        } catch (Exception ex) {
            Logger.getLogger(MedioPagoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstMedioPago;
    }

    @GET
    @Path("/listarMediosMunicipioAsignados")
    @Produces({"application/json", "application/xml"})
    public String listarMediosMunicipioAsignados(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("codMunicipio") String codMunicipio, @QueryParam("idPrueba") int idPrueba) {

        String lstMedioPago = null;
        try {
            ICoberturaMedioPagoController objController = new CoberturaMedioPagoController();
            lstMedioPago = objController.listarMediosMunicipioAsignados(codMunicipio, 1, 1, idPrueba);

        } catch (Exception ex) {
            Logger.getLogger(MedioPagoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstMedioPago;
    }

    @POST
    @Path("/registrarCobertura")
    @Produces({"application/json"})
    public Response registrarCobertura(String json) throws Exception {

        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        try {

            ICoberturaMedioPagoController objController = new CoberturaMedioPagoController();
            int medioPago = objController.insertar(json);

            if (medioPago > 0) {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("200");
                respuesta.setDescripcion("Registro(s) Creado(s)");
                rb.status(200);
                rb.entity(respuesta);
                return rb.build();
            } else {
                Respuesta respuesta = new Respuesta();
                respuesta.setCodigo("201");
                respuesta.setDescripcion("Error:No se pudo registrar el medio de pago.");
                rb.status(201);
                rb.entity(respuesta);
                return rb.build();
            }
        } catch (Exception ex) {
            Logger.getLogger(MedioPagoRest.class.getName()).log(Level.SEVERE, null, ex);

            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Error:No se pudo asignar los medios de pago al municipio.");
            rb.status(201);
            rb.entity(respuesta);
        }

        return rb.build();
    }

}
