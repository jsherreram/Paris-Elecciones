/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.grupoasd.nomina.modelo;

/**
 *
 * @author Grupo ASD
 */
public class InformacionControlPdsSuplencia {
    private String nombreSitio;
    private String codigoSitio;
    private Integer personasAsistentes;
    private Integer personasDisponibles;
    private Integer ordenesNuevas;
    private Integer ordenesDespachadas;
    private String nombreRPS;
    private String celularRPS;
    private Integer idPds;

    /**
     * @return the nombreSitio
     */
    public String getNombreSitio() {
        return nombreSitio;
    }

    /**
     * @param nombreSitio the nombreSitio to set
     */
    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    /**
     * @return the codigoSitio
     */
    public String getCodigoSitio() {
        return codigoSitio;
    }

    /**
     * @param codigoSitio the codigoSitio to set
     */
    public void setCodigoSitio(String codigoSitio) {
        this.codigoSitio = codigoSitio;
    }

    /**
     * @return the personasAsistentes
     */
    public Integer getPersonasAsistentes() {
        return personasAsistentes;
    }

    /**
     * @param personasAsistentes the personasAsistentes to set
     */
    public void setPersonasAsistentes(Integer personasAsistentes) {
        this.personasAsistentes = personasAsistentes;
    }

    /**
     * @return the personasDisponibles
     */
    public Integer getPersonasDisponibles() {
        return personasDisponibles;
    }

    /**
     * @param personasDisponibles the personasDisponibles to set
     */
    public void setPersonasDisponibles(Integer personasDisponibles) {
        this.personasDisponibles = personasDisponibles;
    }

    /**
     * @return the ordenesNuevas
     */
    public Integer getOrdenesNuevas() {
        return ordenesNuevas;
    }

    /**
     * @param ordenesNuevas the ordenesNuevas to set
     */
    public void setOrdenesNuevas(Integer ordenesNuevas) {
        this.ordenesNuevas = ordenesNuevas;
    }

    /**
     * @return the ordenesDespachadas
     */
    public Integer getOrdenesDespachadas() {
        return ordenesDespachadas;
    }

    /**
     * @param ordenesDespachadas the ordenesDespachadas to set
     */
    public void setOrdenesDespachadas(Integer ordenesDespachadas) {
        this.ordenesDespachadas = ordenesDespachadas;
    }

    /**
     * @return the nombreRPS
     */
    public String getNombreRPS() {
        return nombreRPS;
    }

    /**
     * @param nombreRPS the nombreRPS to set
     */
    public void setNombreRPS(String nombreRPS) {
        this.nombreRPS = nombreRPS;
    }

    /**
     * @return the celularRPS
     */
    public String getCelularRPS() {
        return celularRPS;
    }

    /**
     * @param celularRPS the celularRPS to set
     */
    public void setCelularRPS(String celularRPS) {
        this.celularRPS = celularRPS;
    }

    /**
     * @return the idPds
     */
    public Integer getIdPds() {
        return idPds;
    }

    /**
     * @param idPds the idPds to set
     */
    public void setIdPds(Integer idPds) {
        this.idPds = idPds;
    }
}
