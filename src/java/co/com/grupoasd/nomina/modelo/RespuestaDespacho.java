/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

/**
 *
 * @author CristianAlexander
 */
public class RespuestaDespacho {
    private Long idsuplenciadetalle;
    private String observaciones;
    private int estado;
    private Integer codigo;
    private String mensaje;

    /**
     * @return the idsuplenciadetalle
     */
    public Long getIdsuplenciadetalle() {
        return idsuplenciadetalle;
    }

    /**
     * @param idsuplenciadetalle the idsuplenciadetalle to set
     */
    public void setIdsuplenciadetalle(Long idsuplenciadetalle) {
        this.idsuplenciadetalle = idsuplenciadetalle;
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
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
