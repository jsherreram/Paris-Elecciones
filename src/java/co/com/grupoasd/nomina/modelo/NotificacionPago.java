/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.io.Serializable;

/**
 *
 * @author ASD
 */
public class NotificacionPago implements Serializable {

    /**
     * Propiedades de Notificacion
     */
    private long id;
    private Empleado empleado;
    private MedioPago medioPago;
    private Prueba prueba;
    private long valor;
    private String fechaPago;
    private String observaciones;
    private int leido;
    private String observacionesPago;
    private int notificado;
    private int confirmado;
    private String fechaActualiza;
    private String telefono;
    private String link;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    /**
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * @return the medioPago
     */
    public MedioPago getMedioPago() {
        return medioPago;
    }

    /**
     * @param medioPago the medioPago to set
     */
    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }

    /**
     * @return the prueba
     */
    public Prueba getPrueba() {
        return prueba;
    }

    /**
     * @param prueba the prueba to set
     */
    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    /**
     * @return the valor
     */
    public long getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(long valor) {
        this.valor = valor;
    }

    /**
     * @return the fechaPago
     */
    public String getFechaPago() {
        return fechaPago;
    }

    /**
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the leido
     */
    public int getLeido() {
        return leido;
    }

    /**
     * @param leido the leido to set
     */
    public void setLeido(int leido) {
        this.leido = leido;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the observacionesPago
     */
    public String getObservacionesPago() {
        return observacionesPago;
    }

    /**
     * @param observacionesPago the observacionesPago to set
     */
    public void setObservacionesPago(String observacionesPago) {
        this.observacionesPago = observacionesPago;
    }

    /**
     * @return the notificado
     */
    public int getNotificado() {
        return notificado;
    }

    /**
     * @param notificado the notificado to set
     */
    public void setNotificado(int notificado) {
        this.notificado = notificado;
    }

    /**
     * @return the confirmado
     */
    public int getConfirmado() {
        return confirmado;
    }

    /**
     * @param confirmado the confirmado to set
     */
    public void setConfirmado(int confirmado) {
        this.confirmado = confirmado;
    }

    /**
     * @return the fechaActualiza
     */
    public String getFechaActualiza() {
        return fechaActualiza;
    }

    /**
     * @param fechaActualiza the fechaActualiza to set
     */
    public void setFechaActualiza(String fechaActualiza) {
        this.fechaActualiza = fechaActualiza;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
 

}
