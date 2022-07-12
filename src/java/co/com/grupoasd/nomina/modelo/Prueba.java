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
 * @author Pedro Rodríguez
 */

@XmlRootElement
public class Prueba {
    
    private int idprueba;
    private String nombre;
    private int tprueba;
    private String codigoEstadoPrueba;
    private int idEstadoPrueba;
    private String estadoprueba;
    private String descripcionestprueba;
    private int dias;
    private String vtiger_campo_estado;
    private String vtiger_campo_texto;
    private String vtiger_campo_cargo;
    private String tipoPrueba;
    private Date fechaaplicacion;
    private String tnombreprueba;
    private String tpruebadescripcion;
    private Date fecha_final_aplicacion;
    private Date fecha_final_convocatoria;
    private Date fecha_inicio_convocatoria;
    private String vtiger_campo_nodo;
    private String vtiger_campo_municipio;
    private String texto_convocatoria;
    public Prueba(){
    }

    public int getIdEstadoPrueba() {
        return idEstadoPrueba;
    }

    public String getTexto_convocatoria() {
        return texto_convocatoria;
    }

    public void setTexto_convocatoria(String texto_convocatoria) {
        this.texto_convocatoria = texto_convocatoria;
    }

    public void setIdEstadoPrueba(int idEstadoPrueba) {
        this.idEstadoPrueba = idEstadoPrueba;
    }

    public String getCodigoEstadoPrueba() {
        return codigoEstadoPrueba;
    }

    public void setCodigoEstadoPrueba(String codigoEstadoPrueba) {
        this.codigoEstadoPrueba = codigoEstadoPrueba;
    }

    public String getDescripcionestprueba() {
        return descripcionestprueba;
    }

    public void setDescripcionestprueba(String descripcionestprueba) {
        this.descripcionestprueba = descripcionestprueba;
    }

    public String getVtiger_campo_nodo() {
        return vtiger_campo_nodo;
    }

    public void setVtiger_campo_nodo(String vtiger_campo_nodo) {
        this.vtiger_campo_nodo = vtiger_campo_nodo;
    }

    public String getVtiger_campo_municipio() {
        return vtiger_campo_municipio;
    }

    public void setVtiger_campo_municipio(String vtiger_campo_municipio) {
        this.vtiger_campo_municipio = vtiger_campo_municipio;
    }

    public Date getFecha_final_aplicacion() {
        return fecha_final_aplicacion;
    }

    public void setFecha_final_aplicacion(Date fecha_final_aplicacion) {
        this.fecha_final_aplicacion = fecha_final_aplicacion;
    }

    public Date getFecha_final_convocatoria() {
        return fecha_final_convocatoria;
    }

    public void setFecha_final_convocatoria(Date fecha_final_convocatoria) {
        this.fecha_final_convocatoria = fecha_final_convocatoria;
    }

    public Date getFecha_inicio_convocatoria() {
        return fecha_inicio_convocatoria;
    }

    public void setFecha_inicio_convocatoria(Date fecha_inicio_convocatoria) {
        this.fecha_inicio_convocatoria = fecha_inicio_convocatoria;
    }

    public String getTnombreprueba() {
        return tnombreprueba;
    }

    public void setTnombreprueba(String tnombreprueba) {
        this.tnombreprueba = tnombreprueba;
    }

    public String getTpruebadescripcion() {
        return tpruebadescripcion;
    }

    public void setTpruebadescripcion(String tpruebadescripcion) {
        this.tpruebadescripcion = tpruebadescripcion;
    }

    /**
     * @return the idprueba
     */
    
    
    public int getIdprueba() {
        return idprueba;
    }

    /**
     * @param idprueba the idprueba to set
     */
    public void setIdprueba(int idprueba) {
        this.idprueba = idprueba;
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
     * @return the tprueba
     */
    public int getTprueba() {
        return tprueba;
    }

    /**
     * @param tprueba the tprueba to set
     */
    public void setTprueba(int tprueba) {
        this.tprueba = tprueba;
    }

    /**
     * @return the estadoprueba
     */
    public String getEstadoprueba() {
        return estadoprueba;
    }

    /**
     * @param estadoprueba the estadoprueba to set
     */
    public void setEstadoprueba(String estadoprueba) {
        this.estadoprueba = estadoprueba;
    }

    /**
     * @return the dias
     */
    public int getDias() {
        return dias;
    }

    /**
     * @param dias the dias to set
     */
    public void setDias(int dias) {
        this.dias = dias;
    }

    /**
     * @return the vtiger_campo_estado
     */
    public String getVtiger_campo_estado() {
        return vtiger_campo_estado;
    }

    /**
     * @param vtiger_campo_estado the vtiger_campo_estado to set
     */
    public void setVtiger_campo_estado(String vtiger_campo_estado) {
        this.vtiger_campo_estado = vtiger_campo_estado;
    }

    /**
     * @return the vtiger_campo_texto
     */
    public String getVtiger_campo_texto() {
        return vtiger_campo_texto;
    }

    /**
     * @param vtiger_campo_texto the vtiger_campo_texto to set
     */
    public void setVtiger_campo_texto(String vtiger_campo_texto) {
        this.vtiger_campo_texto = vtiger_campo_texto;
    }

    /**
     * @return the fechaaplicacion
     */
    public Date getFechaaplicacion() {
        return fechaaplicacion;
    }

    /**
     * @param fechaaplicacion the fechaaplicacion to set
     */
    public void setFechaaplicacion(Date fechaaplicacion) {
        this.fechaaplicacion = fechaaplicacion;
    }

    /**
     * @return the vtiger_campo_cargo
     */
    public String getVtiger_campo_cargo() {
        return vtiger_campo_cargo;
    }

    /**
     * @param vtiger_campo_cargo the vtiger_campo_cargo to set
     */
    public void setVtiger_campo_cargo(String vtiger_campo_cargo) {
        this.vtiger_campo_cargo = vtiger_campo_cargo;
    }

    public String getTipoPrueba() {
        return tipoPrueba;
    }

    public void setTipoPrueba(String tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }
    
    
}
