/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.util.Date;

/**
 *
 * @author ASD
 */
public class TareaConfirmacion {
    
    
    private String tipo;
    private int estado;
    private Date fechaEnvio;
    private String formaConfirmacion;
    private Date fechaConfirmacion;
    private String emailEnviado;
    private int idEmpleado;
    private String codigoCargo;
    private int idPrueba;
    private int idDivipol;

    public TareaConfirmacion() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getFormaConfirmacion() {
        return formaConfirmacion;
    }

    public void setFormaConfirmacion(String formaConfirmacion) {
        this.formaConfirmacion = formaConfirmacion;
    }

    public Date getFechaConfirmacion() {
        return fechaConfirmacion;
    }

    public void setFechaConfirmacion(Date fechaConfirmacion) {
        this.fechaConfirmacion = fechaConfirmacion;
    }

    public String getEmailEnviado() {
        return emailEnviado;
    }

    public void setEmailEnviado(String emailEnviado) {
        this.emailEnviado = emailEnviado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(String codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public int getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(int idPrueba) {
        this.idPrueba = idPrueba;
    }

    public int getIdDivipol() {
        return idDivipol;
    }

    public void setIdDivipol(int idDivipol) {
        this.idDivipol = idDivipol;
    }  
    
}
