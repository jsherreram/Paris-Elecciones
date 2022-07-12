/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.negocio.campagna;

import co.com.grupoasd.nomina.dao.CampagnaDao;
import co.com.grupoasd.nomina.dto.CallInfo;
import co.com.grupoasd.nomina.modelo.Campagna;
import java.util.List;

/**
 *
 * @author Pedro Rodríguez
 */
public class CampagnaController {

    private CampagnaDao dao = new CampagnaDao();

    public CampagnaController() {

    }

    public List<Campagna> listar() {
        return dao.listar();
    }

    public List<CallInfo> consultarDatosCampagna(int idPrueba, int idCampagna, int idEstado,
            int idCargo, String idNodo, String idMunicipio) {
        String urlConvocatoria = System.getProperty("urlConvocatorias");
        return dao.consultarDatosCampagna(idPrueba, idCampagna, idEstado, idCargo, idNodo, idMunicipio, urlConvocatoria);
    }

    /**
     * Metodo que invoca el Dao de Campagna para consultae los datos de una
     * campagna de encuesta
     *
     * @param idPrueba
     * @param idCampagna
     * @param idProgreso
     * @param idEvento
     * @return
     */
    public List<CallInfo> consultarDatosCampagnaEncuesta(int idPrueba, int idCampagna, int idProgreso, String idEvento) {
        return dao.consultarDatosCampagnaEncuesta(idPrueba, idCampagna, idProgreso, idEvento);
    }

}
