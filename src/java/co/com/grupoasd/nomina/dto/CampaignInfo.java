/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dto;

import java.util.List;

/**
 *
 * @author ASD
 */
public class CampaignInfo {
    
    private int idCampagna;
    private List<CallInfo> lista;

    /**
     * @return the idCampagna
     */
    public int getIdCampagna() {
        return idCampagna;
    }

    /**
     * @param idCampagna the idCampagna to set
     */
    public void setIdCampagna(int idCampagna) {
        this.idCampagna = idCampagna;
    }

    /**
     * @return the lista
     */
    public List<CallInfo> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<CallInfo> lista) {
        this.lista = lista;
    }
    
}
