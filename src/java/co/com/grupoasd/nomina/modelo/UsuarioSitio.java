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
 * @author Administrador
 */
@XmlRootElement
public class UsuarioSitio {
    private long usuario;
    private int idDivipol;
    private Date fechaActualiza;
    private Sitio sitio;

    public long getUsuario() {
        return usuario;
    }

    public void setUsuario(long usuario) {
        this.usuario = usuario;
    }

    public int getIdDivipol() {
        return idDivipol;
    }

    public void setIdDivipol(int idDivipol) {
        this.idDivipol = idDivipol;
    }

    public Date getFechaActualiza() {
        return fechaActualiza;
    }

    public void setFechaActualiza(Date fechaActualiza) {
        this.fechaActualiza = fechaActualiza;
    }

    public Sitio getSitio() {
        return sitio;
    }

    public void setSitio(Sitio sitio) {
        this.sitio = sitio;
    }
    
}
