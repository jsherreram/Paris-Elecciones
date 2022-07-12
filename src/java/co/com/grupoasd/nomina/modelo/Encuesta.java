/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 *
 * @author Pedro Rodríguez
 */

@XmlRootElement
public class Encuesta {

        private int idEncuesta;
        private int idDivipol;
        private int codigoEvento;
        private int estaListo;
        private String horaLlegada;
        private int cantidadAsistio;
        private int cantidadNoAsistio;
        private int cantidadAnulados;
        private String motivosAnulacion; 
        private String novedades;
        private String usuario;
        private Date fecha;
        private int actualizado;    
    
        private int estaElDelegado;
        private String horaLlegadaDelegado;
        private int estaElPagador;
        private String horaInicioSesion;
        private String horaFinSesion;
        private String horaDevolucionMaterial;
        private int salonesListos;
        private  String novedadesSitio;
        private int estadoEncuesta;
                
    
    
    
    public Encuesta()
    {
        
    }

    /**
     * @return the idDivipol
     */
    public int getIdDivipol() {
        return idDivipol;
    }

    /**
     * @param idDivipol the idDivipol to set
     */
    public void setIdDivipol(int idDivipol) {
        this.idDivipol = idDivipol;
    }

    /**
     * @return the codigoEvento
     */
    public int getCodigoEvento() {
        return codigoEvento;
    }

    /**
     * @param codigoEvento the codigoEvento to set
     */
    public void setCodigoEvento(int codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    /**
     * @return the estaListo
     */
    public int getEstaListo() {
        return estaListo;
    }

    /**
     * @param estaListo the estaListo to set
     */
    public void setEstaListo(int estaListo) {
        this.estaListo = estaListo;
    }

    /**
     * @return the horaLlegada
     */
    public String getHoraLlegada() {
        return horaLlegada;
    }

    /**
     * @param horaLlegada the horaLlegada to set
     */
    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
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
     * @return the cantidadAnulados
     */
    public int getCantidadAnulados() {
        return cantidadAnulados;
    }

    /**
     * @param cantidadAnulados the cantidadAnulados to set
     */
    public void setCantidadAnulados(int cantidadAnulados) {
        this.cantidadAnulados = cantidadAnulados;
    }

    /**
     * @return the motivosAnulacion
     */
    public String getMotivosAnulacion() {
        return motivosAnulacion;
    }

    /**
     * @param motivosAnulacion the motivosAnulacion to set
     */
    public void setMotivosAnulacion(String motivosAnulacion) {
        this.motivosAnulacion = motivosAnulacion;
    }

    /**
     * @return the novedades
     */
    public String getNovedades() {
        return novedades;
    }

    /**
     * @param novedades the novedades to set
     */
    public void setNovedades(String novedades) {
        this.novedades = novedades;
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
     * @return the actualizado
     */
    public int getActualizado() {
        return actualizado;
    }

    /**
     * @param actualizado the actualizado to set
     */
    public void setActualizado(int actualizado) {
        this.actualizado = actualizado;
    }

    /**
     * @return the idEncuesta
     */
    public int getIdEncuesta() {
        return idEncuesta;
    }

    /**
     * @param idEncuesta the idEncuesta to set
     */
    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    /**
     * @return the estaElDelegado
     */
    public int getEstaElDelegado() {
        return estaElDelegado;
    }

    /**
     * @param estaElDelegado the estaElDelegado to set
     */
    public void setEstaElDelegado(int estaElDelegado) {
        this.estaElDelegado = estaElDelegado;
    }

    /**
     * @return the horaLlegadaDelegado
     */
    public String getHoraLlegadaDelegado() {
        return horaLlegadaDelegado;
    }

    /**
     * @param horaLlegadaDelegado the horaLlegadaDelegado to set
     */
    public void setHoraLlegadaDelegado(String horaLlegadaDelegado) {
        this.horaLlegadaDelegado = horaLlegadaDelegado;
    }

    /**
     * @return the estaElPagador
     */
    public int getEstaElPagador() {
        return estaElPagador;
    }

    /**
     * @param estaElPagador the estaElPagador to set
     */
    public void setEstaElPagador(int estaElPagador) {
        this.estaElPagador = estaElPagador;
    }

    public String getHoraInicioSesion() {
        return horaInicioSesion;
    }

    public void setHoraInicioSesion(String horaInicioSesion) {
        this.horaInicioSesion = horaInicioSesion;
    }

    public String getHoraFinSesion() {
        return horaFinSesion;
    }

    public void setHoraFinSesion(String horaFinSesion) {
        this.horaFinSesion = horaFinSesion;
    }

    public String getHoraDevolucionMaterial() {
        return horaDevolucionMaterial;
    }

    public void setHoraDevolucionMaterial(String horaDevolucionMaterial) {
        this.horaDevolucionMaterial = horaDevolucionMaterial;
    }

    public int getSalonesListos() {
        return salonesListos;
    }

    public void setSalonesListos(int salonesListos) {
        this.salonesListos = salonesListos;
    }

    public String getNovedadesSitio() {
        return novedadesSitio;
    }

    public void setNovedadesSitio(String novedadesSitio) {
        this.novedadesSitio = novedadesSitio;
    }

    public int getEstadoEncuesta() {
        return estadoEncuesta;
    }

    public void setEstadoEncuesta(int estadoEncuesta) {
        this.estadoEncuesta = estadoEncuesta;
    }
    
    
}
