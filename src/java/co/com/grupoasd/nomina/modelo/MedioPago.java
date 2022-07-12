/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ivan Vargas
 */
@XmlRootElement
public class MedioPago {

    private int id_medio;
    private String nombre;
    private int activo;
    private int idTipoMedioPago;
    private boolean solicitarEntidadBancaria;
    private boolean solicitarNumeroCuenta;
    private boolean solicitarTipoCuenta;
    private int auxilioTransporte;
    private int costoTransaccion;
    private float porcentajeTransaccion;
    private String descripcion;
    private boolean costoTransaccionPorValor;
    private int idPrueba;


    public MedioPago() {

    }

    /**
     * @return the id_medio
     */
    public int getId_medio() {
        return id_medio;
    }

    /**
     * @param id the id_medio to set
     */
    public void setId_medio(int id) {
        this.id_medio = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getIdTipoMedioPago() {
        return idTipoMedioPago;
    }

    public void setIdTipoMedioPago(int idTipoMedioPago) {
        this.idTipoMedioPago = idTipoMedioPago;
    }

    public boolean getSolicitarEntidadBancaria() {
        return solicitarEntidadBancaria;
    }

    public void setSolicitarEntidadBancaria(boolean solicitarEntidadBancaria) {
        this.solicitarEntidadBancaria = solicitarEntidadBancaria;
    }

    public boolean getSolicitarNumeroCuenta() {
        return solicitarNumeroCuenta;
    }

    public void setSolicitarNumeroCuenta(boolean solicitarNumeroCuenta) {
        this.solicitarNumeroCuenta = solicitarNumeroCuenta;
    }

    public boolean getSolicitarTipoCuenta() {
        return solicitarTipoCuenta;
    }

    public void setSolicitarTipoCuenta(boolean solicitarTipoCuenta) {
        this.solicitarTipoCuenta = solicitarTipoCuenta;
    }

    public int getAuxilioTransporte() {
        return auxilioTransporte;
    }

    public void setAuxilioTransporte(int auxilioTransporte) {
        this.auxilioTransporte = auxilioTransporte;
    }

    public int getCostoTransaccion() {
        return costoTransaccion;
    }

    public void setCostoTransaccion(int costoTransaccion) {
        this.costoTransaccion = costoTransaccion;
    }

    public float getPorcentajeTransaccion() {
        return porcentajeTransaccion;
    }

    public void setPorcentajeTransaccion(float porcentajeTransaccion) {
        this.porcentajeTransaccion = porcentajeTransaccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCostoTransaccionPorValor() {
        return costoTransaccionPorValor;
    }

    public void setCostoTransaccionPorValor(boolean costoTransaccionPorValor) {
        this.costoTransaccionPorValor = costoTransaccionPorValor;
    }

    public int getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(int idPrueba) {
        this.idPrueba = idPrueba;
    }
    
    
      
    

}
