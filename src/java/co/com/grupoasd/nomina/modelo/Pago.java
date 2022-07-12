/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Rodríguez
 */
public class Pago {
    
    private String nombreDepartamento;
    private String nombreMunicipio;
    private String codigoMunicipio;
    private int idEmpleado;
    private String tipoIdentificacion;
    private long nrodoc;
    private String tipodoc;
    private String apellido1;
    private String apellido2;
    private String nombre1;
    private String nombre2;
    private String direccion;
    private String telefono;
    private String celular;
    private String email;
    private String idCuenta; 
    private String UltimoCargo;
    
    private String ultimoZona;
    private String ultimoPuesto;
    private String ultimoUbicacion;
    
    private List<RegistroEvento> registrosEvento;
    
   
    public Pago(){
        this.registrosEvento = new ArrayList();
    };

    /**
     * @return the nombreDepartamento
     */
    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    /**
     * @param nombreDepartamento the nombreDepartamento to set
     */
    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    /**
     * @return the nombreMunicipio
     */
    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    /**
     * @param nombreMunicipio the nombreMunicipio to set
     */
    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
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
     * @return the nrodoc
     */
    public long getNrodoc() {
        return nrodoc;
    }

    /**
     * @param nrodoc the nrodoc to set
     */
    public void setNrodoc(long nrodoc) {
        this.nrodoc = nrodoc;
    }

    /**
     * @return the tipodoc
     */
    public String getTipodoc() {
        return tipodoc;
    }

    /**
     * @param tipodoc the tipodoc to set
     */
    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }

    /**
     * @return the apellido1
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * @param apellido1 the apellido1 to set
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * @return the apellido2
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * @param apellido2 the apellido2 to set
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * @return the nombre1
     */
    public String getNombre1() {
        return nombre1;
    }

    /**
     * @param nombre1 the nombre1 to set
     */
    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    /**
     * @return the nombre2
     */
    public String getNombre2() {
        return nombre2;
    }

    /**
     * @param nombre2 the nombre2 to set
     */
    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the idCuenta
     */
    public String getIdCuenta() {
        return idCuenta;
    }

    /**
     * @param idCuenta the idCuenta to set
     */
    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     * @return the registrosEvento
     */
    public List<RegistroEvento> getRegistrosEvento() {
        return registrosEvento;
    }

    /**
     * @param registrosEvento the registrosEvento to set
     */
    public void setRegistrosEvento(List<RegistroEvento> registrosEvento) {
        this.registrosEvento = registrosEvento;
    }

    /**
     * @return the codigoMunicipio
     */
    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    /**
     * @param codigoMunicipio the codigoMunicipio to set
     */
    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    /**
     * @return the UltimoCargo
     */
    public String getUltimoCargo() {
        return UltimoCargo;
    }

    /**
     * @param UltimoCargo the UltimoCargo to set
     */
    public void setUltimoCargo(String UltimoCargo) {
        this.UltimoCargo = UltimoCargo;
    }

    /**
     * @return the ultimoPuesto
     */
    public String getUltimoPuesto() {
        return ultimoPuesto;
    }

    /**
     * @param ultimoPuesto the ultimoPuesto to set
     */
    public void setUltimoPuesto(String ultimoPuesto) {
        this.ultimoPuesto = ultimoPuesto;
    }

    /**
     * @return the ultimoUbicacion
     */
    public String getUltimoUbicacion() {
        return ultimoUbicacion;
    }

    /**
     * @param ultimoUbicacion the ultimoUbicacion to set
     */
    public void setUltimoUbicacion(String ultimoUbicacion) {
        this.ultimoUbicacion = ultimoUbicacion;
    }

    /**
     * @return the ultimoZona
     */
    public String getUltimoZona() {
        return ultimoZona;
    }

    /**
     * @param ultimoZona the ultimoZona to set
     */
    public void setUltimoZona(String ultimoZona) {
        this.ultimoZona = ultimoZona;
    }

    /**
     * @return the tipoIdentificacion
     */
    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    /**
     * @param tipoIdentificacion the tipoIdentificacion to set
     */
    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    
}
