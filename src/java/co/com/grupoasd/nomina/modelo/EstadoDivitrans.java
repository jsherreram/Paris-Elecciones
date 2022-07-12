/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.io.Serializable;

/**
 *
 * @author cprada
 */
public class EstadoDivitrans implements Serializable {

    private int idEstadoDivitrans;
    private String descripcion;

    /**
     * Default constructor
     */
    public EstadoDivitrans() {
    }

    /**
     * Constructor con valores
     *
     * @param idEstadoDivitrans
     * @param descripcion
     */
    public EstadoDivitrans(int idEstadoDivitrans, String descripcion) {
        this.idEstadoDivitrans = idEstadoDivitrans;
        this.descripcion = descripcion;
    }

    /**
     * @return the idEstadoDivitrans
     */
    public int getIdEstadoDivitrans() {
        return idEstadoDivitrans;
    }

    /**
     * @param idEstadoDivitrans the idEstadoDivitrans to set
     */
    public void setIdEstadoDivitrans(int idEstadoDivitrans) {
        this.idEstadoDivitrans = idEstadoDivitrans;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
