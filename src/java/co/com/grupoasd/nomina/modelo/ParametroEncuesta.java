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
public class ParametroEncuesta {
    
    
    private int idParametro;
    private String tipoSesion;
    private String parteEncuesta;
    private String horaInicio;
    private String horaFin;
    private String horaInicioAlerta;

    public ParametroEncuesta() {
    }

    public int getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }

    public String getTipoSesion() {
        return tipoSesion;
    }

    public void setTipoSesion(String tipoSesion) {
        this.tipoSesion = tipoSesion;
    }

    public String getParteEncuesta() {
        return parteEncuesta;
    }

    public void setParteEncuesta(String parteEncuesta) {
        this.parteEncuesta = parteEncuesta;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getHoraInicioAlerta() {
        return horaInicioAlerta;
    }

    public void setHoraInicioAlerta(String horaInicioAlerta) {
        this.horaInicioAlerta = horaInicioAlerta;
    }
    
    
    
}
