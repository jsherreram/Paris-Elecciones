/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

/**
 *
 * @author Pedro Rodriguez
 */
public class PagoEvento {

    private int codigoEvento ;
    private String nombre;
    private String fecha;
    
    public PagoEvento(){}

    /**
     * @return the codigoEvento
     */
    public int getCodigoEvento() {
        return codigoEvento;
    }

    /**
     * @param codigoEvento the codigoEvento to set
     */
    public void setCodigoEvento(int codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
}
