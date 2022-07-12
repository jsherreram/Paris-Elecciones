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
 * @author Grupo ASD
 */
@XmlRootElement
public class EmpleadoPruebaEstado {
   
    private int id;
    private int idEmpleado;
    private int idPrueba;
    private int idestpersona;
    private String codigoCargo;    
    private int idMedioPago;
    private int idBanco;
    private String numeroCuenta;
    private int tipoCuenta;
    private String nombrePrueba;
    private int tipoPrueba;
    private String nombreTipoPrueba;
    private String nombre;
    private String cargo;
    private Cargo cargoObj;
    private Date fechaAplicacion ;
    private int idprueba;
    private String estado;
    private String usuario;
    private int bloqueado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(int bloqueado) {
        this.bloqueado = bloqueado;
    }
    
    
    /**
     * @return the idEmpleado
     */
    public int getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado the idEmpleado to set
     */
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * @return the idPrueba
     */
    public int getIdPrueba() {
        return idPrueba;
    }

    /**
     * @param idPrueba the idPrueba to set
     */
    public void setIdPrueba(int idPrueba) {
        this.idPrueba = idPrueba;
    }

    /**
     * @return the idMedioPago
     */
    public int getIdMedioPago() {
        return idMedioPago;
    }

    /**
     * @param idMedioPago the idMedioPago to set
     */
    public void setIdMedioPago(int idMedioPago) {
        this.idMedioPago = idMedioPago;
    }

    /**
     * @return the idBanco
     */
    public int getIdBanco() {
        return idBanco;
    }

    /**
     * @param idBanco the idBanco to set
     */
    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    /**
     * @return the numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * @param numeroCuenta the numeroCuenta to set
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * @return the tipoCuenta
     */
    public int getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * @param tipoCuenta the tipoCuenta to set
     */
    public void setTipoCuenta(int tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * @return the idestpersona
     */
    public int getIdestpersona() {
        return idestpersona;
    }

    /**
     * @param idestpersona the idestpersona to set
     */
    public void setIdestpersona(int idestpersona) {
        this.idestpersona = idestpersona;
    }

    /**
     * @return the codigoCargo
     */
    public String getCodigoCargo() {
        return codigoCargo;
    }

    /**
     * @param codigoCargo the codigoCargo to set
     */
    public void setCodigoCargo(String codigoCargo) {
        this.codigoCargo = codigoCargo;
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
     * @return the tipoPrueba
     */
    public int getTipoPrueba() {
        return tipoPrueba;
    }

    /**
     * @param tipoPrueba the tipoPrueba to set
     */
    public void setTipoPrueba(int tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }

  
    

    
   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Cargo getCargoObj() {
        return cargoObj;
    }

    public void setCargoObj(Cargo cargoObj) {
        this.cargoObj = cargoObj;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public int getIdprueba() {
        return idprueba;
    }

    public void setIdprueba(int idprueba) {
        this.idprueba = idprueba;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreTipoPrueba() {
        return nombreTipoPrueba;
    }

    public void setNombreTipoPrueba(String nombreTipoPrueba) {
        this.nombreTipoPrueba = nombreTipoPrueba;
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

    
}
