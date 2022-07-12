/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ASD
 */
public class CallInfo implements Serializable {

    private int id;
    private int idCampaign;
    private String celular;
    private List<CallAttribute> listAttributes;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the listAttributes
     */
    public List<CallAttribute> getListAttributes() {
        return listAttributes;
    }

    /**
     * @param listAttributes the listAttributes to set
     */
    public void setListAttributes(List<CallAttribute> listAttributes) {
        this.listAttributes = listAttributes;
    }

    /**
     * @return the idCampaign
     */
    public int getIdCampaign() {
        return idCampaign;
    }

    /**
     * @param idCampaign the idCampaign to set
     */
    public void setIdCampaign(int idCampaign) {
        this.idCampaign = idCampaign;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }
    
}
