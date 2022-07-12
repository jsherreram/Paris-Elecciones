/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASD
 */

@XmlRootElement
public class TipoPruebaEsp {

 private int tprueba;
 private String tipo;
 private String descripcion;

    public int getTprueba() {
        return tprueba;
    }

    public void setTprueba(int tprueba) {
        this.tprueba = tprueba;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String dscripcion) {
        this.descripcion = dscripcion;
    }
    

    
}
