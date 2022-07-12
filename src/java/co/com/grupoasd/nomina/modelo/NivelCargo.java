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
public class NivelCargo {

 private int nivel_cargo;
 private String nivel_descripcion;

    public int getNivel_cargo() {
        return nivel_cargo;
    }
    public void setNivel_cargo(int nivel_cargo) {
        this.nivel_cargo = nivel_cargo;
    }
    public String getNivel_descripcion() {
        return nivel_descripcion;
    }
    public void setNivel_descripcion(String nivel_descripcion) {
        this.nivel_descripcion = nivel_descripcion;
    }   
}
