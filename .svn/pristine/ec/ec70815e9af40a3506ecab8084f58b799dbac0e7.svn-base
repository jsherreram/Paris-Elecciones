/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.rest;

import co.com.grupoasd.nomina.dto.CallInfo;
import co.com.grupoasd.nomina.dto.CampaignInfo;
import co.com.grupoasd.nomina.modelo.Campagna;
import co.com.grupoasd.nomina.negocio.campagna.CampagnaController;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Pedro Rodríguez
 */
@Path("/campagna")
public class CampagnaRest {

    @GET
    @Path("/listar")
    @Produces({"application/json"})
    public List<Campagna> listar() {
        List<Campagna> lstCampagna = new ArrayList<>();
        try {
            CampagnaController objController = new CampagnaController();
            lstCampagna = objController.listar();
        } catch (Exception ex) {
            Logger.getLogger(CampagnaRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstCampagna;
    }

    /**
     * Metodo encargado de obtener los datos para las llamadas de la campagna
     *
     * @param idPrueba
     * @param idCampagna
     * @param idEstado
     * @param idCargo
     * @param idNodo
     * @param idMunicipio
     * @return
     */
    @GET
    @Path("/consultarDatosCampagna")
    @Produces(MediaType.APPLICATION_JSON)
    public CampaignInfo consultarDatosCampagna(@QueryParam("idPrueba") int idPrueba,
            @QueryParam("idCampagna") int idCampagna, @QueryParam("idEstado") int idEstado,
            @QueryParam("idCargo") int idCargo, @QueryParam("idNodo") String idNodo, @QueryParam("idMunicipio") String idMunicipio) {
        CampagnaController objController = new CampagnaController();
        List<CallInfo> consultarDatosCampagna = objController.consultarDatosCampagna(idPrueba,
                idCampagna, idEstado, idCargo, idNodo, idMunicipio);
        CampaignInfo infoCampagna = new CampaignInfo();
        infoCampagna.setIdCampagna(idCampagna);
        infoCampagna.setLista(consultarDatosCampagna);
        return infoCampagna;
    }

    /**
     * Servicio encargado de consultar los datos para la campagna de encuesta
     *
     * @param idPrueba
     * @param idCampagna
     * @param idProgreso
     * @param idEvento
     * @return
     */
    @GET
    @Path("/consultarDatosCampagnaEncuesta")
    @Produces(MediaType.APPLICATION_JSON)
    public CampaignInfo consultarDatosCampagnaEncuesta(@QueryParam("idPrueba") int idPrueba,
            @QueryParam("idCampagna") int idCampagna, @QueryParam("idProgreso") int idProgreso,
            @QueryParam("idEvento") String idEvento) {
        CampagnaController objController = new CampagnaController();
        List<CallInfo> consultarDatosCampagna = objController.consultarDatosCampagnaEncuesta(idPrueba,
                idCampagna, idProgreso, idEvento);
        CampaignInfo infoCampagna = new CampaignInfo();
        infoCampagna.setIdCampagna(idCampagna);
        infoCampagna.setLista(consultarDatosCampagna);
        return infoCampagna;
    }
}
