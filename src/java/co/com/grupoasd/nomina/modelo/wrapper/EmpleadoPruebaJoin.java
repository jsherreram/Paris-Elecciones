/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.grupoasd.nomina.modelo.wrapper;

import co.com.grupoasd.nomina.dao.EmpleadoPruebaEstadoDao;
import co.com.grupoasd.nomina.modelo.Empleado;
import co.com.grupoasd.nomina.modelo.EmpleadoPruebaEstado;

/**
 * Bean usado en el modulo de Pagos
 * @author Administrador
 */
public class EmpleadoPruebaJoin extends Empleado{
    
    private String nombreCargo;
    private String nombreDocumento;
    private String nombrePrueba;
    private String codigoPrueba;
    private EmpleadoPruebaEstado prueba;

    /**
     * @return the nombreCargo
     */
    public String getNombreCargo() {
        
        return nombreCargo;
    }

    /**
     * @param nombreCargo the nombreCargo to set
     */
    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    /**
     * @return the nombreDocumento
     */
    public String getNombreDocumento() {
        return nombreDocumento;
    }

    /**
     * @param nombreDocumento the nombreDocumento to set
     */
    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

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
     * @return the codigoPrueba
     */
    public String getCodigoPrueba() {
        return codigoPrueba;
    }

    /**
     * @param codigoPrueba the codigoPrueba to set
     */
    public void setCodigoPrueba(String codigoPrueba) {
        this.codigoPrueba = codigoPrueba;
    }

    /**
     * @return the prueba
     */
    public EmpleadoPruebaEstado getPrueba() {
        return prueba;
    }

    /**
     * @param prueba the prueba to set
     */
    public void setPrueba(EmpleadoPruebaEstado prueba) {
        this.prueba = prueba;
    }
}
