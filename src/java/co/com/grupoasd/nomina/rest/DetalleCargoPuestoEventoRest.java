/**
 *
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dao.DetalleCargoPuestoEventoDao;
import co.com.grupoasd.nomina.modelo.Cargo;
import co.com.grupoasd.nomina.modelo.DetalleCargoPuestoEvento;
import co.com.grupoasd.nomina.modelo.Evento;
import co.com.grupoasd.nomina.modelo.Prueba;
import co.com.grupoasd.nomina.modelo.Respuesta;
import co.com.grupoasd.nomina.modelo.Sitio;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Date: 18/05/2022
 *
 * @author John Steak Herrera Moreno
 * @version 1.0
 */
@Path("/detalleCargoPuestoEvento")
public class DetalleCargoPuestoEventoRest {

    /**
     * Author: John Steak Herrera Moreno 
     * Date: 18/05/2022
     *
     * @param detalleCargoPuestoEvento
     * @return
     */
    @POST
    @Path("/create")
    @Consumes({"application/json", "application/xml"})
    public Response create(DetalleCargoPuestoEvento detalleCargoPuestoEvento) {

        Response.ResponseBuilder rb = javax.ws.rs.core.Response.status(200);
        DetalleCargoPuestoEventoDao detalleCargoPuestoEventoDao = new DetalleCargoPuestoEventoDao();
        long idDetalleCargoPuestoEvento = detalleCargoPuestoEventoDao.insertDetalleCargoPuestoEvento(detalleCargoPuestoEvento);

        if (idDetalleCargoPuestoEvento > 0) {
            detalleCargoPuestoEvento.setId(idDetalleCargoPuestoEvento);
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("200");
            respuesta.setDescripcion("Registro Creado");
            respuesta.setId((int) idDetalleCargoPuestoEvento);
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        } else {
            Respuesta respuesta = new Respuesta();
            respuesta.setCodigo("201");
            respuesta.setDescripcion("Código de Detalle Cargo Puesto Evento ya existe, o se genero un problema al insertar");
            respuesta.setId((int) idDetalleCargoPuestoEvento);
            rb.status(200);
            rb.entity(respuesta);
            return rb.build();
        }
    }

    /**
     * Author: John Steak Herrera Moreno 
     * Date: 10/06/2022
     *
     * @param request
     * @param idPrueba
     * @param idEvento
     * @param idSitio
     * @param idCargo
     * @param ubicacion
     * @return
     * @throws Exception
     */
    @GET
    @Path("/find")
    @Produces({"application/json", "application/xml"})
    public DetalleCargoPuestoEvento findEventoAdicional(@javax.ws.rs.core.Context HttpServletRequest request,
            @QueryParam("idPrueba") int idPrueba, @QueryParam("idEvento") int idEvento,
            @QueryParam("idSitio") int idSitio, @QueryParam("idCargo") String idCargo,
            @QueryParam("ubicacion") String ubicacion) throws Exception {
        DetalleCargoPuestoEventoDao detalleCargoPuestoEventoDao = new DetalleCargoPuestoEventoDao();
        Prueba prueba = new Prueba();
        prueba.setIdprueba(idPrueba);
        Evento evento = new Evento();
        evento.setCodigoEvento(idEvento);
        Sitio sitio = new Sitio();
        sitio.setIddivipol(idSitio);
        Cargo cargo = new Cargo();
        cargo.setCodigoCargo(idCargo);
        return detalleCargoPuestoEventoDao.findEventoAdicional(idPrueba, idEvento, idSitio, idCargo, ubicacion);
    }

}
