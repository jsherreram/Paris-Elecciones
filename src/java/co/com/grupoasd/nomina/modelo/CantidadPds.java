/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.util.ArrayList;

/**
 *
 * @author CristianAlexander
 */
public class CantidadPds {

    private Integer cantidad;
    private ArrayList<Long> nrodocs ;

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the nrodocs
     */
    public ArrayList<Long> getNrodocs() {
        return nrodocs;
    }

    /**
     * @param nrodocs the nrodocs to set
     */
    public void setNrodocs(ArrayList<Long> nrodocs) {
        this.nrodocs = nrodocs;
    }
}
