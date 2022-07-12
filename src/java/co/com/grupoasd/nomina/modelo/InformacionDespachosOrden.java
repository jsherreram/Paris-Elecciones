/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.util.Date;


/**
 *
 * @author Grupo ASD
 */
public class InformacionDespachosOrden {
    private Long idOrden;
    private String nombrePds;
    private String fechaDespacho;
    private Integer cantidad;
    private Long idDivipolPds;
    private String nombre;
    private Long documento;
    private String celular;
    private String cargo;
    private String estado;
    /**
     * @return the idOrden
     */
    public Long getIdOrden() {
        return idOrden;
    }

    /**
     * @param idOrden the idOrden to set
     */
    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    /**
     * @return the nombrePds
     */
    public String getNombrePds() {
        return nombrePds;
    }

    /**
     * @param nombrePds the nombrePds to set
     */
    public void setNombrePds(String nombrePds) {
        this.nombrePds = nombrePds;
    }

    /**
     * @return the fechaDespacho
     */
    public String getFechaDespacho() {
        return fechaDespacho;
    }

    /**
     * @param fechaDespacho the fechaDespacho to set
     */
    public void setFechaDespacho(String fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the idDivipolPds
     */
    public Long getIdDivipolPds() {
        return idDivipolPds;
    }

    /**
     * @param idDivipolPds the idDivipolPds to set
     */
    public void setIdDivipolPds(Long idDivipolPds) {
        this.idDivipolPds = idDivipolPds;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the documento
     */
    public Long getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(Long documento) {
        this.documento = documento;
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
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
