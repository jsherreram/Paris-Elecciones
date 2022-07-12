/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ASD
 */
public class DetalleCargoPuestoEvento implements Serializable {
    
    /**
     * 
     */
    private long id;
    private Sitio divipol;
    private Prueba prueba;
    private Departamento departamento;
    private Municipio municipio;
    private Cargo cargo;
    private Evento evento;
    private String ubicacion;
    private Empleado empleado;
    private int estado;
    private String usuario;
    private Date fecha;
    private int asistio;
    private int cantidadAsistio;
    private int cantidadNoAsistio;
    private long codigoTarifa;
    private String observacion;
    private long consecutivo;
    private long orden;
    private int asistenciaBiometrica;
    private Date fechaAsistencia;
    private int idOrden;
    private int devuelto;
    private String salon;
    private int numeroSilla;

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
     * @return the divipol
     */
    public Sitio getDivipol() {
        return divipol;
    }

    /**
     * @param divipol the divipol to set
     */
    public void setDivipol(Sitio divipol) {
        this.divipol = divipol;
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
     * @return the departamento
     */
    public Departamento getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    /**
     * @return the municipio
     */
    public Municipio getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the cargo
     */
    public Cargo getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the evento
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * @param evento the evento to set
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * 
     * @return 
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * 
     * @param ubicacion 
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the asistio
     */
    public int getAsistio() {
        return asistio;
    }

    /**
     * @param asistio the asistio to set
     */
    public void setAsistio(int asistio) {
        this.asistio = asistio;
    }

    /**
     * @return the cantidadAsistio
     */
    public int getCantidadAsistio() {
        return cantidadAsistio;
    }

    /**
     * @param cantidadAsistio the cantidadAsistio to set
     */
    public void setCantidadAsistio(int cantidadAsistio) {
        this.cantidadAsistio = cantidadAsistio;
    }

    /**
     * @return the cantidadNoAsistio
     */
    public int getCantidadNoAsistio() {
        return cantidadNoAsistio;
    }

    /**
     * @param cantidadNoAsistio the cantidadNoAsistio to set
     */
    public void setCantidadNoAsistio(int cantidadNoAsistio) {
        this.cantidadNoAsistio = cantidadNoAsistio;
    }

    /**
     * @return the codigoTarifa
     */
    public long getCodigoTarifa() {
        return codigoTarifa;
    }

    /**
     * @param codigoTarifa the codigoTarifa to set
     */
    public void setCodigoTarifa(long codigoTarifa) {
        this.codigoTarifa = codigoTarifa;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the consecutivo
     */
    public long getConsecutivo() {
        return consecutivo;
    }

    /**
     * @param consecutivo the consecutivo to set
     */
    public void setConsecutivo(long consecutivo) {
        this.consecutivo = consecutivo;
    }

    /**
     * @return the orden
     */
    public long getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(long orden) {
        this.orden = orden;
    }

    /**
     * @return the asistenciaBiometrica
     */
    public int getAsistenciaBiometrica() {
        return asistenciaBiometrica;
    }

    /**
     * @param asistenciaBiometrica the asistenciaBiometrica to set
     */
    public void setAsistenciaBiometrica(int asistenciaBiometrica) {
        this.asistenciaBiometrica = asistenciaBiometrica;
    }

    /**
     * @return the fechaAsistencia
     */
    public Date getFechaAsistencia() {
        return fechaAsistencia;
    }

    /**
     * @param fechaAsistencia the fechaAsistencia to set
     */
    public void setFechaAsistencia(Date fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    /**
     * @return the idOrden
     */
    public int getIdOrden() {
        return idOrden;
    }

    /**
     * @param idOrden the idOrden to set
     */
    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    /**
     * @return the devuelto
     */
    public int getDevuelto() {
        return devuelto;
    }

    /**
     * @param devuelto the devuelto to set
     */
    public void setDevuelto(int devuelto) {
        this.devuelto = devuelto;
    }

    /**
     * @return the salon
     */
    public String getSalon() {
        return salon;
    }

    /**
     * @param salon the salon to set
     */
    public void setSalon(String salon) {
        this.salon = salon;
    }

    /**
     * @return the numeroSilla
     */
    public int getNumeroSilla() {
        return numeroSilla;
    }

    /**
     * @param numeroSilla the numeroSilla to set
     */
    public void setNumeroSilla(int numeroSilla) {
        this.numeroSilla = numeroSilla;
    }

}
