/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.util.Date;

/**
 *
 * @author Grupo ASD
 */
public class ConfirmarAsistenciaVO {
    private String nombrePrueba;
    private String fechaAplicacion;
    private Integer codigoPrueba;
    private String tipoPrueba;
    private String cargo;
    private int dias;
    

    /**
     * @return the nombrePrueba
     */
    public String getNombrePrueba() {
        return nombrePrueba;
    }

    /**
     * @param nombrePrueba the nombrePrueba to set
     */
    public void setNombrePrueba(String nombrePrueba) {
        this.nombrePrueba = nombrePrueba;
    }

    /**
     * @return the fechaAplicacion
     */
    public String getFechaAplicacion() {
        return fechaAplicacion;
    }

    /**
     * @param fechaAplicacion the fechaAplicacion to set
     */
    public void setFechaAplicacion(String fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    /**
     * @return the codigoPrueba
     */
    public Integer getCodigoPrueba() {
        return codigoPrueba;
    }

    /**
     * @param codigoPrueba the codigoPrueba to set
     */
    public void setCodigoPrueba(Integer codigoPrueba) {
        this.codigoPrueba = codigoPrueba;
    }

    /**
     * @return the tipoPrueba
     */
    public String getTipoPrueba() {
        return tipoPrueba;
    }

    /**
     * @param tipoPrueba the tipoPrueba to set
     */
    public void setTipoPrueba(String tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the dias
     */
    public int getDias() {
        return dias;
    }

    /**
     * @param dias the dias to set
     */
    public void setDias(int dias) {
        this.dias = dias;
    }

    
}
