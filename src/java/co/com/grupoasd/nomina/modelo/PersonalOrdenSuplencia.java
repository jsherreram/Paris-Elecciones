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
public class PersonalOrdenSuplencia {

    private Long idDetalle;
    private Long nrodoc;
    private Long idOrden;
    private Integer estado;
    private Integer prioridad;
    private Integer codigoPds;
    private String estadoCodigo;
    private String nombres;
    private String apellidos;

    /**
     * @return the idDetalle
     */
    public Long getIdDetalle() {
        return idDetalle;
    }

    /**
     * @param idDetalle the idDetalle to set
     */
    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    /**
     * @return the nrodoc
     */
    public Long getNrodoc() {
        return nrodoc;
    }

    /**
     * @param nrodoc the nrodoc to set
     */
    public void setNrodoc(Long nrodoc) {
        this.nrodoc = nrodoc;
    }

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
     * @return the estado
     */
    public Integer getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    /**
     * @return the prioridad
     */
    public Integer getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * @return the codigoPds
     */
    public Integer getCodigoPds() {
        return codigoPds;
    }

    /**
     * @param codigoPds the codigoPds to set
     */
    public void setCodigoPds(Integer codigoPds) {
        this.codigoPds = codigoPds;
    }

    /**
     * @return the estadoCodigo
     */
    public String getEstadoCodigo() {
        return estadoCodigo;
    }

    /**
     * @param estadoCodigo the estadoCodigo to set
     */
    public void setEstadoCodigo(String estadoCodigo) {
        this.estadoCodigo = estadoCodigo;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

}
