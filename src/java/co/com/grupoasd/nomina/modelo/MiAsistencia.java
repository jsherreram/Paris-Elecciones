/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wilfer Carvajal
 */

@XmlRootElement
public class MiAsistencia {

private String nombreevento;
private int escapacitacion;
private Date fechaevento;
private String tiposesion;
private int asistio;
private int valor;
private String nombresesion;
private String medioregistra;

    public String getMedioregistra() {
        return medioregistra;
    }

    public void setMedioregistra(String medioregistra) {
        this.medioregistra = medioregistra;
    }


    public String getNombreevento() {
        return nombreevento;
    }

    public void setNombreevento(String nombreevento) {
        this.nombreevento = nombreevento;
    }

    public int getEscapacitacion() {
        return escapacitacion;
    }

    public void setEscapacitacion(int escapacitacion) {
        this.escapacitacion = escapacitacion;
    }

    public Date getFechaevento() {
        return fechaevento;
    }

    public void setFechaevento(Date fechaevento) {
        this.fechaevento = fechaevento;
    }

    public String getTiposesion() {
        return tiposesion;
    }

    public void setTiposesion(String tiposesion) {
        this.tiposesion = tiposesion;
    }

    public int getAsistio() {
        return asistio;
    }

    public void setAsistio(int asistio) {
        this.asistio = asistio;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getNombresesion() {
        return nombresesion;
    }

    public void setNombresesion(String nombresesion) {
        this.nombresesion = nombresesion;
    }
}
