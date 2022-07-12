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
public class ConsultaOrdenes {
    private Long idOrden;
    private String descripcion;
    private String codigoPuesto;
    private String nombrePuesto;
    private String nombreMunicipio;
    private String  nombreDepartamento;
    private String pds;

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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the codigoPuesto
     */
    public String getCodigoPuesto() {
        return codigoPuesto;
    }

    /**
     * @param codigoPuesto the codigoPuesto to set
     */
    public void setCodigoPuesto(String codigoPuesto) {
        this.codigoPuesto = codigoPuesto;
    }

    /**
     * @return the nombrePuesto
     */
    public String getNombrePuesto() {
        return nombrePuesto;
    }

    /**
     * @param nombrePuesto the nombrePuesto to set
     */
    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
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
     * @return the pds
     */
    public String getPds() {
        return pds;
    }

    /**
     * @param pds the pds to set
     */
    public void setPds(String pds) {
        this.pds = pds;
    }
}
