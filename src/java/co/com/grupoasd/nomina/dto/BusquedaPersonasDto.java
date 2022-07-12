/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.dto;

import co.com.grupoasd.nomina.modelo.Sitio;
import java.io.Serializable;

/**
 *
 * @author ASD
 */
public class BusquedaPersonasDto implements Serializable {

    private String codDepartamento;
    private int codCargo;
    private int idPrueba;
    private int codDeptoDane;
    private int codigoMunDane;
    private int cantidadSalones;
    private double distancia;
    private int viajar;
    private Sitio sitio;

    
    /**
     * @return the codCargo
     */
    public int getCodCargo() {
        return codCargo;
    }

    /**
     * @param codCargo the codCargo to set
     */
    public void setCodCargo(int codCargo) {
        this.codCargo = codCargo;
    }

    /**
     * @return the idPrueba
     */
    public int getIdPrueba() {
        return idPrueba;
    }

    /**
     * @param idPrueba the idPrueba to set
     */
    public void setIdPrueba(int idPrueba) {
        this.idPrueba = idPrueba;
    }

    /**
     * @return the codDeptoDane
     */
    public int getCodDeptoDane() {
        return codDeptoDane;
    }

    /**
     * @param codDeptoDane the codDeptoDane to set
     */
    public void setCodDeptoDane(int codDeptoDane) {
        this.codDeptoDane = codDeptoDane;
    }

    /**
     * @return the codigoMunDane
     */
    public int getCodigoMunDane() {
        return codigoMunDane;
    }

    /**
     * @param codigoMunDane the codigoMunDane to set
     */
    public void setCodigoMunDane(int codigoMunDane) {
        this.codigoMunDane = codigoMunDane;
    }

    /**
     * @return the cantidadSalones
     */
    public int getCantidadSalones() {
        return cantidadSalones;
    }

    /**
     * @param cantidadSalones the cantidadSalones to set
     */
    public void setCantidadSalones(int cantidadSalones) {
        this.cantidadSalones = cantidadSalones;
    }

    /**
     * @return the viajar
     */
    public int getViajar() {
        return viajar;
    }

    /**
     * @param viajar the viajar to set
     */
    public void setViajar(int viajar) {
        this.viajar = viajar;
    }

    /**
     * @return the sitio
     */
    public Sitio getSitio() {
        return sitio;
    }

    /**
     * @param sitio the sitio to set
     */
    public void setSitio(Sitio sitio) {
        this.sitio = sitio;
    }

    /**
     * @return the codDepartamento
     */
    public String getCodDepartamento() {
        return codDepartamento;
    }

    /**
     * @param codDepartamento the codDepartamento to set
     */
    public void setCodDepartamento(String codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    /**
     * @return the distancia
     */
    public double getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

}
