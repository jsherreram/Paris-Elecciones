/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author Pedro Rodríguez
 */
public class StatusCargue {

    private int	id;
    private String	usuario;
    private int	idtipoCargue;
    private String	nombreArchivo;
    private String	fechaHoraInicio;
    private String	fechaHoraFinal;
    private int	estadoStatus;
    private int	cantidadRegistrosTotal;
    private int	cantidadRegistrosProcesadosOk;
    private int	cantidadRegistrosProcesadosError;
    private String ArchivoErrores;
    private String estado;
    private String mostrarImagenArchivoError;
    private int identificadorRegistro;

    public StatusCargue(){}

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the idtipoCargue
     */
    public int getIdtipoCargue() {
        return idtipoCargue;
    }

    /**
     * @param idtipoCargue the idtipoCargue to set
     */
    public void setIdtipoCargue(int idtipoCargue) {
        this.idtipoCargue = idtipoCargue;
    }

    /**
     * @return the nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * @return the fechaHoraInicio
     */
    public String getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    /**
     * @param fechaHoraInicio the fechaHoraInicio to set
     */
    public void setFechaHoraInicio(String fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    /**
     * @return the fechaHoraFinal
     */
    public String getFechaHoraFinal() {
        return fechaHoraFinal;
    }

    /**
     * @param fechaHoraFinal the fechaHoraFinal to set
     */
    public void setFechaHoraFinal(String fechaHoraFinal) {
        this.fechaHoraFinal = fechaHoraFinal;
    }

    /**
     * @return the estadoStatus
     */
    public int getEstadoStatus() {
        return estadoStatus;
    }

    /**
     * @param estadoStatus the estadoStatus to set
     */
    public void setEstadoStatus(int estadoStatus) {
        this.estadoStatus = estadoStatus;
    }

    /**
     * @return the cantidadRegistrosTotal
     */
    public int getCantidadRegistrosTotal() {
        return cantidadRegistrosTotal;
    }

    /**
     * @param cantidadRegistrosTotal the cantidadRegistrosTotal to set
     */
    public void setCantidadRegistrosTotal(int cantidadRegistrosTotal) {
        this.cantidadRegistrosTotal = cantidadRegistrosTotal;
    }

    /**
     * @return the cantidadRegistrosProcesadosOk
     */
    public int getCantidadRegistrosProcesadosOk() {
        return cantidadRegistrosProcesadosOk;
    }

    /**
     * @param cantidadRegistrosProcesadosOk the cantidadRegistrosProcesadosOk to set
     */
    public void setCantidadRegistrosProcesadosOk(int cantidadRegistrosProcesadosOk) {
        this.cantidadRegistrosProcesadosOk = cantidadRegistrosProcesadosOk;
    }

    /**
     * @return the cantidadRegistrosProcesadosError
     */
    public int getCantidadRegistrosProcesadosError() {
        return cantidadRegistrosProcesadosError;
    }

    /**
     * @param cantidadRegistrosProcesadosError the cantidadRegistrosProcesadosError to set
     */
    public void setCantidadRegistrosProcesadosError(int cantidadRegistrosProcesadosError) {
        this.cantidadRegistrosProcesadosError = cantidadRegistrosProcesadosError;
    }

    /**
     * @return the pathArchivoErrores
     */
    public String getArchivoErrores() {
        return ArchivoErrores;
    }

    /**
     * @param pathArchivoErrores the pathArchivoErrores to set
     */
    public void setArchivoErrores(String pathArchivoErrores) {
        this.ArchivoErrores = pathArchivoErrores;
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

    /**
     * @return the mostrarImagenArchivoError
     */
    public String getMostrarImagenArchivoError() {
        return mostrarImagenArchivoError;
    }

    /**
     * @param mostrarImagenArchivoError the mostrarImagenArchivoError to set
     */
    public void setMostrarImagenArchivoError(String mostrarImagenArchivoError) {
        this.mostrarImagenArchivoError = mostrarImagenArchivoError;
    }

    public int getIdentificadorRegistro() {
        return identificadorRegistro;
    }

    public void setIdentificadorRegistro(int identificadorRegistro) {
        this.identificadorRegistro = identificadorRegistro;
    }
    
    
    
}
